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

import org.eastway.echarts.client.common.TicklerColumnDefinitionsFactory;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenEhrEventHandler;
import org.eastway.echarts.client.events.ViewAddressesEvent;
import org.eastway.echarts.client.events.ViewAddressesEventHandler;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEventHandler;
import org.eastway.echarts.client.events.ViewContactsEvent;
import org.eastway.echarts.client.events.ViewContactsEventHandler;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEventHandler;
import org.eastway.echarts.client.events.ViewDiagnosesEvent;
import org.eastway.echarts.client.events.ViewDiagnosesEventHandler;
import org.eastway.echarts.client.events.ViewLinksEvent;
import org.eastway.echarts.client.events.ViewLinksEventHandler;
import org.eastway.echarts.client.events.ViewMedicationsEvent;
import org.eastway.echarts.client.events.ViewMedicationsEventHandler;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewMessagesEventHandler;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEventHandler;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.events.ViewReferralEventHandler;
import org.eastway.echarts.client.events.ViewTicklerEvent;
import org.eastway.echarts.client.events.ViewTicklerEventHandler;
import org.eastway.echarts.client.presenter.AddressPresenter;
import org.eastway.echarts.client.presenter.AppointmentPresenter;
import org.eastway.echarts.client.presenter.ContactPresenter;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.DemographicsPresenter;
import org.eastway.echarts.client.presenter.DiagnosisPresenter;
import org.eastway.echarts.client.presenter.EHRPresenter;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.client.presenter.MedicationPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.ReferralPresenter;
import org.eastway.echarts.client.presenter.TicklerPresenter;
import org.eastway.echarts.client.view.AddressView;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.client.view.ContactView;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.DashboardViewImpl;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.DiagnosisView;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.client.view.LinkView;
import org.eastway.echarts.client.view.MedicationView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.client.view.ReferralView;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.client.view.TicklerViewImpl;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetReferral;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController {
	private final EventBus eventBus;
	private DashboardView<LinkedHashMap<String, Long>> dashboard = null;
	private EHRView<EHR> ehrView;
	protected MessagesView messagesView;
	protected CachingDispatchAsyncImpl dispatch;
	private DashboardPresenter dashboardPresenter;

	@Inject
	public AppController(EventBus eventBus, CachingDispatchAsyncImpl dispatch) {
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		bind();
	}

	public void bind() {
		eventBus.addHandler(OpenEhrEvent.TYPE, new OpenEhrEventHandler() {
			@Override
			public void onOpenEhr(OpenEhrEvent event) {
				doViewEhr(event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewPatientSummaryEvent.TYPE, new ViewPatientSummaryEventHandler() {
			@Override
			public void onViewPatientSummary(ViewPatientSummaryEvent event) {
				doViewPatientSummary(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewMessagesEvent.TYPE, new ViewMessagesEventHandler() {
			@Override
			public void onViewMessages(ViewMessagesEvent event) {
				doViewMessages(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewReferralEvent.TYPE, new ViewReferralEventHandler() {
			@Override
			public void onViewReferral(ViewReferralEvent event) {
				doViewReferral(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewAppointmentsEvent.TYPE, new ViewAppointmentsEventHandler() {
			@Override
			public void onViewAppointments(ViewAppointmentsEvent event) {
				doViewAppointments(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewDiagnosesEvent.TYPE, new ViewDiagnosesEventHandler() {
			@Override
			public void onViewDiagnoses(ViewDiagnosesEvent event) {
				doViewDiagnoses(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewLinksEvent.TYPE, new ViewLinksEventHandler() {
			@Override
			public void onViewLinks(ViewLinksEvent event) {
				doViewLinksEvent(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewAddressesEvent.TYPE, new ViewAddressesEventHandler() {
			@Override
			public void onViewAddresses(ViewAddressesEvent event) {
				doViewAddresses(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewContactsEvent.TYPE, new ViewContactsEventHandler() {
			@Override
			public void onViewContacts(ViewContactsEvent event) {
				doViewContacts(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewMedicationsEvent.TYPE, new ViewMedicationsEventHandler() {
			@Override
			public void onViewMedications(ViewMedicationsEvent event) {
				doViewMedications(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewTicklerEvent.TYPE, new ViewTicklerEventHandler() {
			@Override
			public void onOpenTickler(ViewTicklerEvent event) {
				doViewTickler(event.getAction());
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
				doViewDemographics(event.getView(), event.getAction());
			}
		});
	}

	protected void doViewTickler(GetTickler action) {
		TicklerView<Tickler> ticklerView = new TicklerViewImpl<Tickler>();
		Presenter presenter = new TicklerPresenter(ticklerView, TicklerColumnDefinitionsFactory.getTicklerColumnDefinitions(), eventBus, dispatch, action);
		presenter.go(null);
		dashboard.addTab(ticklerView.asWidget(), "Tickler");
	}

	protected void doViewMedications(EHRView<EHR> view, GetMedications action) {
		Presenter presenter = new MedicationPresenter(new MedicationView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewContacts(EHRView<EHR> view, GetContacts action) {
		Presenter presenter = new ContactPresenter(new ContactView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewAddresses(EHRView<EHR> view, GetAddresses action) {
		Presenter presenter = new AddressPresenter(new AddressView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewLinksEvent(EHRView<EHR> view, GetLinks action) {
		Presenter presenter = new LinkPresenter(new LinkView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewDiagnoses(EHRView<EHR> view, GetDiagnoses action) {
		Presenter presenter = new DiagnosisPresenter(new DiagnosisView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewAppointments(EHRView<EHR> view, GetAppointments action) {
		Presenter presenter = new AppointmentPresenter(new AppointmentView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewReferral(EHRView<EHR> view, GetReferral action) {
		Presenter presenter = new ReferralPresenter(new ReferralView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewMessages(EHRView<EHR> view, GetMessages action) {
		Presenter presenter = new MessagesPresenter(new MessagesView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewEhr(String caseNumber) {
		ehrView = new EHRViewImpl<EHR>();
		Presenter presenter = new EHRPresenter(ehrView, eventBus, dispatch, caseNumber);
		presenter.go(null);
		dashboard.addTab(ehrView.asWidget(), caseNumber);
	}

	public void doViewPatientSummary(EHRView<EHR> view, GetPatientSummary action) {
		Presenter presenter = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewDemographics(final EHRView<EHR> view, GetDemographics action) {
		Presenter presenter = new DemographicsPresenter(new DemographicsView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void go(final HasWidgets container) {
		container.clear();
		dashboard = new DashboardViewImpl<LinkedHashMap<String, Long>>();
		dashboardPresenter = new DashboardPresenter(dashboard, eventBus, dispatch);
		dashboardPresenter.go(container);
	}
}
