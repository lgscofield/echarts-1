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

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ViewAddressesEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewContactsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewDiagnosesEvent;
import org.eastway.echarts.client.events.ViewLinksEvent;
import org.eastway.echarts.client.events.ViewMedicationsEvent;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class EHRPresenter implements Presenter, EHRView.Presenter<EHR> {

	private EHR ehr;
	private EHRServicesAsync ehrServices;
	private EHRView<EHR> view;
	private EventBus eventBus;
	private long ehrId;
	private GetAppointments appointments = null;
	private GetDemographics demographics = null;
	private GetPatientSummary patientSummary = null;
	private GetReferral referral = null;
	private GetDiagnoses diagnoses = null;
	private GetLinks links = null;
	private GetMessages messages = null;
	private GetAddresses addresses = null;
	private GetContacts contacts = null;
	private GetMedications medications = null;

	public EHRPresenter(EHRView<EHR> view, EventBus eventBus, EHRServicesAsync ehrServices, long ehrId) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.ehrServices = ehrServices;
		this.ehrId = ehrId;
	}

	private void fetchEhr(long ehrId) {
		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR ehr) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				setEhr(ehr);
				setActions();
				eventBus.fireEvent(new ChangeCurrentEhrEvent(getEhr()));
			}
		};
		eventBus.fireEvent(new RequestEvent(State.SENT));
		ehrServices.getEhr(ehrId, EchartsUser.sessionId, callback);
	}

	private void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	private void setActions() {
		appointments = new GetAppointments(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		demographics = new GetDemographics(EchartsUser.sessionId, new Long(ehrId).toString());
		patientSummary = new GetPatientSummary(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		referral = new GetReferral(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		diagnoses = new GetDiagnoses(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		links = new GetLinks(EchartsUser.sessionId);
		messages = new GetMessages(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		addresses = new GetAddresses(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		contacts = new GetContacts(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
		medications = new GetMedications(EchartsUser.sessionId, ehr.getSubject().getCaseNumber());
	}

	@Override
	public void go(final HasWidgets container) {
		fetchEhr(ehrId);
	}

	@Override
	public void viewPatientSummary() {
		eventBus.fireEvent(new ViewPatientSummaryEvent(ehrId, view, patientSummary));
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		eventBus.fireEvent(new ViewMessagesEvent(ehr.getSubject().getCaseNumber(), view, messages));
	}

	@Override
	public void viewDemographics() {
		eventBus.fireEvent(new ViewDemographicsEvent(ehrId, view, demographics));
	}

	@Override
	public void viewReferral() {
		eventBus.fireEvent(new ViewReferralEvent(ehrId, view, referral));
	}

	@Override
	public void viewAppointments() {
		eventBus.fireEvent(new ViewAppointmentsEvent(ehr.getSubject().getCaseNumber(), view, appointments));
	}

	@Override
	public void viewDiagnoses() {
		eventBus.fireEvent(new ViewDiagnosesEvent(ehr.getSubject().getCaseNumber(), view, diagnoses));
	}

	@Override
	public void viewLinks() {
		eventBus.fireEvent(new ViewLinksEvent(ehr.getSubject().getCaseNumber(), view, links));
	}

	@Override
	public void viewAddresses() {
		eventBus.fireEvent(new ViewAddressesEvent(ehr.getSubject().getCaseNumber(), view, addresses));
	}

	@Override
	public void viewContacts() {
		eventBus.fireEvent(new ViewContactsEvent(ehr.getSubject().getCaseNumber(), view, contacts));
	}

	@Override
	public void viewMedications() {
		eventBus.fireEvent(new ViewMedicationsEvent(ehr.getSubject().getCaseNumber(), view, medications));
	}
}