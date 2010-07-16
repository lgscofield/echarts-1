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

import java.util.Date;
import java.util.LinkedHashMap;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.client.view.TicklerView.DueDateStatus;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerResult;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TicklerPresenter implements Presenter, TicklerView.Presenter<LinkedHashMap<String, Long>> {
	private TicklerView<LinkedHashMap<String, Long>> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetTickler action;
	private long NOW = new Date().getTime();
	private long day = 24L * 3600L * 1000L;
	private long YEAR = 365L * day;
	private long NINETY_DAYS = 90L * day;
	private long THIRTY_DAYS = 30L * day;
	private long ONE_HUNDRED_EIGHTY_DAYS = 180L * day;
	private String dateFormat = "M/d/y";

	public TicklerPresenter(TicklerView<LinkedHashMap<String, Long>> view, EventBus eventBus, CachingDispatchAsync dispatch, GetTickler action) {
		this.view = view;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.view.setPresenter(this);
		this.action = action;
	}

	public void fetchData() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.executeWithCache(action, new AsyncCallback<GetTicklerResult>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetTicklerResult result) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				for (Assignment assignment : result.getAssignments())
					if (assignment != null)
						setDates(assignment);
			}
		});
	}

	private void setDates(Assignment assignment) {
		Patient patient = assignment.getPatient();
		view.setName(assignment.getPatient().getName());
		view.setCaseNumber(patient.getCaseNumber());

		if (patient.getHipaaDateCompleted() != null)
			view.setHipaaDateCompleted(DateTimeFormat.getFormat(dateFormat).format(patient.getHipaaDateCompleted()));

		long ispDueDate = 0L;
		long ispDateCompleted = 0L;
		if (patient.getIspDateCompleted() != null) {
			ispDueDate = patient.getIspDateCompleted().getTime() + YEAR;
			ispDateCompleted = patient.getIspDateCompleted().getTime();
		}
		view.setIspDueDate(formatDueDate(ispDueDate), getDueDateStatus(ispDueDate));

		long ispReviewDueDate = getIspReviewDueDate(ispDueDate, ispDateCompleted, assignment, patient);
		view.setIspReviewDueDate(formatDueDate(ispReviewDueDate), getDueDateStatus(ispReviewDueDate));

		long healthHistoryDueDate = 0L;
		if (patient.getHealthHistoryDateCompleted() != null)
			healthHistoryDueDate = patient.getHealthHistoryDateCompleted().getTime() + YEAR;
		view.setHealthHistoryDueDate(formatDueDate(healthHistoryDueDate), getDueDateStatus(healthHistoryDueDate));

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
		view.setDiagnosticAssessmentUpdate(formatDueDate(diagnosticAssessmentUpdateDueDate), getDueDateStatus(diagnosticAssessmentUpdateDueDate));

		long financialDueDate = 0L;
		if (patient.getFinancialDateCompleted() != null) {
			if (patient.isTitleTwenty())
				financialDueDate = patient.getFinancialDateCompleted().getTime() + ONE_HUNDRED_EIGHTY_DAYS;
			else
				financialDueDate = patient.getFinancialDateCompleted().getTime() + YEAR;
		}
		view.setFinancialDueDate(formatDueDate(financialDueDate), getDueDateStatus(financialDueDate));

		long oocDueDate = 0L;
		if (patient.getOutcomesConsumerDateCompleted() != null)
			oocDueDate = patient.getOutcomesConsumerDateCompleted().getTime() + YEAR;
		view.setOoc(formatDueDate(oocDueDate), getDueDateStatus(oocDueDate));
		view.nextRecord();
	}

	private DueDateStatus getDueDateStatus(long dueDate) {
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
		return DateTimeFormat.getFormat(dateFormat).format(new Date(dueDate));
	}

	private long getIspReviewDueDate(long ispDueDate, long ispDateCompleted, Assignment assignment, Patient patient) {
		long ispReviewDueDate = 0L;
		if (getDueDateStatus(ispDueDate) == DueDateStatus.OVERDUE) {
			return ispDueDate;
		} else {
			if (patient.getIspReviewDateCompleted() != null) {
				if (assignment.getService().matches("Pgm076")
						|| assignment.getDemographics().isAlcoholDrug()
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
				if (assignment.getService().matches("Pgm076")) {
					ispReviewDueDate = ispDateCompleted + NINETY_DAYS;
				} else {
					ispReviewDueDate = ispDateCompleted + ONE_HUNDRED_EIGHTY_DAYS;
				}
				return ispReviewDueDate;
			}
		}
		return ispReviewDueDate;
	}

	@Override
	public void go(HasWidgets container) {
		fetchData();
	}

	@Override
	public void openEhr(String text) {
		eventBus.fireEvent(new OpenEhrEvent(text));
	}
}
