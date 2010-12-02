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
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.EHRProxy;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter implements Presenter, PatientSummaryView.Presenter<EHRProxy> {

	private String caseNumber;
	private PatientSummaryView<EHRProxy> view;
	private EchartsRequestFactory requestFactory;

	public PatientSummaryPresenter(PatientSummaryView<EHRProxy> view,
			List<ColumnDefinition<EHRProxy>> columnDefinitions, EventBus eventBus, EchartsRequestFactory requestFactory, String caseNumber) {
		this.view = view;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.caseNumber = caseNumber;
		this.requestFactory = requestFactory;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		Request<EHRProxy> request = requestFactory.ehrRequest().findEHRByCaseNumber(caseNumber)
			.with("patient")
			.with("patient.caseStatus")
			.with("demographics")
			.with("demographics.gender");
		request.fire(new Receiver<EHRProxy>() {
			@Override
			public void onSuccess(EHRProxy ehr) {
				view.setRowData(ehr);
			}
		});
	}
}
