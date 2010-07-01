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
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewMessagesEventHandler;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEventHandler;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.EHRPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.DashboardViewImpl;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController implements ValueChangeHandler<String> {
	private final EventBus eventBus;
	private HasWidgets container;
	private EHRServicesAsync ehrServices = GWT.<EHRServicesAsync>create(EHRServices.class);
	private RpcServicesAsync rpcServices = GWT.<RpcServicesAsync>create(RpcServices.class);
	private DashboardView<LinkedHashMap<String, Long>> dashboard = null;
	private EHRView<EHR> ehrView;
	private PatientSummaryView patientSummaryView;
	private Long ehrId;
	protected MessagesView messagesView;
	protected CachingDispatchAsync dispatch;
	private DashboardPresenter dashboardPresenter;

	@Inject
	public AppController(EventBus eventBus, CachingDispatchAsync dispatch) {
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		bind();
	}

	public void bind() {
		History.addValueChangeHandler(this);

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
	}

	public void doViewMessages(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		History.newItem("messages", false);
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
		History.newItem("summary", false);
		Presenter presenter = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, ehrServices, ehrId);
		presenter.go(view.getDisplayArea());
	}

	public void go(final HasWidgets container) {
		container.clear();
		dashboard = new DashboardViewImpl<LinkedHashMap<String, Long>>();
		dashboardPresenter = new DashboardPresenter(dashboard, eventBus, dispatch);
		dashboardPresenter.go(container);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		final String token = event.getValue();
		if (token != null) {
			if (token.equals("dashboard")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess() {
						if (dashboard == null) {
							dashboard = new DashboardViewImpl<LinkedHashMap<String, Long>>();
							new DashboardPresenter(dashboard, eventBus, dispatch)
								.go(container);
						} else {
							dashboard.setSelectedTab(0);
						}
					}
				});
			} else if (token.equals("list")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess() {
						if (ehrView == null)
							ehrView = new EHRViewImpl<EHR>();
						new EHRPresenter(ehrView, eventBus, ehrServices, ehrId)
							.go(null);
						dashboard.addTab(ehrView.asWidget(), new Long(ehrId).toString());
					}
				});
			} else if (token.equals("summary")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess() {
						if (patientSummaryView == null)
							patientSummaryView = new PatientSummaryView();
						new PatientSummaryPresenter(patientSummaryView, eventBus, ehrServices, ehrId)
							.go(ehrView.getDisplayArea());
					}
				});
			} else if (token.equals("messages")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable arg0) {
					}

					@Override
					public void onSuccess() {
						if (messagesView == null)
							messagesView = new MessagesView();
						new MessagesPresenter(messagesView, eventBus, rpcServices, ehrId)
							.go(ehrView.getDisplayArea());
					}
				});
			}
		}
	}
}
