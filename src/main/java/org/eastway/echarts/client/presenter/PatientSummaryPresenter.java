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
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetPatientSummaryResult;

import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter implements Presenter, PatientSummaryView.Presenter<GetPatientSummaryResult> {

	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetPatientSummary action;
	private PatientSummaryView<GetPatientSummaryResult> view;

	public PatientSummaryPresenter(PatientSummaryView<GetPatientSummaryResult> view,
			List<ColumnDefinition<GetPatientSummaryResult>> columnDefinitions, EventBus eventBus, CachingDispatchAsync dispatch, GetPatientSummary action) {
		this.view = view;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		dispatch.executeWithCache(action, new EchartsCallback<GetPatientSummaryResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetPatientSummaryResult result) {
				view.setRowData(result);
			}
		});
	}
}
