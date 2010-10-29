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
import org.eastway.echarts.client.view.DiagnosisView;
import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetDiagnosesResult;

import com.google.gwt.user.client.ui.HasWidgets;

public class DiagnosisPresenter implements Presenter, DiagnosisView.Presenter<Diagnosis> {

	private DiagnosisView<Diagnosis> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetDiagnoses action;

	public DiagnosisPresenter(DiagnosisView<Diagnosis> view, List<ColumnDefinition<Diagnosis>> columnDefinitions, EventBus eventBus,
			CachingDispatchAsync dispatch, GetDiagnoses action) {
		this.view = view;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	public void fetchData() {
		dispatch.executeWithCache(action, new EchartsCallback<GetDiagnosesResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetDiagnosesResult result) {
				setData(result);				
			}
		});
	}

	private void setData(GetDiagnosesResult result) {
		view.setRowData(result.getDiagnoses());
	}
}
