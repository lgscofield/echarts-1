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

import com.google.gwt.core.client.GWT;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController {
	private final EventBus eventBus;
	private EHRServicesAsync ehrServices = GWT.<EHRServicesAsync>create(EHRServices.class);
	private DashboardView<LinkedHashMap<String, Long>> dashboard = null;
	private EHRView<EHR> ehrView;
	protected MessagesView messagesView;
	protected CachingDispatchAsyncImpl dispatch;
	private DashboardPresenter dashboardPresenter;
	protected DemographicsPresenter demographicsPresenter;

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
				doViewEhr(event.getId(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewPatientSummaryEvent.TYPE, new ViewPatientSummaryEventHandler() {
			@Override
			public void onViewPatientSummary(ViewPatientSummaryEvent event) {
				doViewPatientSummary(event.getId(), event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewMessagesEvent.TYPE, new ViewMessagesEventHandler() {
			@Override
			public void onViewMessages(ViewMessagesEvent event) {
				doViewMessages(event.getView(), event.getAction(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewReferralEvent.TYPE, new ViewReferralEventHandler() {
			@Override
			public void onViewReferral(ViewReferralEvent event) {
				doViewReferral(event.getId(), event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewAppointmentsEvent.TYPE, new ViewAppointmentsEventHandler() {
			@Override
			public void onViewAppointments(ViewAppointmentsEvent event) {
				doViewAppointments(event.getCaseNumber(), event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewDiagnosesEvent.TYPE, new ViewDiagnosesEventHandler() {
			@Override
			public void onViewDiagnoses(ViewDiagnosesEvent event) {
				doViewDiagnoses(event.getCaseNumber(), event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewLinksEvent.TYPE, new ViewLinksEventHandler() {
			@Override
			public void onViewLinks(ViewLinksEvent event) {
				doViewLinksEvent(event.getView(), event.getAction(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewAddressesEvent.TYPE, new ViewAddressesEventHandler() {
			@Override
			public void onViewAddresses(ViewAddressesEvent event) {
				doViewAddresses(event.getView(), event.getAction(), event.getCaseNumber());
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
				doViewDemographics(event.getId(), event.getView(), event.getAction());
			}
		});
	}

	protected void doViewMedications(EHRView<EHR> view, GetMedications action) {
		Presenter presenter = new MedicationPresenter(new MedicationView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewContacts(EHRView<EHR> view, GetContacts action) {
		Presenter presenter = new ContactPresenter(new ContactView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewAddresses(EHRView<EHR> view, GetAddresses action, String caseNumber) {
		Presenter presenter = new AddressPresenter(new AddressView(), eventBus, dispatch, action, caseNumber);
		presenter.go(view.getDisplayArea());
	}

	public void doViewLinksEvent(EHRView<EHR> view, GetLinks action, String caseNumber) {
		Presenter presenter = new LinkPresenter(new LinkView(), eventBus, dispatch, action, caseNumber);
		presenter.go(view.getDisplayArea());
	}

	public void doViewDiagnoses(String caseNumber, EHRView<EHR> view, GetDiagnoses action) {
		Presenter presenter = new DiagnosisPresenter(new DiagnosisView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewAppointments(String caseNumber, EHRView<EHR> view, GetAppointments action) {
		Presenter presenter = new AppointmentPresenter(new AppointmentView(), eventBus, dispatch, action, caseNumber);
		presenter.go(view.getDisplayArea());
	}

	public void doViewReferral(long ehrId, EHRView<EHR> view, GetReferral action) {
		Presenter presenter = new ReferralPresenter(new ReferralView(), eventBus, dispatch, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewMessages(EHRView<EHR> view, GetMessages action, String caseNumber) {
		Presenter presenter = new MessagesPresenter(new MessagesView(), eventBus, dispatch, caseNumber, action);
		presenter.go(view.getDisplayArea());
	}

	public void doViewEhr(long ehrId, String caseNumber) {
		ehrView = new EHRViewImpl<EHR>();
		Presenter presenter = new EHRPresenter(ehrView, eventBus, ehrServices, ehrId);
		presenter.go(null);
		dashboard.addTab(ehrView.asWidget(), caseNumber);
	}

	public void doViewPatientSummary(long ehrId, EHRView<EHR> view, GetPatientSummary action) {
		Presenter presenter = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, dispatch, action, ehrId);
		presenter.go(view.getDisplayArea());
	}

	public void doViewDemographics(final long ehrId, final EHRView<EHR> view, GetDemographics action) {
		demographicsPresenter = new DemographicsPresenter(new DemographicsView(), eventBus, dispatch, action, ehrId);
		demographicsPresenter.go(view.getDisplayArea());
	}

	public void go(final HasWidgets container) {
		container.clear();
		dashboard = new DashboardViewImpl<LinkedHashMap<String, Long>>();
		dashboardPresenter = new DashboardPresenter(dashboard, eventBus, dispatch);
		dashboardPresenter.go(container);
	}
}
