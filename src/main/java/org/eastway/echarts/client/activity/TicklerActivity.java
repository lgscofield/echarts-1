/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.EhrRequest;
import org.eastway.echarts.client.request.PatientProxy;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TicklerActivity extends AbstractActivity implements TicklerView.Presenter<Tickler> {

	class EHRFetcher {
		EHRProxy fetchedEHR;
		List<AssignmentProxy> fetchedAssignments;

		void Run(final EhrRequest ehrRequest, final AssignmentRequest assignmentRequest, final String caseNumber, final Receiver<EHRFetcher> callback) {
			ehrRequest.findEHRByCaseNumber(caseNumber)
				.with("patient")
				.with("patient.caseStatus")
				.with("demographics")
				.with("demographics.gender")
				.with("demographics.employment")
				.with("demographics.maritalStatus")
				.fire(
					new Receiver<EHRProxy>() {
						@Override
						public void onSuccess(EHRProxy response) {
							if (response != null) {
								fetchedEHR = response;
								assignmentRequest.findAssignmentsByCaseNumber(caseNumber).fire(
										new Receiver<List<AssignmentProxy>>() {
											@Override
											public void onSuccess(List<AssignmentProxy> response) {
												fetchedAssignments = response;
												if (fetchedAssignments != null)
													callback.onSuccess(EHRFetcher.this);
											}
										});
							}
						}
					});
		}
	}

	private TicklerView<Tickler> view;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;

	public TicklerActivity(TicklerPlace place,
						   EchartsRequestFactory requestFactory,
						   List<ColumnDefinition<Tickler>> columnDefinitions,
						   PlaceController placeController,
						   TicklerView<Tickler> view) {
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.view = view;
		this.view.setColumnDefinitions(columnDefinitions);
		this.view.setPresenter(this);
	}

	public void fetchData() {
		requestFactory.assignmentRequest().findAssignmentsByStaff(EchartsUser.staffId)
			.with("patient")
			.with("demographics")
				.fire(new Receiver<List<AssignmentProxy>>() {
			@Override
			public void onSuccess(List<AssignmentProxy> response) {
				view.setRowData(setDates(response));
				panel.setWidget(view);
			}
		});
	}

	private long NOW = new Date().getTime();
	private long day = 24L * 3600L * 1000L;
	private long YEAR = 365L * day;
	private long THIRTY_DAYS = 30L * day;
	private long NINETY_DAYS = 90L * day;
	private long ONE_HUNDRED_EIGHTY_DAYS = 180L * day;
	private AcceptsOneWidget panel;

	private List<Tickler> setDates(List<AssignmentProxy> assignments) {
		List<Tickler> tickler = new ArrayList<Tickler>();
		for (AssignmentProxy assignment : assignments) {
			Tickler result = new Tickler();
			PatientProxy patient = assignment.getPatient();
			result.setName(assignment.getPatient().getName());
			result.setCaseNumber(patient.getCaseNumber());
	
			if (patient.getHipaaDateCompleted() != null)
				result.setHipaaDateCompleted(formatDueDate(patient.getHipaaDateCompleted().getTime()));
	
			long ispDueDate = 0L;
			long ispDateCompleted = 0L;
			if (patient.getIspDateCompleted() != null) {
				ispDueDate = patient.getIspDateCompleted().getTime() + YEAR;
				ispDateCompleted = patient.getIspDateCompleted().getTime();
			}
			result.setIspDueDate(formatDueDate(ispDueDate), getDueDateStatus(ispDueDate));
	
			long ispReviewDueDate = getIspReviewDueDate(ispDueDate, ispDateCompleted, assignment, patient);
			result.setIspReviewDueDate(formatDueDate(ispReviewDueDate), getDueDateStatus(ispReviewDueDate));
	
			long healthHistoryDueDate = 0L;
			if (patient.getHealthHistoryDateCompleted() != null)
				healthHistoryDueDate = patient.getHealthHistoryDateCompleted().getTime() + YEAR;
			result.setHealthHistoryDueDate(formatDueDate(healthHistoryDueDate), getDueDateStatus(healthHistoryDueDate));
	
			long diagnosticAssessmentUpdateDueDate = 0L;
			if (patient.getDiagnosticAssessmentDateCompleted() != null) {
				if (assignment.getService().matches("Pgm021")
						|| assignment.getService().matches("Pgm022")
						|| assignment.getService().matches("Pgm023")
						|| assignment.getService().matches("Pgm030"))
					diagnosticAssessmentUpdateDueDate = patient.getDiagnosticAssessmentDateCompleted().getTime() + YEAR;
				else
					diagnosticAssessmentUpdateDueDate = patient.getDiagnosticAssessmentDateCompleted().getTime() + (2L * YEAR);
			}
			result.setDiagnosticAssessmentUpdate(formatDueDate(diagnosticAssessmentUpdateDueDate), getDueDateStatus(diagnosticAssessmentUpdateDueDate));
	
			long financialDueDate = 0L;
			if (patient.getFinancialDateCompleted() != null) {
				if (patient.getTitleTwenty())
					financialDueDate = patient.getFinancialDateCompleted().getTime() + ONE_HUNDRED_EIGHTY_DAYS;
				else
					financialDueDate = patient.getFinancialDateCompleted().getTime() + YEAR;
			}
			result.setFinancialDueDate(formatDueDate(financialDueDate), getDueDateStatus(financialDueDate));
	
			long oocDueDate = 0L;
			if (patient.getOutcomesConsumerDateCompleted() != null)
				oocDueDate = patient.getOutcomesConsumerDateCompleted().getTime() + YEAR;
			result.setOoc(formatDueDate(oocDueDate), getDueDateStatus(oocDueDate));
			result.setService(assignment.getService());
			result.setStaffName(assignment.getStaffName());
			tickler.add(result);
		}
		return tickler;
	}

	private long getIspReviewDueDate(long ispDueDate, long ispDateCompleted, AssignmentProxy assignment, PatientProxy patient) {
		long ispReviewDueDate = 0L;
		if (getDueDateStatus(ispDueDate) == DueDateStatus.OVERDUE) {
			return ispDueDate;
		} else {
			boolean aodStatus = (assignment.getDemographics().getAlcoholDrug()==null?false:assignment.getDemographics().getAlcoholDrug())&&(EchartsUser.staffId=="5542"||EchartsUser.staffId=="5396");
			if (patient.getIspReviewDateCompleted() != null) {
				if (assignment.getService().matches("Pgm076")
						|| aodStatus
						|| assignment.getService().matches("Pgm021")
						|| assignment.getService().matches("Pgm022")
						|| assignment.getService().matches("Pgm023")
						|| assignment.getService().matches("Pgm030")) {
					ispReviewDueDate = patient.getIspReviewDateCompleted().getTime() + NINETY_DAYS;
				} else {
					ispReviewDueDate = patient.getIspReviewDateCompleted().getTime() + ONE_HUNDRED_EIGHTY_DAYS;
				}
				if (ispReviewDueDate > ispDueDate)
					return ispDueDate;
			} else {
				if (assignment.getService().matches("Pgm076") || aodStatus) {
					ispReviewDueDate = ispDateCompleted + NINETY_DAYS;
				} else {
					ispReviewDueDate = ispDateCompleted + ONE_HUNDRED_EIGHTY_DAYS;
				}
				return ispReviewDueDate;
			}
		}
		return ispReviewDueDate;
	}

	private int getDueDateStatus(long dueDate) {
		if (dueDate == 0L)
			return DueDateStatus.NO_DATA;
		if ((dueDate - NOW) > THIRTY_DAYS)
			return DueDateStatus.COMPLIANT;
		else if (dueDate > NOW)
			return DueDateStatus.DUE_IN_THIRTY_DAYS;
		else
			return DueDateStatus.OVERDUE;
	}


	private String formatDueDate(long dueDate) {
		String dateFormat = "M/d/y";
		return DateTimeFormat.getFormat(dateFormat)
			.format(new Date(dueDate));
	}

	public TicklerView<Tickler> getDisplay() {
		return view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		fetchData();
	}

	@Override
	public void goTo(Tickler tickler) {
		placeController.goTo(new PatientSummaryPlace(tickler.getCaseNumber()));
	}

	@Override
	public void openEhr(Tickler t) {
		Window.open("http://" + Window.Location.getHost() + Window.Location.getPath() + Window.Location.getQueryString() + "#PatientSummaryPlace:" + t.getCaseNumber(), "_blank", "");
	}
}
