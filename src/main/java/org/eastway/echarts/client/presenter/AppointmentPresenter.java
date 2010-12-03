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

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.shared.AppointmentProxy;
import org.eastway.echarts.shared.GetAppointments;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppointmentPresenter implements Presenter, AppointmentView.Presenter<AppointmentProxy> {

	private EchartsRequestFactory requestFactory;
	private GetAppointments action;
	private AppointmentView<AppointmentProxy> view;

	public AppointmentPresenter(AppointmentView<AppointmentProxy> view, List<ColumnDefinition<AppointmentProxy>> columnDefinitions, EventBus eventBus, EchartsRequestFactory requestFactory, GetAppointments action) {
		this.view = view;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.requestFactory = requestFactory;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private int record = 0;
	private long rowCount = 0;

	public void fetchData() {
		requestFactory.appointmentRequest().findAppointmentEntriesByCaseNumber(action.getCaseNumber())
				.fire(new Receiver<List<AppointmentProxy>>() {
			@Override
			public void onSuccess(List<AppointmentProxy> response) {
				rowCount = response.size();
				view.setRowData(response, action.getStartRecord(), action.getMaxResults(), rowCount);
			}
		});
//		dispatch.execute(action, new EchartsCallback<GetAppointmentsResult>(eventBus) {
//			@Override
//			protected void handleFailure(Throwable caught) {
//			}
//
//			@Override
//			protected void handleSuccess(GetAppointmentsResult result) {
//				rowCount = result.getRowCount();
//				view.setRowData(result.getAppointments(), action.getStartRecord(), action.getMaxResults(), rowCount);
//			}
//		});
	}

	@Override
	public void getOlder() {
		record += action.getMaxResults();
		if (record > rowCount) {
			record -= action.getMaxResults();
			return;				
		}
		action.setStartRecord(record);
		fetchData();
	}

	@Override
	public void getNewer() {
		record -= action.getMaxResults();
		if (record < 0) {
			record = 0;
			return;
		}
		action.setStartRecord(record);
		fetchData();
	}

	@Override
	public void getNewest() {
		action.setStartRecord(0);
		record = 0;
		fetchData();
	}

	@Override
	public void getOldest() {
		action.setStartRecord((int)rowCount - (int)rowCount % action.getMaxResults());
		record = action.getStartRecord();
		fetchData();
	}
}
