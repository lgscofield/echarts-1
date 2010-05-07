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

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.events.OpenEhrEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientListPresenter extends Presenter<PatientListPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setData(LinkedHashMap<String, Long> data);

		HasClickHandlers getTable();

		String getClickedRow(ClickEvent event);
	}

	private LinkedHashMap<String, Long> data;
	private PatientServicesAsync patientServices;

	public PatientListPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientServices) {
		super(display, eventBus);
		this.patientServices = patientServices;
	}

	private void bind() {
		display.getTable().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String selected = display.getClickedRow(event);

				if (selected != null) {
					long ehrId = getData().get(selected);
					eventBus.fireEvent(new OpenEhrEvent(ehrId));
				}
			}
		});
	}

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
		patientServices.getPatientList(UserImpl.getSessionId(), callback);
	}

	private void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}

	private LinkedHashMap<String, Long> getData() {
		return this.data;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchPatientList();
		bind();
	}
}
