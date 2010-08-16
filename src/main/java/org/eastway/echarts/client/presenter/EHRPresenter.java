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

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.EchartsUser;
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
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetPatientSummaryResult;
import org.eastway.echarts.shared.GetReferral;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.user.client.ui.HasWidgets;

public class EHRPresenter implements Presenter, EHRView.Presenter<EHR> {

	private EHR ehr;
	private String caseNumber;
	private EHRView<EHR> view;
	private EventBus eventBus;
	private long ehrId;
	private GetAppointments appointments = null;
	private GetDemographics demographics = null;
	private GetPatientSummary patientSummary = null;
	private GetReferral referral = null;
	private GetDiagnoses diagnoses;
	private GetLinks links = null;
	private GetMessages messages = null;
	private GetAddresses addresses = null;
	private GetContacts contacts = null;
	private GetMedications medications = null;
	private CachingDispatchAsync dispatch = null;

	public EHRPresenter(EHRView<EHR> view, EventBus eventBus, CachingDispatchAsync dispatch, String caseNumber) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.caseNumber = caseNumber;
	}

	private void fetchData() {
		dispatch.executeWithCache(patientSummary, new EchartsCallback<GetPatientSummaryResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetPatientSummaryResult result) {
				setData(result);
			}
		});
	}

	protected void setData(GetPatientSummaryResult result) {
		EHR ehr = new EHRDTO();
		Demographics demographics = new DemographicsDTO();
		Patient patient = new PatientDTO();
		Code caseStatus = new CodeDTO();
		ehr.setDemographics(demographics);
		ehr.setSubject(patient);
		patient.setFirstName(result.getFirstName());
		patient.setMiddleInitial(result.getMiddleInitial());
		patient.setLastName(result.getLastName());
		patient.setSuffix(result.getSuffix());
		demographics.setDob(result.getDob());
		caseStatus.setDescriptor(result.getCaseStatus());
		patient.setCaseStatus(caseStatus);
		patient.setSsn(result.getSsn());
		this.ehr = ehr;
		eventBus.fireEvent(new ChangeCurrentEhrEvent(ehr));
	}

	private void setActions() {
		appointments = new GetAppointments(EchartsUser.sessionId, caseNumber);
		demographics = new GetDemographics(EchartsUser.sessionId, caseNumber);
		patientSummary = new GetPatientSummary(EchartsUser.sessionId, caseNumber);
		referral = new GetReferral(EchartsUser.sessionId, caseNumber);
		diagnoses = new GetDiagnoses(EchartsUser.sessionId, caseNumber);
		links = new GetLinks(EchartsUser.sessionId, caseNumber);
		messages = new GetMessages(EchartsUser.sessionId, caseNumber);
		addresses = new GetAddresses(EchartsUser.sessionId, caseNumber);
		contacts = new GetContacts(EchartsUser.sessionId, caseNumber);
		medications = new GetMedications(EchartsUser.sessionId, caseNumber);
	}

	@Override
	public void go(final HasWidgets container) {
		setActions();
		fetchData();
	}

	@Override
	public void viewPatientSummary() {
		eventBus.fireEvent(new ViewPatientSummaryEvent(view, patientSummary));
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		eventBus.fireEvent(new ViewMessagesEvent(caseNumber, view, messages));
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
		eventBus.fireEvent(new ViewAppointmentsEvent(caseNumber, view, appointments));
	}

	@Override
	public void viewDiagnoses() {
		eventBus.fireEvent(new ViewDiagnosesEvent(caseNumber, view, diagnoses));
	}

	@Override
	public void viewLinks() {
		eventBus.fireEvent(new ViewLinksEvent(caseNumber, view, links));
	}

	@Override
	public void viewAddresses() {
		eventBus.fireEvent(new ViewAddressesEvent(caseNumber, view, addresses));
	}

	@Override
	public void viewContacts() {
		eventBus.fireEvent(new ViewContactsEvent(caseNumber, view, contacts));
	}

	@Override
	public void viewMedications() {
		eventBus.fireEvent(new ViewMedicationsEvent(caseNumber, view, medications));
	}
}