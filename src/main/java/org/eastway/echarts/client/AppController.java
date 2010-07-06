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
package org.eastway.echarts.client;

import java.util.LinkedHashMap;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenEhrEventHandler;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEventHandler;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEventHandler;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewMessagesEventHandler;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEventHandler;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.events.ViewReferralEventHandler;
import org.eastway.echarts.client.presenter.AppointmentPresenter;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.DemographicsPresenter;
import org.eastway.echarts.client.presenter.EHRPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.ReferralPresenter;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.DashboardViewImpl;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.client.view.ReferralView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.core.client.GWT;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController {
	private final EventBus eventBus;
	private HasWidgets container;
	private EHRServicesAsync ehrServices = GWT.<EHRServicesAsync>create(EHRServices.class);
	private RpcServicesAsync rpcServices = GWT.<RpcServicesAsync>create(RpcServices.class);
	private ReferralServicesAsync referralServices = GWT.<ReferralServicesAsync>create(ReferralServices.class);
	private DashboardView<LinkedHashMap<String, Long>> dashboard = null;
	private EHRView<EHR> ehrView;
	private PatientSummaryView patientSummaryView;
	private Long ehrId;
	protected MessagesView messagesView;
	protected CachingDispatchAsync dispatch;
	private DashboardPresenter dashboardPresenter;
	protected DemographicsPresenter demographicsPresenter;

	@Inject
	public AppController(EventBus eventBus, CachingDispatchAsync dispatch) {
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		bind();
	}

	public void bind() {
		eventBus.addHandler(OpenEhrEvent.TYPE, new OpenEhrEventHandler() {
			@Override
			public void onOpenEhr(OpenEhrEvent event) {
				doViewEhr(event.getId(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewPatientSummaryEvent.TYPE, new ViewPatientSummaryEventHandler() {
			@Override
			public void onViewPatientSummary(ViewPatientSummaryEvent event) {
				doViewPatientSummary(event.getId(), event.getView());
			}
		});
		eventBus.addHandler(ViewMessagesEvent.TYPE, new ViewMessagesEventHandler() {
			@Override
			public void onViewMessages(ViewMessagesEvent event) {
				doViewMessages(event.getId(), event.getView());
			}
		});
		eventBus.addHandler(ViewReferralEvent.TYPE, new ViewReferralEventHandler() {
			@Override
			public void onViewReferral(ViewReferralEvent event) {
				doViewReferral(event.getId(), event.getView());
			}
		});
		eventBus.addHandler(ViewAppointmentsEvent.TYPE, new ViewAppointmentsEventHandler() {
			@Override
			public void onViewAppointments(ViewAppointmentsEvent event) {
				doViewAppointments(event.getCaseNumber(), event.getView());
			}
		});
		eventBus.addHandler(RequestEvent.TYPE, new RequestEvent.Handler() {
			// Only show loading status if a request isn't serviced in 250ms.
			private static final int LOADING_TIMEOUT = 250;
			public void onRequestEvent(RequestEvent requestEvent) {
				if (requestEvent.getState() == State.SENT) {
					dashboard.getMole().showDelayed(LOADING_TIMEOUT);
				} else {
					dashboard.getMole().hide();
				}
			}
		});
		eventBus.addHandler(ViewDemographicsEvent.TYPE, new ViewDemographicsEventHandler() {
			@Override
			public void onViewDemographics(ViewDemographicsEvent event) {
				doViewDemographics(event.getId(), event.getView());
			}
		});
	}

	public void doViewAppointments(String caseNumber, EHRView<EHR> view) {
		Presenter presenter = new AppointmentPresenter(new AppointmentView(), eventBus, dispatch, caseNumber);
		presenter.go(view.getDisplayArea());
	}

	public void doViewReferral(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		Presenter presenter = new ReferralPresenter(new ReferralView(), eventBus, referralServices, ehrId);
		presenter.go(view.getDisplayArea());
	}

	public void doViewMessages(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		Presenter presenter = new MessagesPresenter(new MessagesView(), eventBus, rpcServices, ehrId);
		presenter.go(view.getDisplayArea());
	}

	public void doViewEhr(long ehrId, String caseNumber) {
		this.ehrId = ehrId;
		ehrView = new EHRViewImpl<EHR>();
		Presenter presenter = new EHRPresenter(ehrView, eventBus, ehrServices, ehrId);
		presenter.go(null);
		dashboard.addTab(ehrView.asWidget(), caseNumber);
	}

	public void doViewPatientSummary(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		Presenter presenter = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, ehrServices, ehrId);
		presenter.go(view.getDisplayArea());
	}

	public void doViewDemographics(final long ehrId, final EHRView<EHR> view) {
		this.ehrId = ehrId;
		demographicsPresenter = new DemographicsPresenter(new DemographicsView(), eventBus, dispatch, ehrId);
		demographicsPresenter.go(view.getDisplayArea());
	}

	public void go(final HasWidgets container) {
		container.clear();
		dashboard = new DashboardViewImpl<LinkedHashMap<String, Long>>();
		dashboardPresenter = new DashboardPresenter(dashboard, eventBus, dispatch);
		dashboardPresenter.go(container);
	}
}
