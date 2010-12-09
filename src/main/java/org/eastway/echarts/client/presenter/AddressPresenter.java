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

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.AddressProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.AddressView;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddressPresenter implements Presenter, AddressView.Presenter<AddressProxy> {

	private AddressView<AddressProxy> view;
	private EchartsRequestFactory requestFactory;
	private String caseNumber;

	public AddressPresenter(AddressView<AddressProxy> view, List<ColumnDefinition<AddressProxy>> columnDefinitions, EchartsRequestFactory requestFactory, String caseNumber) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.caseNumber = caseNumber;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
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
}
