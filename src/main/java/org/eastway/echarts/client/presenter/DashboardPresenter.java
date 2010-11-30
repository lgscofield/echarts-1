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

import java.util.LinkedHashMap;

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ChangeCurrentEhrEventHandler;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.ViewGroupProgressNoteEvent;
import org.eastway.echarts.client.events.ViewLastSeenReportEvent;
import org.eastway.echarts.client.events.ViewMedsomSignaturesEvent;
import org.eastway.echarts.client.events.ViewOverlapsReportEvent;
import org.eastway.echarts.client.events.ViewProfileEvent;
import org.eastway.echarts.client.events.ViewProviderSignaturesEvent;
import org.eastway.echarts.client.events.ViewStaffHistoryEvent;
import org.eastway.echarts.client.events.ViewSupervisorSignaturesEvent;
import org.eastway.echarts.client.events.ViewTicklerEvent;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetPatientSummaryResult;
import org.eastway.echarts.shared.GetProductivity;
import org.eastway.echarts.shared.GetProductivityResult;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class DashboardPresenter implements Presenter, DashboardView.Presenter<LinkedHashMap<String, Long>> {

	private DashboardView<LinkedHashMap<String, Long>> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private String caseNumber;

	@Inject
	public DashboardPresenter(DashboardView<LinkedHashMap<String, Long>> view,
			EventBus eventBus, final CachingDispatchAsync dispatch) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
	}

	private void bind() {
		eventBus.addHandler(ChangeCurrentEhrEvent.TYPE, new ChangeCurrentEhrEventHandler() {
			@Override
			public <T> void onChangeCurrentEhr(ChangeCurrentEhrEvent<T> event) {
				setCurrentEhrData((GetPatientSummaryResult)event.getEhr());
			}
		});
	}

	public DashboardView<LinkedHashMap<String, Long>> getDisplay() {
		return view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchData();
		if (Cookies.getCookie("first_login") != null)
			view.isFirstLogin();
	}

	private void setCurrentEhrData(final GetPatientSummaryResult ehr) {
		if (ehr == null) {
			view.showEhrStub(false);
			return;
		}
		Patient patient = ehr.getPatient();
		Demographics demographics = ehr.getDemographics();
		view.setName(patient.getName());
		view.setCaseStatus(patient.getCaseStatus().getDescriptor() == null ? "" : patient.getCaseStatus().getDescriptor());
		view.setDob(demographics.getDob());
		view.setProvider(ehr.getProvider() == null ? "" : ehr.getProvider());
		view.setSsn(patient.getSsn());
		view.showEhrStub(true);
	}

	private void fetchData() {
		getProductivityData();
	}

	@Override
	public void changeCurrentEhr(Object ehr) {
			eventBus.fireEvent(new ChangeCurrentEhrEvent<GetPatientSummaryResult>((GetPatientSummaryResult)ehr));
	}

	@Override
	public void openEhr(String text) {
		caseNumber = text.replaceAll("(.*) - .*", "$1");
		dispatch.execute(new GetPatientSummary(EchartsUser.sessionId, caseNumber, EchartsUser.staffId), new EchartsCallback<GetPatientSummaryResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetPatientSummaryResult t) {
				eventBus.fireEvent(new OpenEhrEvent(t));	
			}
		});
	}

	private void getProductivityData() {
		dispatch.execute(new GetProductivity(EchartsUser.sessionId, EchartsUser.staffId), new EchartsCallback<GetProductivityResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetProductivityResult result) {
				String color = null;
				if (result.getTotal().doubleValue() < result.getYellowNumber())
					color = "red";
				else if (result.getTotal().doubleValue() < result.getGreenNumber())
					color = "yellow";
				else
					color = "green";
				view.setProductivity(result.getTotal().toPlainString(), color);
				view.setBonusProjection(new Double(result.getGreenNumber()).toString());
			}
		});
	}

	@Override
	public void openTickler() {
		eventBus.fireEvent(new ViewTicklerEvent());
	}

	@Override
	public void logout() {
		eventBus.fireEvent(new LogoutEvent());
	}

	@Override
	public void openProfile() {
		eventBus.fireEvent(new ViewProfileEvent());
	}

	@Override
	public void openProviderSignatures() {
		eventBus.fireEvent(new ViewProviderSignaturesEvent());
	}

	@Override
	public void openSupervisorSignatures() {
		eventBus.fireEvent(new ViewSupervisorSignaturesEvent());
	}

	@Override
	public void openMedsomSignatures() {
		eventBus.fireEvent(new ViewMedsomSignaturesEvent());
	}

	@Override
	public void openStaffHistory() {
		eventBus.fireEvent(new ViewStaffHistoryEvent());
	}

	@Override
	public void openGroupProgressNote() {
		eventBus.fireEvent(new ViewGroupProgressNoteEvent());
	}

	@Override
	public void openLastSeenReport() {
		eventBus.fireEvent(new ViewLastSeenReportEvent());
	}

	@Override
	public void openOverlapsReport() {
		eventBus.fireEvent(new ViewOverlapsReportEvent());
	}
}
