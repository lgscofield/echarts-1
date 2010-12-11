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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.activity.AppointmentActivity;
import org.eastway.echarts.client.activity.DemographicsActivity;
import org.eastway.echarts.client.activity.DiagnosisActivity;
import org.eastway.echarts.client.activity.EhrActivity;
import org.eastway.echarts.client.activity.PatientSummaryActivity;
import org.eastway.echarts.client.activity.ReferralActivity;
import org.eastway.echarts.client.common.ARInfoColumnDefinitionsImpl;
import org.eastway.echarts.client.common.AddressColumnDefinitionsImpl;
import org.eastway.echarts.client.common.AppointmentColumnDefinitionsImpl;
import org.eastway.echarts.client.common.DemographicsColumnDefinitionsImpl;
import org.eastway.echarts.client.common.DiagnosisColumnDefinitionsImpl;
import org.eastway.echarts.client.common.PatientSummaryColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ReferralColumnDefinitionsImpl;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.LogoutEventHandler;
import org.eastway.echarts.client.events.OpenCpstNoteEvent;
import org.eastway.echarts.client.events.OpenCpstNoteEventHandler;
import org.eastway.echarts.client.events.OpenDoctorProgressNoteEvent;
import org.eastway.echarts.client.events.OpenDoctorProgressNoteEventHandler;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenEhrEventHandler;
import org.eastway.echarts.client.events.OpenIndividualProgressNoteEvent;
import org.eastway.echarts.client.events.OpenIndividualProgressNoteEventHandler;
import org.eastway.echarts.client.events.OpenIspEvent;
import org.eastway.echarts.client.events.OpenIspEventHandler;
import org.eastway.echarts.client.events.OpenNurseProgressNoteEvent;
import org.eastway.echarts.client.events.OpenNurseProgressNoteEventHandler;
import org.eastway.echarts.client.events.ViewARInfoEvent;
import org.eastway.echarts.client.events.ViewARInfoEventHandler;
import org.eastway.echarts.client.events.ViewAddressesEvent;
import org.eastway.echarts.client.events.ViewAddressesEventHandler;
import org.eastway.echarts.client.events.ViewAppointmentsEvent;
import org.eastway.echarts.client.events.ViewAppointmentsEventHandler;
import org.eastway.echarts.client.events.ViewDemographicsEvent;
import org.eastway.echarts.client.events.ViewDemographicsEventHandler;
import org.eastway.echarts.client.events.ViewDiagnosesEvent;
import org.eastway.echarts.client.events.ViewDiagnosesEventHandler;
import org.eastway.echarts.client.events.ViewGroupProgressNoteEvent;
import org.eastway.echarts.client.events.ViewGroupProgressNoteEventHandler;
import org.eastway.echarts.client.events.ViewLabsEvent;
import org.eastway.echarts.client.events.ViewLabsEventHandler;
import org.eastway.echarts.client.events.ViewLastSeenReportEvent;
import org.eastway.echarts.client.events.ViewLastSeenReportEventHandler;
import org.eastway.echarts.client.events.ViewLinksEvent;
import org.eastway.echarts.client.events.ViewLinksEventHandler;
import org.eastway.echarts.client.events.ViewMedicationsEvent;
import org.eastway.echarts.client.events.ViewMedicationsEventHandler;
import org.eastway.echarts.client.events.ViewMedsomSignaturesEvent;
import org.eastway.echarts.client.events.ViewMedsomSignaturesEventHandler;
import org.eastway.echarts.client.events.ViewMessagesEvent;
import org.eastway.echarts.client.events.ViewMessagesEventHandler;
import org.eastway.echarts.client.events.ViewOverlapsReportEvent;
import org.eastway.echarts.client.events.ViewOverlapsReportEventHandler;
import org.eastway.echarts.client.events.ViewPatientSummaryEvent;
import org.eastway.echarts.client.events.ViewPatientSummaryEventHandler;
import org.eastway.echarts.client.events.ViewProfileEvent;
import org.eastway.echarts.client.events.ViewProfileEventHandler;
import org.eastway.echarts.client.events.ViewReferralEvent;
import org.eastway.echarts.client.events.ViewReferralEventHandler;
import org.eastway.echarts.client.events.ViewServiceHistoryEvent;
import org.eastway.echarts.client.events.ViewServiceHistoryEventHandler;
import org.eastway.echarts.client.events.ViewProviderSignaturesEvent;
import org.eastway.echarts.client.events.ViewProviderSignaturesEventHandler;
import org.eastway.echarts.client.events.ViewStaffHistoryEvent;
import org.eastway.echarts.client.events.ViewStaffHistoryEventHandler;
import org.eastway.echarts.client.events.ViewSupervisorSignaturesEvent;
import org.eastway.echarts.client.events.ViewSupervisorSignaturesEventHandler;
import org.eastway.echarts.client.events.ViewTicklerEvent;
import org.eastway.echarts.client.events.ViewTicklerEventHandler;
import org.eastway.echarts.client.events.ViewTreatmentPlanEvent;
import org.eastway.echarts.client.events.ViewTreatmentPlanEventHandler;
import org.eastway.echarts.client.presenter.ARInfoPresenter;
import org.eastway.echarts.client.presenter.AddressPresenter;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.client.presenter.MedicationPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.ProfilePresenter;
import org.eastway.echarts.client.rpc.ARInfoProxy;
import org.eastway.echarts.client.rpc.AddressProxy;
import org.eastway.echarts.client.rpc.AppointmentProxy;
import org.eastway.echarts.client.rpc.DbServerConfigProxy;
import org.eastway.echarts.client.rpc.DemographicsProxy;
import org.eastway.echarts.client.rpc.DiagnosisProxy;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.ReferralProxy;
import org.eastway.echarts.client.view.ARInfoViewImpl;
import org.eastway.echarts.client.view.AddressViewImpl;
import org.eastway.echarts.client.view.AppointmentViewImpl;
import org.eastway.echarts.client.view.DemographicsViewImpl;
import org.eastway.echarts.client.view.DiagnosisViewImpl;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.EHRViewImpl;
import org.eastway.echarts.client.view.LinkView;
import org.eastway.echarts.client.view.MedicationView;
import org.eastway.echarts.client.view.PatientSummaryViewImpl;
import org.eastway.echarts.client.view.ReferralViewImpl;
import org.eastway.echarts.shared.GetARInfo;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController implements Presenter {
	private final EventBus eventBus;
	private EHRViewImpl<EHRProxy> ehrView;
	private DashboardPresenter dashboardPresenter;
	@Inject private ProfilePresenter profilePresenter;
	@Inject private DiagnosisColumnDefinitionsImpl diagnosisColumnDefinitions;
	@Inject private DemographicsColumnDefinitionsImpl demographicsColumnDefinitions;
	@Inject private AddressColumnDefinitionsImpl addressColumnDefinitions;
	@Inject private PatientSummaryColumnDefinitionsImpl patientSummaryColumnDefinitions;
	@Inject private AppointmentColumnDefinitionsImpl appointmentColumnDefinitions;
	@Inject private ReferralColumnDefinitionsImpl referralColumnDefinitions;
	@Inject private ARInfoColumnDefinitionsImpl aRInfoColumnDefinitions;
	private EchartsRequestFactory requestFactory;

	private static final Logger log = Logger.getLogger(AppController.class.getName());

	@Inject
	public AppController(DashboardPresenter dashboardPresenter, EventBus eventBus, EchartsRequestFactory requestFactory) {
		this.dashboardPresenter = dashboardPresenter;
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;
		checkSession();
	}

	private void checkSession() {
		requestFactory.dbServerConfigRequest().findDbServerConfig("dbServerUrl").fire(new Receiver<DbServerConfigProxy>() {
			@Override
			public void onSuccess(DbServerConfigProxy response) {
				if ((EchartsUser.sessionId == null || EchartsUser.sessionId == "null") ||
						(EchartsUser.userName == null || EchartsUser.userName == "null") ||
						(EchartsUser.staffId == null || EchartsUser.staffId == "null")) {
					Window.Location.assign("http://" + response.getConfigValue() + "/echarts/logout.aspx?continue=" + Window.Location.getHref());
				} else {
					setDbServerUrl(response.getConfigValue());
					bind();
				}
			}
		});
	}

	private void setDbServerUrl(String dbServerUrl) {
		EchartsUser.dbServerUrl = dbServerUrl;
	}

	private void bind() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		});
		eventBus.addHandler(OpenEhrEvent.TYPE, new OpenEhrEventHandler() {
			@Override
			public void onOpenEhr(OpenEhrEvent event) {
				doViewEhr(event.getEhr());
			}
		});
		eventBus.addHandler(ViewPatientSummaryEvent.TYPE, new ViewPatientSummaryEventHandler() {
			@Override
			public <T> void onViewPatientSummary(ViewPatientSummaryEvent<T> event) {
				doViewPatientSummary(event.getView(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewMessagesEvent.TYPE, new ViewMessagesEventHandler() {
			@Override
			public <T> void onViewMessages(ViewMessagesEvent<T> event) {
				doViewMessages(event.getView(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewReferralEvent.TYPE, new ViewReferralEventHandler() {
			@Override
			public <T> void onViewReferral(ViewReferralEvent<T> event) {
				doViewReferral(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewAppointmentsEvent.TYPE, new ViewAppointmentsEventHandler() {
			@Override
			public <T> void onViewAppointments(ViewAppointmentsEvent<T> event) {
				doViewAppointments(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewDiagnosesEvent.TYPE, new ViewDiagnosesEventHandler() {
			@Override
			public <T> void onViewDiagnoses(ViewDiagnosesEvent<T> event) {
				doViewDiagnoses(event.getView(), event.getCaseNumber(), event.getAction());
			}
		});
		eventBus.addHandler(ViewLinksEvent.TYPE, new ViewLinksEventHandler() {
			@Override
			public <T> void onViewLinks(ViewLinksEvent<T> event) {
				doViewLinksEvent(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewAddressesEvent.TYPE, new ViewAddressesEventHandler() {
			@Override
			public <T> void onViewAddresses(ViewAddressesEvent<T> event) {
				doViewAddresses(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewMedicationsEvent.TYPE, new ViewMedicationsEventHandler() {
			@Override
			public <T> void onViewMedications(ViewMedicationsEvent<T> event) {
				doViewMedications(event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewTicklerEvent.TYPE, new ViewTicklerEventHandler() {
			@Override
			public void onOpenTickler(ViewTicklerEvent event) {
				doViewTickler();
			}
		});
		eventBus.addHandler(ViewProfileEvent.TYPE, new ViewProfileEventHandler() {
			@Override
			public void onViewProfile(ViewProfileEvent event) {
				doViewProfile();
			}
		});
		RequestEvent.register(eventBus, new RequestEvent.Handler() {
			// Only show loading status if a request isn't serviced in 250ms.
			private static final int LOADING_TIMEOUT = 250;
			@Override
			public void onRequestEvent(RequestEvent requestEvent) {
				if (requestEvent.getState() == State.SENT) {
					dashboardPresenter.getDisplay().getMole().showDelayed(LOADING_TIMEOUT);
				} else if (requestEvent.getState() == State.RECEIVED) {
					dashboardPresenter.getDisplay().getMole().hide();
				}
			}
		});
		eventBus.addHandler(ViewDemographicsEvent.TYPE, new ViewDemographicsEventHandler() {
			@Override
			public <T> void onViewDemographics(ViewDemographicsEvent<T> event) {
				doViewDemographics(event.getView(), event.getCaseNumber());
			}
		});
		eventBus.addHandler(OpenIspEvent.TYPE, new OpenIspEventHandler() {
			@Override
			public void onOpenIsp(OpenIspEvent event) {
				doOpenIsp(event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewTreatmentPlanEvent.TYPE, new ViewTreatmentPlanEventHandler() {
			@Override
			public <T> void onViewTreatmentPlan(ViewTreatmentPlanEvent<T> event) {
				doViewTreatmentPlan(event.getCaseNumber(), event.getView());
			}
		});
		eventBus.addHandler(ViewServiceHistoryEvent.TYPE, new ViewServiceHistoryEventHandler() {
			@Override
			public <T> void onViewServiceHistory(ViewServiceHistoryEvent<T> event) {
				doViewServiceHistory(event.getCaseNumber(), event.getView());
			}
		});
		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			@Override
			public void onLogout(LogoutEvent event) {
				doLogout();
			}
		});
		eventBus.addHandler(OpenCpstNoteEvent.TYPE, new OpenCpstNoteEventHandler() {
			@Override
			public void onOpenCpstNote(OpenCpstNoteEvent event) {
				doOpenCpstNote(event.getCaseNumber());
			}
		});
		eventBus.addHandler(OpenIndividualProgressNoteEvent.TYPE, new OpenIndividualProgressNoteEventHandler() {
			@Override
			public void onOpenIndividualProgressNote(
					OpenIndividualProgressNoteEvent event) {
				doOpenIndividualProgressNote(event.getCaseNumber());
			}
		});
		eventBus.addHandler(OpenDoctorProgressNoteEvent.TYPE, new OpenDoctorProgressNoteEventHandler() {
			@Override
			public void onOpenDoctorProgressNote(
					OpenDoctorProgressNoteEvent event) {
				doOpenDoctorProgressNote(event.getCaseNumber());
			}
		});
		eventBus.addHandler(OpenNurseProgressNoteEvent.TYPE, new OpenNurseProgressNoteEventHandler() {
			@Override
			public void onOpenNurseProgressNote(OpenNurseProgressNoteEvent event) {
				doOpenNurseProgressNote(event.getCaseNumber());
			}
		});
		eventBus.addHandler(ViewProviderSignaturesEvent.TYPE, new ViewProviderSignaturesEventHandler() {
			@Override
			public void onViewSignature(ViewProviderSignaturesEvent event) {
				doViewSignature();
			}
		});
		eventBus.addHandler(ViewARInfoEvent.TYPE, new ViewARInfoEventHandler() {
			@Override
			public <T> void onViewARInfo(ViewARInfoEvent<T> event) {
				doViewARInfo(event.getCaseNumber(), event.getView(), event.getAction());
			}
		});
		eventBus.addHandler(ViewSupervisorSignaturesEvent.TYPE, new ViewSupervisorSignaturesEventHandler() {
			@Override
			public void onViewSupervisorSignatures(ViewSupervisorSignaturesEvent event) {
				doViewSupervisorSignatures();
			}
		});
		eventBus.addHandler(ViewMedsomSignaturesEvent.TYPE, new ViewMedsomSignaturesEventHandler() {
			@Override
			public void onViewMedsomSignatures(ViewMedsomSignaturesEvent event) {
				doViewMedsomSignatures();
			}
		});
		eventBus.addHandler(ViewStaffHistoryEvent.TYPE, new ViewStaffHistoryEventHandler() {
			@Override
			public void onViewStaffHistory(ViewStaffHistoryEvent event) {
				doViewStaffHistory();
			}
		});
		eventBus.addHandler(ViewGroupProgressNoteEvent.TYPE, new ViewGroupProgressNoteEventHandler() {
			@Override
			public void onViewGroupProgressNote(ViewGroupProgressNoteEvent event) {
				doViewGroupProgressNote();
			}
		});
		eventBus.addHandler(ViewLastSeenReportEvent.TYPE, new ViewLastSeenReportEventHandler() {
			@Override
			public void onViewLastSeenReport(ViewLastSeenReportEvent viewLastSeenReportEvent) {
				doViewLastSeenReport();
			}
		});
		eventBus.addHandler(ViewLabsEvent.TYPE, new ViewLabsEventHandler() {
			@Override
			public <T> void onViewLabs(ViewLabsEvent<T> event) {
				doViewLabs(event.getCaseNumber(), event.getView());
			}
		});
		eventBus.addHandler(ViewOverlapsReportEvent.TYPE, new ViewOverlapsReportEventHandler() {
			@Override
			public void onOpenOverlapsReport(ViewOverlapsReportEvent event) {
				doViewOverlapsReport();
			}
		});
	}

	private void doViewLastSeenReport() {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/lastseen.asp", "_blank", "");
	}

	private void doViewGroupProgressNote() {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/108GroupSetup.asp?staffid=" + EchartsUser.staffId, "_blank", "");
	}

	private void doViewStaffHistory() {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/staffhistory.asp?staffid=" + EchartsUser.staffId);
		frame.setSize("100%", "100%");
		dashboardPresenter.getDisplay().addTab(frame, "History");
	}

	private void doViewMedsomSignatures() {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/signatures/medqueue.asp?staffid=" + EchartsUser.staffId);
		frame.setSize("100%", "100%");
		dashboardPresenter.getDisplay().addTab(frame, "Med-Som Signatures");
	}

	private void doViewSupervisorSignatures() {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/signatures/supervisorqueue.asp?staffid=" + EchartsUser.staffId);
		frame.setSize("100%", "100%");
		dashboardPresenter.getDisplay().addTab(frame, "Supervisor Signatures");
	}

	private <T> void doViewARInfo(String caseNumber, EHRView<T> ehrView, GetARInfo action) {
		Presenter presenter = new ARInfoPresenter(new ARInfoViewImpl<ARInfoProxy>(), aRInfoColumnDefinitions, requestFactory, action);
		presenter.go(ehrView.getDisplayArea());
	}

	private void doViewSignature() {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/signatures/sign.asp?staffid=" + EchartsUser.staffId);
		frame.setSize("100%", "100%");
		dashboardPresenter.getDisplay().addTab(frame, "Provider Signatures");
	}

	private void doOpenNurseProgressNote(String caseNumber) {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/103PM-NPNEdit.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber, "_blank", "");
	}

	private void doOpenDoctorProgressNote(String caseNumber) {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/104PharmEdit.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber, "_blank", "");
	}

	private void doOpenIndividualProgressNote(String caseNumber) {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/102IPNEdit.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber, "_blank", "");
	}

	private void doOpenCpstNote(String caseNumber) {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/101CPSTEdit.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber, "_blank", "");
	}

	private void doLogout() {
		Window.Location.assign("http://" + EchartsUser.dbServerUrl + "/echarts/logout.aspx?continue=" + Window.Location.getHref());
	}

	private <T> void doViewServiceHistory(String caseNumber, EHRView<T> ehrView) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/clienthistory.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber);
		ehrView.getDisplayArea().clear();
		ehrView.getDisplayArea().add(frame);
		frame.setSize("100%", "100%");
	}

	private <T> void doViewTreatmentPlan(String caseNumber, EHRView<T> ehrView) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/treatmentplan.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber);
		ehrView.getDisplayArea().clear();
		ehrView.getDisplayArea().add(frame);
		frame.setSize("100%", "100%");
	}

	private void doOpenIsp(String caseNumber) {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/GandO.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber, "_blank", "");
	}

	private void doViewTickler() {
//		dashboardPresenter.getDisplay().addTab(
//				ticklerPresenter
//					.getDisplay()
//					.asWidget(), "Tickler");
//		ticklerPresenter.go(null);
	}

	private void doViewProfile() {
		dashboardPresenter.getDisplay().addTab(profilePresenter.getDisplay().asWidget(), "Profile");
		profilePresenter.go(null);
	}

	private <T> void doViewMedications(EHRView<T> ehrView, GetMedications action) {
		Presenter presenter = new MedicationPresenter(new MedicationView(), requestFactory, action);
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewAddresses(EHRView<T> ehrView, GetAddresses action) {
		Presenter presenter = new AddressPresenter(new AddressViewImpl<AddressProxy>(), addressColumnDefinitions, requestFactory, action.getCaseNumber());
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewLinksEvent(EHRView<T> ehrView, GetLinks action) {
		Presenter presenter = new LinkPresenter(new LinkView(), requestFactory, action);
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewDiagnoses(EHRView<T> ehrView, String caseNumber, GetDiagnoses action) {
		DiagnosisActivity diagnosisPresenter = new DiagnosisActivity(new DiagnosisViewImpl<DiagnosisProxy>(), diagnosisColumnDefinitions, requestFactory, action.getCaseNumber());
		diagnosisPresenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewAppointments(EHRView<T> ehrView, GetAppointments action) {
		Presenter presenter = new AppointmentActivity(new AppointmentViewImpl<AppointmentProxy>(), appointmentColumnDefinitions, eventBus, requestFactory, action);
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewReferral(EHRView<T> ehrView, GetReferral action) {
		Presenter presenter = new ReferralActivity(new ReferralViewImpl<ReferralProxy>(), referralColumnDefinitions, requestFactory, action.getCaseNumber());
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewMessages(EHRView<T> ehrView, String caseNumber) {
		//Presenter presenter = new MessageActivity(new MessageViewImpl(), eventBus, requestFactory, caseNumber);
		//presenter.go(ehrView.getDisplayArea());
	}

	private void doViewEhr(EHRProxy ehr) {
		ehrView = new EHRViewImpl<EHRProxy>();
		Presenter presenter = new EhrActivity(ehrView, eventBus, ehr);
		presenter.go(null);
		dashboardPresenter.getDisplay().addTab(ehrView.asWidget(), ehr.getPatient().getCaseNumber());
	}

	private <T> void doViewPatientSummary(EHRView<T> ehrView, String caseNumber) {
		Presenter presenter = new PatientSummaryActivity(new PatientSummaryViewImpl<EHRProxy>(), patientSummaryColumnDefinitions, eventBus, requestFactory, caseNumber);
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewDemographics(final EHRView<T> ehrView, String caseNumber) {
		Presenter presenter = new DemographicsActivity(new DemographicsViewImpl<DemographicsProxy>(), demographicsColumnDefinitions, requestFactory, caseNumber);
		presenter.go(ehrView.getDisplayArea());
	}

	private <T> void doViewLabs(String caseNumber, EHRView<T> view) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/labs.asp?PATID=" + caseNumber);
		ehrView.getDisplayArea().clear();
		ehrView.getDisplayArea().add(frame);
		frame.setSize("100%", "100%");
	}

	private void doViewOverlapsReport() {
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/overlaps.asp?staffid=" + EchartsUser.staffId, "_blank", "");
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		dashboardPresenter.go(container);
	}
}
