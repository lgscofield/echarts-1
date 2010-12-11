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

import org.eastway.echarts.client.EchartsClientFactory;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.rpc.DemographicsProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.DemographicsView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;

public class DemographicsActivity extends AbstractActivity implements Presenter, DemographicsView.Presenter<DemographicsProxy> {

	private DemographicsView<DemographicsProxy> view;
	private String caseNumber;
	private EchartsClientFactory clientFactory;

	public DemographicsActivity(DemographicsView<DemographicsProxy> view,
			List<ColumnDefinition<DemographicsProxy>> columnDefinitions, EchartsRequestFactory requestFactory, String caseNumber) {
		this.view = view;
		//this.requestFactory = requestFactory;
		this.caseNumber = caseNumber;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
	}

	public DemographicsActivity(DemographicsPlace place,
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

	private void fetchData() {
		Request<DemographicsProxy> request = clientFactory.getRequestFactory().demographicsRequest().findDemographics(caseNumber)
			.with("employment")
			.with("maritalStatus")
			.with("educationLevel")
			.with("educationType")
			.with("race");
		request.fire(new Receiver<DemographicsProxy>() {
			@Override
			public void onSuccess(DemographicsProxy response) {
				if (response != null)
					setData(response);
			}
		});
	}

	public void setData(DemographicsProxy demographics) {
		view.setRowData(demographics);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getDemographicsView();
		view.setPresenter(this);
		view.setColumnDefinitions(clientFactory.getDemographicsColumnDefinitions());
		panel.setWidget(view.asWidget());
		fetchData();
	}
}
