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

import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.EhrRequest;
import org.eastway.echarts.client.ui.PatientSummaryView;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PatientSummaryActivity extends AbstractActivity implements PatientSummaryView.Presenter {

	class EHRFetcher {
		EHRProxy fetchedEHR;
		List<AssignmentProxy> fetchedAssignments;

		void Run(final EhrRequest ehrRequest, final AssignmentRequest assignmentRequest, final String caseNumber, final Receiver<EHRFetcher> callback) {
			ehrRequest.findEHRByCaseNumber(caseNumber)
				.with("patient")
				.with("patient.caseStatus")
				.with("demographics")
				.with("demographics.gender")
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

						@Override
						public void onFailure(ServerFailure failure) {
							callback.onFailure(failure);
						}
					});
								
		}
	}

	private String caseNumber;
	private PatientSummaryView view;
	private EchartsRequestFactory requestFactory;
	private AcceptsOneWidget panel;

	public PatientSummaryActivity(PatientSummaryPlace place,
								  EchartsRequestFactory requestFactory,
								  PatientSummaryView view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.view = view;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		final EhrRequest ehrRequest = requestFactory.ehrRequest();
		AssignmentRequest assignmentRequest = requestFactory.assignmentRequest();
		new EHRFetcher().Run(ehrRequest, assignmentRequest, caseNumber, new Receiver<PatientSummaryActivity.EHRFetcher>() {
			@Override
			public void onSuccess(EHRFetcher response) {
				if (response != null) {
					EHRProxy ehr = requestFactory.ehrRequest().edit(response.fetchedEHR);
					ehr.setAssignments(response.fetchedAssignments);
					view.setValue(ehr);
					panel.setWidget(view);
				} else {
					handleFailure("No information found for case number: " + caseNumber);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
	}

	private void handleFailure(String message) {
		view.setValue(null);
		view.setError(message);
		panel.setWidget(view);
	}
}
