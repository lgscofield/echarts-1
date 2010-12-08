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

import com.google.gwt.event.shared.EventBus;

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
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.events.ViewServiceHistoryEvent;
import org.eastway.echarts.client.events.ViewTreatmentPlanEvent;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.shared.GetARInfo;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.user.client.ui.HasWidgets;

public class EHRPresenter implements Presenter, EHRView.Presenter<EHRProxy> {

	private EHRProxy ehr;
	private EHRViewImpl<EHRProxy> view;
	private EventBus eventBus;
	private long ehrId;
	private GetAppointments appointments = null;
	private GetReferral referral = null;
	private GetDiagnoses diagnoses;
	private GetLinks links = null;
	private GetAddresses addresses = null;
	private GetMedications medications = null;
	private GetARInfo aRInfo = null;

	public EHRPresenter(EHRViewImpl<EHRProxy> ehrView, EventBus eventBus, EHRProxy ehr) {
		this.eventBus = eventBus;
		this.view = ehrView;
		this.view.setPresenter(this);
		setData(ehr);
	}

	protected void setData(EHRProxy ehr) {
		this.ehr = ehr;
		eventBus.fireEvent(new ChangeCurrentEhrEvent<EHRProxy>(ehr));
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
		eventBus.fireEvent(new ViewPatientSummaryEvent<EHRProxy>(view, ehr.getPatient().getCaseNumber()));
	}

	@Override
	public EHRProxy getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		eventBus.fireEvent(new ViewMessagesEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view));
	}

	@Override
	public void viewDemographics() {
		eventBus.fireEvent(new ViewDemographicsEvent<EHRProxy>(ehrId, view, ehr.getPatient().getCaseNumber()));
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
}