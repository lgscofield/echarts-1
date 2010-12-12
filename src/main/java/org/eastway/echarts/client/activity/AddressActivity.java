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
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.rpc.AddressProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.ui.AddressView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class AddressActivity extends AbstractActivity implements AddressView.Presenter<AddressProxy> {

	private AddressView<AddressProxy> view;
	private String caseNumber;
	private EchartsRequestFactory requestFactory;
	private List<ColumnDefinition<AddressProxy>> columnDefinitions;

	public AddressActivity(AddressPlace place,
			EchartsRequestFactory requestFactory,
			List<ColumnDefinition<AddressProxy>> columnDefinitions,
			AddressView<AddressProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.columnDefinitions = columnDefinitions;
		this.view = view;
	}

	private void fetchData() {
		requestFactory.addressRequest().findAddressesByCaseNumber(caseNumber).fire(new Receiver<List<AddressProxy>>() {
			@Override
			public void onSuccess(List<AddressProxy> response) {
				if (response != null)
					view.setRowData(response);
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
}
