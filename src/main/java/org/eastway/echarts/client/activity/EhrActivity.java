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

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsClientFactory;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ViewARInfoEvent;
import org.eastway.echarts.client.events.ViewAddressesEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewDiagnosesEvent;
import org.eastway.echarts.client.events.ViewLabsEvent;
import org.eastway.echarts.client.events.ViewLinksEvent;
import org.eastway.echarts.client.events.ViewMedicationsEvent;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.events.ViewServiceHistoryEvent;
import org.eastway.echarts.client.events.ViewTreatmentPlanEvent;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.EhrPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.rpc.AssignmentProxy;
import org.eastway.echarts.client.rpc.AssignmentRequest;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EhrRequest;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.shared.GetARInfo;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

public class EhrActivity extends AbstractActivity implements Presenter, EHRView.Presenter<EHRProxy> {

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

	private EHRProxy ehr;
	private EHRView<EHRProxy> view;
	private EventBus eventBus;
	private long ehrId;
	private GetAppointments appointments = null;
	private GetReferral referral = null;
	private GetDiagnoses diagnoses;
	private GetLinks links = null;
	private GetAddresses addresses = null;
	private GetMedications medications = null;
	private GetARInfo aRInfo = null;
	private EchartsClientFactory clientFactory;
	private String caseNumber;

	public EhrActivity(EHRViewImpl<EHRProxy> ehrView, EventBus eventBus, EHRProxy ehr) {
		this.eventBus = eventBus;
		this.view = ehrView;
		this.view.setPresenter(this);
		setData(ehr);
	}

	public EhrActivity(EhrPlace place, EchartsClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.caseNumber = place.getCaseNumber();
	}

	protected void setData(EHRProxy ehr) {
		this.ehr = ehr;
		clientFactory.getEventBus().fireEvent(new ChangeCurrentEhrEvent<EHRProxy>(ehr));
	}

	private void setActions() {
		appointments = new GetAppointments(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		referral = new GetReferral(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		diagnoses = new GetDiagnoses(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		links = new GetLinks(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		addresses = new GetAddresses(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		medications = new GetMedications(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
		aRInfo = new GetARInfo(EchartsUser.sessionId, ehr.getPatient().getCaseNumber());
	}

	@Override
	public void go(final HasWidgets container) {
		setActions();
		viewPatientSummary();
	}

	@Override
	public void viewPatientSummary() {
		clientFactory.getPlaceController().goTo(new PatientSummaryPlace(caseNumber));
	}

	@Override
	public EHRProxy getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		clientFactory.getPlaceController().goTo(new MessagePlace(caseNumber));
	}

	@Override
	public void viewDemographics() {
		clientFactory.getPlaceController().goTo(new DemographicsPlace(caseNumber));
	}

	@Override
	public void viewReferral() {
		eventBus.fireEvent(new ViewReferralEvent<EHRProxy>(ehrId, view, referral));
	}

	@Override
	public void viewAppointments() {
		eventBus.fireEvent(new ViewAppointmentsEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, appointments));
	}

	@Override
	public void viewDiagnoses() {
		eventBus.fireEvent(new ViewDiagnosesEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, diagnoses));
	}

	@Override
	public void viewLinks() {
		eventBus.fireEvent(new ViewLinksEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, links));
	}

	@Override
	public void viewAddresses() {
		eventBus.fireEvent(new ViewAddressesEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, addresses));
	}

	@Override
	public void viewMedications() {
		eventBus.fireEvent(new ViewMedicationsEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, medications));
	}

	@Override
	public void viewTreatmentPlan() {
		eventBus.fireEvent(new ViewTreatmentPlanEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view));
	}

	@Override
	public void viewServiceHistory() {
		eventBus.fireEvent(new ViewServiceHistoryEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view));
	}

	@Override
	public void viewARInfo() {
		eventBus.fireEvent(new ViewARInfoEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view, aRInfo));
	}

	@Override
	public void viewLabs() {
		eventBus.fireEvent(new ViewLabsEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view));
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getEhrView();
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
		final EhrRequest ehrRequest = clientFactory.getRequestFactory().ehrRequest();
		AssignmentRequest assignmentRequest = clientFactory.getRequestFactory().assignmentRequest();
		new EHRFetcher().Run(ehrRequest, assignmentRequest, caseNumber, new Receiver<EhrActivity.EHRFetcher>() {
			@Override
			public void onSuccess(EHRFetcher response) {
				EHRProxy ehr = clientFactory.getRequestFactory().ehrRequest().edit(response.fetchedEHR);
				ehr.setAssignments(response.fetchedAssignments);
				setData(ehr);
			}
		});
	}
}