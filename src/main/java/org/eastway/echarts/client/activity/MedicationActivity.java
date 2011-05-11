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

import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.MedicationProxy;
import org.eastway.echarts.client.ui.MedicationView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MedicationActivity extends AbstractActivity implements MedicationView.Presenter<MedicationProxy> {

	private String caseNumber;
	private MedicationView<MedicationProxy> view;
	private EchartsRequestFactory requestFactory;
	private AcceptsOneWidget panel;

	public MedicationActivity(MedicationPlace place,
							  EchartsRequestFactory requestFactory,
							  MedicationView<MedicationProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.view = view;
	}

	private void fetchData() {
		requestFactory.medicationRequest().findMedicationsByCaseNumber(caseNumber)
				.with("lastEditBy")
				.fire(new Receiver<List<MedicationProxy>>() {
			@Override
			public void onSuccess(List<MedicationProxy> response) {
				if (response != null && !response.isEmpty()) {
					view.setRowData(response);
					panel.setWidget(view);
				} else {
					handleFailure("No medication data found for case number: " + caseNumber);
				}
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		fetchData();
	}

	private void handleFailure(String message) {
		view.setRowData(null);
		view.setError(message);
		panel.setWidget(view);
	}
}
