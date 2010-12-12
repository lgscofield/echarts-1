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

import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ViewLabsEvent;
import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.EhrPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.rpc.AssignmentProxy;
import org.eastway.echarts.client.rpc.AssignmentRequest;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.EhrRequest;
import org.eastway.echarts.client.view.EHRView;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EhrActivity extends AbstractActivity implements EHRView.Presenter<EHRProxy> {

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
	private String caseNumber;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;

	public EhrActivity(EhrPlace place,
					   EchartsRequestFactory requestFactory,
					   EventBus eventBus,
					   PlaceController placeController,
					   EHRView<EHRProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.view = view;
	}

	protected void setData(EHRProxy ehr) {
		this.ehr = ehr;
		eventBus.fireEvent(new ChangeCurrentEhrEvent<EHRProxy>(ehr));
	}

	@Override
	public void viewPatientSummary() {
		placeController.goTo(new PatientSummaryPlace(caseNumber));
	}

	@Override
	public EHRProxy getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		placeController.goTo(new MessagePlace(caseNumber));
	}

	@Override
	public void viewDemographics() {
		placeController.goTo(new DemographicsPlace(caseNumber));
	}

	@Override
	public void viewReferral() {
		placeController.goTo(new ReferralPlace(caseNumber));
	}

	@Override
	public void viewAppointments() {
		placeController.goTo(new AppointmentPlace(caseNumber));
	}

	@Override
	public void viewDiagnoses() {
		placeController.goTo(new DiagnosisPlace(caseNumber));
	}

	@Override
	public void viewLinks() {
		placeController.goTo(new LinkPlace(caseNumber));
	}

	@Override
	public void viewAddresses() {
		placeController.goTo(new AddressPlace(caseNumber));
	}

	@Override
	public void viewMedications() {
		placeController.goTo(new MedicationPlace(caseNumber));
	}

	@Override
	public void viewTreatmentPlan() {
		placeController.goTo(new TreatmentPlanPlace(caseNumber));
	}

	@Override
	public void viewServiceHistory() {
		placeController.goTo(new ServiceHistoryPlace(caseNumber));
	}

	@Override
	public void viewARInfo() {
		placeController.goTo(new ARInfoPlace(caseNumber));
	}

	@Override
	public void viewLabs() {
		eventBus.fireEvent(new ViewLabsEvent<EHRProxy>(ehr.getPatient().getCaseNumber(), view));
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
		final EhrRequest ehrRequest = requestFactory.ehrRequest();
		AssignmentRequest assignmentRequest = requestFactory.assignmentRequest();
		new EHRFetcher().Run(ehrRequest, assignmentRequest, caseNumber, new Receiver<EhrActivity.EHRFetcher>() {
			@Override
			public void onSuccess(EHRFetcher response) {
				EHRProxy ehr = requestFactory.ehrRequest().edit(response.fetchedEHR);
				ehr.setAssignments(response.fetchedAssignments);
				setData(ehr);
			}
		});
	}
}