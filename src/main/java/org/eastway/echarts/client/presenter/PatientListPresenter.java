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

import org.eastway.echarts.client.EchartsUser;
//import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.view.PatientListView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientListPresenter implements PatientListView.Presenter<LinkedHashMap<String, Long>> {
	private LinkedHashMap<String, Long> data;
	private PatientListView<LinkedHashMap<String, Long>> display;
	//private PatientServicesAsync patientServices;
	private HandlerManager eventBus;

//	public PatientListPresenter(PatientListView<LinkedHashMap<String, Long>> display, HandlerManager eventBus, PatientServicesAsync patientServices) {
//		this.patientServices = patientServices;
//		this.display = display;
//		this.display.setPresenter(this);
//		this.eventBus = eventBus;
//	}

	public void fetchPatientList() {
		AsyncCallback<LinkedHashMap<String, Long>> callback = new AsyncCallback<LinkedHashMap<String, Long>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashMap<String, Long> data) {
				display.setData(data);
				setData(data);
			}
		};
		//patientServices.getPatientList(EchartsUser.sessionId, EchartsUser.staffId, callback);
	}

	private void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}

	private LinkedHashMap<String, Long> getData() {
		return this.data;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void onOpenChartClicked() {
		fetchPatientList();
	}

	@Override
	public void onItemSelected(String row) {
		if (row != null) {
			long ehrId = getData().get(row);
			eventBus.fireEvent(new OpenEhrEvent(ehrId, row));
		}
	}
}
