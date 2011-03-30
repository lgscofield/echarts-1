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

import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.request.AppointmentDataProvider;
import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.AppointmentView;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class AppointmentActivity extends AbstractActivity implements AppointmentView.Presenter<AppointmentProxy> {

	private AppointmentView<AppointmentProxy> view;
	private String caseNumber;
	private AppointmentDataProvider dataProvider;

	public AppointmentActivity(AppointmentPlace place,
			EchartsRequestFactory requestFactory,
			AppointmentView<AppointmentProxy> view,
			AppointmentDataProvider dataProvider) {
		this.view = view;
		this.view.setPresenter(this);
		this.caseNumber = place.getCaseNumber();
		this.dataProvider = dataProvider;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		dataProvider.setCaseNumber(caseNumber);
		view.setStartDate(new Date());
		dataProvider.setStartDate(view.getStartDate());
		view.setDataProvider(dataProvider);
		panel.setWidget(view);
	}

	@Override
	public void onStop() {
		view.reset();
	}

	@Override
	public void setDateFilter(Date value) {
		dataProvider.setStartDate(value);
	}
}
