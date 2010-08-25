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

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetAppointmentsResult;

import com.google.gwt.user.client.ui.HasWidgets;

public class AppointmentPresenter implements Presenter, AppointmentView.Presenter<Appointment> {

	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetAppointments action;
	private AppointmentView<Appointment> view;

	public AppointmentPresenter(AppointmentView<Appointment> view, List<ColumnDefinition<Appointment>> columnDefinitions, EventBus eventBus, CachingDispatchAsync dispatch, GetAppointments action) {
		this.view = view;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.dispatch = dispatch;
		this.eventBus = eventBus;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private int record = 0;

	public void fetchData() {
		dispatch.execute(action, new EchartsCallback<GetAppointmentsResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetAppointmentsResult result) {
				view.setRowData(result.getAppointments());
			}
		});
	}

	@Override
	public void getNext() {
		record += action.getMaxResults() + 1;
		action.setStartRecord(record);
		fetchData();
	}

	@Override
	public void getPrevious() {
		int firstRecord = record - action.getMaxResults();
		if (firstRecord < 0)
			firstRecord = 0;
		action.setStartRecord(firstRecord);
		fetchData();
	}
}
