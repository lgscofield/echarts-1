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
package org.eastway.echarts.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.events.OpenCpstNoteEvent;
import org.eastway.echarts.client.events.OpenDoctorProgressNoteEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenIndividualProgressNoteEvent;
import org.eastway.echarts.client.events.OpenIspEvent;
import org.eastway.echarts.client.events.OpenNurseProgressNoteEvent;
import org.eastway.echarts.client.rpc.AssignmentProxy;
import org.eastway.echarts.client.rpc.AssignmentRequest;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.EhrRequest;
import org.eastway.echarts.client.rpc.PatientProxy;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class TicklerPresenter implements Presenter, TicklerView.Presenter<Tickler> {

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
	private EventBus eventBus;
	private GetTickler action;
	private EchartsRequestFactory requestFactory;

	@Inject
	public TicklerPresenter(TicklerView<Tickler> view, List<ColumnDefinition<Tickler>> columnDefinitions, EventBus eventBus, EchartsRequestFactory requestFactory, GetTickler action) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.action = action;
	}

	public void fetchData() {
		action.setSessionId(EchartsUser.sessionId);
		action.setStaffId(EchartsUser.staffId);
		requestFactory.assignmentRequest().findAssignmentsByStaff(EchartsUser.staffId)
			.with("patient")
			.with("demographics")
				.fire(new Receiver<List<AssignmentProxy>>() {
			@Override
			public void onSuccess(List<AssignmentProxy> response) {
				view.setRowData(setDates(response));
			}
		});
	}

	private long NOW = new Date().getTime();
	private long day = 24L * 3600L * 1000L;
	private long YEAR = 365L * day;
	private long THIRTY_DAYS = 30L * day;
	private long NINETY_DAYS = 90L * day;
	private long ONE_HUNDRED_EIGHTY_DAYS = 180L * day;

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

	@Override
	public void go(HasWidgets container) {
		fetchData();
	}

	@Override
	public void openEhr(Tickler tickler) {
		String caseNumber = tickler.getCaseNumber();
		final EhrRequest ehrRequest = requestFactory.ehrRequest();
		AssignmentRequest assignmentRequest = requestFactory.assignmentRequest();
		new EHRFetcher().Run(ehrRequest, assignmentRequest, caseNumber, new Receiver<TicklerPresenter.EHRFetcher>() {
			@Override
			public void onSuccess(EHRFetcher response) {
				EHRProxy ehr = requestFactory.ehrRequest().edit(response.fetchedEHR);
				ehr.setAssignments(response.fetchedAssignments);
				eventBus.fireEvent(new OpenEhrEvent(ehr));
			}
		});
	}

	@Override
	public void openIsp(Tickler tickler) {
		eventBus.fireEvent(new OpenIspEvent(tickler.getCaseNumber()));
	}

	public TicklerView<Tickler> getDisplay() {
		return view;
	}

	@Override
	public void openCpstNote(Tickler tickler) {
		eventBus.fireEvent(new OpenCpstNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openIndividualProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenIndividualProgressNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openDoctorProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenDoctorProgressNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openNurseProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenNurseProgressNoteEvent(tickler.getCaseNumber()));
	}
}
