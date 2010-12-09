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
import org.eastway.echarts.client.rpc.ARInfoProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.ARInfoView;
import org.eastway.echarts.shared.GetARInfo;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class ARInfoPresenter implements Presenter, ARInfoView.Presenter<ARInfoProxy> {

	private ARInfoView<ARInfoProxy> view;
	private EchartsRequestFactory requestFactory;
	private GetARInfo action;

	public ARInfoPresenter(ARInfoView<ARInfoProxy> view, List<ColumnDefinition<ARInfoProxy>> columnDefinitions, EchartsRequestFactory requestFactory, GetARInfo action) {
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

	private void fetchData() {
		requestFactory.arinfoRequest().findARInfo(action.getCaseNumber()).fire(new Receiver<ARInfoProxy>() {
			@Override
			public void onSuccess(ARInfoProxy response) {
				if (response != null)
					view.setRowData(response);
			}
		});
	}
}
