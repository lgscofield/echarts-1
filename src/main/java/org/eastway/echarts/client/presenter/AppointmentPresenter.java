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
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetAppointmentsResult;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppointmentPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Appointment {
		public void nextRecord();
	}

	private Display display;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetAppointments appointments;
	private String caseNumber;

	public AppointmentPresenter(Display display, EventBus eventBus, CachingDispatchAsync dispatch, String caseNumber) {
		this.display = display;
		this.dispatch = dispatch;
		this.eventBus = eventBus;
		this.caseNumber = caseNumber;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		appointments = new GetAppointments(EchartsUser.sessionId, caseNumber);
		fetchData();
	}

	public void fetchData() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.execute(appointments, new AsyncCallback<GetAppointmentsResult>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetAppointmentsResult result) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				setData(result);
			}
			
		});
	}

	public void setData(GetAppointmentsResult result) {
		for (Appointment a : result.getAppointments()) {
			display.setActivity(a.getActivity());
			display.setAppointmentDate(a.getAppointmentDate());
			//display.setCaseNumber(a.getCaseNumber());
			display.setEndTime(a.getEndTime());
			display.setId(a.getId());
			display.setLocation(a.getLocation());
			display.setNotes(a.getNotes());
			display.setPriority(a.getPriority());
			display.setStaff(a.getStaff());
			display.setStartTime(a.getStartTime());
			display.nextRecord();
		}
	}
}
