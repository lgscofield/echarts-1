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

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.AppointmentView;
import org.eastway.echarts.client.ui.CurrentEhrWidget;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class AppointmentActivity extends AbstractActivity implements AppointmentView.Presenter<AppointmentProxy> {

	private AppointmentView<AppointmentProxy> view;
	private String caseNumber;
	private int maxResults = 0;
	private int startRecord = 0;
	private EchartsRequestFactory requestFactory;
	private List<ColumnDefinition<AppointmentProxy>> columnDefinitions;

	public AppointmentActivity(AppointmentPlace place,
			EchartsRequestFactory requestFactory,
			List<ColumnDefinition<AppointmentProxy>> columnDefinitions,
			AppointmentView<AppointmentProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.columnDefinitions = columnDefinitions;
		this.view = view;
	}

	private int record = 0;
	private long rowCount = 0;

	public void fetchData() {
		requestFactory.appointmentRequest().findAppointmentEntriesByCaseNumber(caseNumber)
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
		view.setPresenter(this);
		view.setColumnDefinitions(columnDefinitions);
		panel.setWidget(view.asWidget());
		fetchData();
		CurrentEhrWidget.instance().setEhr(caseNumber, requestFactory);
	}
}
