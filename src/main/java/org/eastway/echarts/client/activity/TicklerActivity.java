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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.PatientProxy;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TicklerActivity extends AbstractActivity implements TicklerView.Presenter<Tickler> {
	private TicklerView<Tickler> view;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;
	private String staffId;

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
		this.staffId = place.getTicklerName();
	}

	public void fetchData() {
		AssignmentRequest context = requestFactory.assignmentRequest();
		context.findAssignmentsByStaff(EchartsUser.staffId, staffId)
			.with("patient")
			.with("demographics")
			.to(new Receiver<List<AssignmentProxy>>() {
			@Override
			public void onSuccess(List<AssignmentProxy> response) {
				if (response != null && response.size() != 0) {
					view.setRowData(setDates(response));
					view.setNoteTimeliness(
							NO_DATA_COUNT.intValue(),
							NO_DATA_COUNT.divide(TOTAL_COUNT, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue(),
							OVERDUE_COUNT.intValue(),
							OVERDUE_COUNT.divide(TOTAL_COUNT, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue(),
							UP_TO_DATE_COUNT.intValue(),
							UP_TO_DATE_COUNT.divide(TOTAL_COUNT, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue());
					panel.setWidget(view);
				}
			}
		});
		if (!staffId.equals(EchartsUser.staffId)) {
			context.findUserByStaffId(staffId).to(new Receiver<UserProxy>() {
				@Override
				public void onSuccess(UserProxy response) {
					if (response != null)
						view.setHeader(response.getStaffName());
				}
			});
		}
		context.fire();
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

			if (patient.getHipaaDateCompleted() != null) {
				TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
				UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
				result.setHipaaDateCompleted(formatDueDate(patient.getHipaaDateCompleted().getTime()));
			} else {
				TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
				NO_DATA_COUNT = NO_DATA_COUNT.add(new BigDecimal(1));
			}

			long ispDueDate = 0L;
			long ispDateCompleted = 0L;
			if (patient.getIspDateCompleted() != null) {
				ispDueDate = patient.getIspDateCompleted().getTime() + YEAR;
				ispDateCompleted = patient.getIspDateCompleted().getTime();
			}
			int ispDueDateStatus = getDueDateStatus(ispDueDate);
			result.setIspDueDate(formatDueDate(ispDueDate), ispDueDateStatus);
	
			long ispReviewDueDate = getIspReviewDueDate(ispDueDate, ispDueDateStatus, ispDateCompleted, assignment, patient);
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

	private long getIspReviewDueDate(long ispDueDate, int ispDueDateStatus, long ispDateCompleted, AssignmentProxy assignment, PatientProxy patient) {
		long ispReviewDueDate = 0L;
		if (ispDueDateStatus == DueDateStatus.OVERDUE) {
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

	private BigDecimal NO_DATA_COUNT = new BigDecimal(0.0);
	private BigDecimal OVERDUE_COUNT = new BigDecimal(0.0);
	private BigDecimal UP_TO_DATE_COUNT = new BigDecimal(0.0);
	private BigDecimal TOTAL_COUNT = new BigDecimal(0.0);

	/**
	 * Determine status of {@code dueDate} and return an integer constant from
	 * {@link org.eastway.echarts.shared.DueDateStatus} representing the status.
	 * 
	 * This method should be called exactly once for each column to be displayed
	 * in the tickler table.
	 * 
	 * @param dueDate the long value to be tested
	 * @return an integer from {@code org.eastway.echarts.shared.DueDateStatus}
	 */
	private int getDueDateStatus(long dueDate) {
		TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
		if (dueDate == 0L) {
			NO_DATA_COUNT = NO_DATA_COUNT.add(new BigDecimal(1));
			return DueDateStatus.NO_DATA;
		}
		if ((dueDate - NOW) > THIRTY_DAYS) {
			UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.COMPLIANT;
		} else if (dueDate > NOW) {
			UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.DUE_IN_THIRTY_DAYS;
		} else {
			OVERDUE_COUNT = OVERDUE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.OVERDUE;
		}
	}


	private String formatDueDate(long dueDate) {
		return view.formatTicklerDate(new Date(dueDate));
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
		Window.open(Window.Location.createUrlBuilder()
			.setHash("#patientsummary:" + t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openIsp(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/GandO.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openIndividualProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/102IPNEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openCpstNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/101CPSTEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openDoctorProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/104PharmEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openNurseProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/103PM-NPNEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openPrintablePatientSummary(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Client/demographicsprint.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber())
				.buildString(), "_blank", "");
	}
}
