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

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.request.ARInfoProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.ARInfoView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ARInfoActivity extends AbstractActivity implements ARInfoView.Presenter<ARInfoProxy> {

	private ARInfoView<ARInfoProxy> view;
	private String caseNumber;
	private EchartsRequestFactory requestFactory;
	private List<ColumnDefinition<ARInfoProxy>> columnDefinitions;

	public ARInfoActivity(ARInfoPlace place,
			EchartsRequestFactory requestFactory,
			List<ColumnDefinition<ARInfoProxy>> columnDefinitions,
			ARInfoView<ARInfoProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.columnDefinitions = columnDefinitions;
		this.view = view;
	}

	private void fetchData() {
		requestFactory.arinfoRequest().findARInfo(caseNumber).fire(new Receiver<ARInfoProxy>() {
			@Override
			public void onSuccess(ARInfoProxy response) {
				if (response != null) {
					view.setRowData(response);
				} else {
					handleFailure("No ARInfo found for case number: " + caseNumber);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.setColumnDefinitions(columnDefinitions);
		panel.setWidget(view.asWidget());
		fetchData();
	}

	private void handleFailure(String message) {
		view.setRowData(null);
		view.setError(message);
	}
}
