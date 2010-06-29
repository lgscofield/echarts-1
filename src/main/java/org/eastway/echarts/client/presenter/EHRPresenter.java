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

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class EHRPresenter implements Presenter, EHRView.Presenter<EHR> {

	private EHR ehr;
	private EHRServicesAsync ehrServices;
	private EHRView<EHR> view;
	private HandlerManager eventBus;
	private long ehrId;

	public EHRPresenter(EHRView<EHR> view, HandlerManager eventBus, EHRServicesAsync ehrServices, long ehrId) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.ehrServices = ehrServices;
		this.ehrId = ehrId;
	}

	private void fetchEhr(long ehrId) {
		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR ehr) {
				setEhr(ehr);
				eventBus.fireEvent(new ChangeCurrentEhrEvent(getEhr()));
			}
		};
		ehrServices.getEhr(ehrId, Cookies.getCookie("sessionId"), callback);
	}

	private void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	@Override
	public void go(final HasWidgets container) {
		fetchEhr(ehrId);
	}

	@Override
	public void onItemSelected(String selected) {
		if (selected == "patientSummary") {
			eventBus.fireEvent(new ViewPatientSummaryEvent(ehrId));
		}
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}
}