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

import org.eastway.echarts.client.EchartsClientFactory;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.rpc.AppointmentProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.shared.GetAppointments;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppointmentActivity extends AbstractActivity implements Presenter, AppointmentView.Presenter<AppointmentProxy> {

	private AppointmentView<AppointmentProxy> view;
	private EchartsClientFactory clientFactory;
	private String caseNumber;
	private int maxResults = 0;
	private int startRecord = 0;

	public AppointmentActivity(AppointmentView<AppointmentProxy> view, List<ColumnDefinition<AppointmentProxy>> columnDefinitions, EventBus eventBus, EchartsRequestFactory requestFactory, GetAppointments action) {
		this.view = view;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
	}

	public AppointmentActivity(AppointmentPlace place,
			EchartsClientFactory clientFactory) {
		this.caseNumber = place.getCaseNumber();
		this.clientFactory = clientFactory;
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
		clientFactory.getRequestFactory().appointmentRequest().findAppointmentEntriesByCaseNumber(caseNumber)
				.fire(new Receiver<List<AppointmentProxy>>() {
			@Override
			public void onSuccess(List<AppointmentProxy> response) {
				if (response != null) {
					rowCount = response.size();
					view.setRowData(response, startRecord, maxResults, rowCount);
				}
			}
		});
	}

	@Override
	public void getOlder() {
		record += maxResults;
		if (record > rowCount) {
			record -= maxResults;
			return;				
		}
		startRecord = record;
		fetchData();
	}

	@Override
	public void getNewer() {
		record -= maxResults;
		if (record < 0) {
			record = 0;
			return;
		}
		startRecord = record;
		fetchData();
	}

	@Override
	public void getNewest() {
		startRecord = 0;
		record = 0;
		fetchData();
	}

	@Override
	public void getOldest() {
		startRecord = (int)rowCount - (int)rowCount % maxResults;
		record = startRecord;
		fetchData();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getAppointmentView();
		view.setPresenter(this);
		view.setColumnDefinitions(clientFactory.getAppointmentColumnDefinitions());
		panel.setWidget(view.asWidget());
		fetchData();
	}
}
