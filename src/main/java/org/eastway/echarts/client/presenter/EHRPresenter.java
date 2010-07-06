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

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class EHRPresenter implements Presenter, EHRView.Presenter<EHR> {

	private EHR ehr;
	private EHRServicesAsync ehrServices;
	private EHRView<EHR> view;
	private EventBus eventBus;
	private long ehrId;

	public EHRPresenter(EHRView<EHR> view, EventBus eventBus, EHRServicesAsync ehrServices, long ehrId) {
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
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				setEhr(ehr);
				eventBus.fireEvent(new ChangeCurrentEhrEvent(getEhr()));
			}
		};
		eventBus.fireEvent(new RequestEvent(State.SENT));
		ehrServices.getEhr(ehrId, EchartsUser.sessionId, callback);
	}

	private void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	@Override
	public void go(final HasWidgets container) {
		fetchEhr(ehrId);
	}

	@Override
	public void viewPatientSummary() {
		eventBus.fireEvent(new ViewPatientSummaryEvent(ehrId, view));
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public void viewMessages() {
		eventBus.fireEvent(new ViewMessagesEvent(ehrId, view));
	}

	@Override
	public void viewDemographics() {
		eventBus.fireEvent(new ViewDemographicsEvent(ehrId, view));
	}

	@Override
	public void viewReferral() {
		eventBus.fireEvent(new ViewReferralEvent(ehrId, view));
	}

	@Override
	public void viewAppointments() {
		eventBus.fireEvent(new ViewAppointmentsEvent(ehr.getSubject().getCaseNumber(), view));
	}
}