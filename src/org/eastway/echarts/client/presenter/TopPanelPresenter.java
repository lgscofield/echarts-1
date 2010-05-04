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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.events.ChangeCurrentPatientEvent;
import org.eastway.echarts.client.events.ChangeCurrentPatientEventHandler;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public class TopPanelPresenter extends Presenter<TopPanelPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setData(LinkedHashMap<String, Long> data);

		HasClickHandlers getSearchButton();

		HasText getSuggestBox();

		HasClickHandlers getLogoutButton();

		void setCurrentPatientData(ArrayList<String[]> data);
	}

	private LinkedHashMap<String, Long> data;

	public TopPanelPresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		fetchData();
		bind();
	}

	private void bind() {
		display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new OpenEhrEvent(data.get(display.getSuggestBox().getText())));
				display.getSuggestBox().setText("");
			}
		});
		display.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent());
			}
		});
		eventBus.addHandler(ChangeCurrentPatientEvent.TYPE, new ChangeCurrentPatientEventHandler() {
			@Override
			public void onChangeCurrentPatient(ChangeCurrentPatientEvent event) {
				setCurrentPatientData(event.getPatient());
			}
		});
	}

	private void setCurrentPatientData(PatientDTO patient) {
		if (patient == null) {
			display.setCurrentPatientData(null);
			return;
		}
		ArrayList<String[]> data = new ArrayList<String[]>();

		data.add(new String[] {"Name",patient.getName()});
		data.add(new String[] {"DOB",new Long(patient.getDemographics().getDob().getTime()).toString()});

		Long age = (new Date().getTime() - patient.getDemographics().getDob().getTime()) / (3600*24*365) / 1000;

		data.add(new String[] {"Age",  age.toString() });
		data.add(new String[] {"Case Status",patient.getCaseStatus()});
		data.add(new String[] {"Provider",UserImpl.getStaffName()});
		data.add(new String[] {"SSN",patient.getSsn()});
		display.setCurrentPatientData(data);
	}

	public void fetchData() {
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
		Rpc.singleton().getPatientList(UserImpl.getSessionId(), callback);
	}

	protected void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
