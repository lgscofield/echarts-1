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
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.rpc.DiagnosisProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.DiagnosisView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DiagnosisActivity extends AbstractActivity implements DiagnosisView.Presenter<DiagnosisProxy> {

	private DiagnosisView<DiagnosisProxy> view;
	private String caseNumber;
	private EchartsRequestFactory requestFactory;
	private List<ColumnDefinition<DiagnosisProxy>> columnDefinitions;

	public DiagnosisActivity(DiagnosisPlace place,
			EchartsRequestFactory requestFactory,
			List<ColumnDefinition<DiagnosisProxy>> columnDefinitions,
			DiagnosisView<DiagnosisProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.columnDefinitions = columnDefinitions;
		this.view = view;
	}

	public void fetchData() {
		requestFactory.diagnosisRequest().findDiagnosesByCaseNumber(caseNumber)
			.with("axis1A")
			.with("axis1B")
			.with("axis1C")
			.with("axis1D")
			.with("axis1E")
			.with("axis2A")
			.with("axis2B")
			.with("axis2C")
				.fire(new Receiver<List<DiagnosisProxy>>() {
			@Override
			public void onSuccess(List<DiagnosisProxy> response) {
				if (response != null)
					setData(response);
			}
		});
	}

	private void setData(List<DiagnosisProxy> response) {
		view.setRowData(response);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.setColumnDefinitions(columnDefinitions);
		panel.setWidget(view.asWidget());
		fetchData();
	}
}
