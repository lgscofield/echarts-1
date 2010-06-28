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
import java.util.List;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ChangeCurrentEhrEventHandler;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public class TopPanelPresenter extends Presenter<TopPanelPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setData(LinkedHashMap<String, Long> data);

		HasClickHandlers getSearchButton();

		HasText getSuggestBox();

		HasClickHandlers getLogoutButton();

		void setCurrentEhrData(ArrayList<String[]> data);
	}

	private LinkedHashMap<String, Long> data;
	private PatientServicesAsync patientServices;

	public TopPanelPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientServices) {
		super(display, eventBus);
		this.patientServices = patientServices;
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
		eventBus.addHandler(ChangeCurrentEhrEvent.TYPE, new ChangeCurrentEhrEventHandler() {
			@Override
			public void onChangeCurrentEhr(ChangeCurrentEhrEvent event) {
				setCurrentEhrData(event.getEhr());
			}
		});
	}

	private void setCurrentEhrData(EHR ehr) {
		if (ehr == null) {
			display.setCurrentEhrData(null);
			return;
		}
		ArrayList<String[]> data = new ArrayList<String[]>();
		Patient patient = ehr.getSubject();
		Demographics demographics = ehr.getDemographics();
		data.add(new String[] {"Name",patient.getName()});
		data.add(new String[] {"DOB",new Long(demographics.getDob().getTime()).toString()});

		Long age = (new Date().getTime() - demographics.getDob().getTime()) / (3600*24*365) / 1000;

		data.add(new String[] {"Age",  age.toString() });
		data.add(new String[] {"Case Status",patient.getCaseStatus().getDescriptor() });
		data.add(new String[] {"Provider", getProvider(ehr.getAssignments()) });
		data.add(new String[] {"SSN",patient.getSsn()});
		display.setCurrentEhrData(data);
	}

	private String getProvider(List<Assignment> assignments) {
		for (Assignment assignment : assignments) {
			if (!assignment.getService().matches("S CS"))
				continue;
			else
				return assignment.getStaffName();
		}

		for (Assignment assignment : assignments)
			return assignment.getStaffName();
		return null;
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
		patientServices.getPatientList(Cookies.getCookie("sessionId"), Cookies.getCookie("staff_id"), callback);
	}

	protected void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
