package org.eastway.echarts.client.activity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;

import org.eastway.echarts.client.EchartsConstants;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.AppointmentReportListPlace;
import org.eastway.echarts.client.place.DashboardFramePlace;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.LabPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.EhrQueryListPlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.PhysicianOrderPlace;
import org.eastway.echarts.client.place.PlaceLogRecordListPlace;
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.StaffAnalysisPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.request.AddressProxy;
import org.eastway.echarts.client.request.AppointmentDataProvider;
import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.LinkProxy;
import org.eastway.echarts.client.request.MedicationProxy;
import org.eastway.echarts.client.request.MessageProxy;
import org.eastway.echarts.client.request.PlaceLogRecordProxy;
import org.eastway.echarts.client.request.PlaceLogRecordRequest;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.ui.ARInfoView;
import org.eastway.echarts.client.ui.AddressView;
import org.eastway.echarts.client.ui.AppointmentListView;
import org.eastway.echarts.client.ui.AppointmentView;
import org.eastway.echarts.client.ui.DashboardView;
import org.eastway.echarts.client.ui.DemographicsView;
import org.eastway.echarts.client.ui.DiagnosisView;
import org.eastway.echarts.client.ui.LinkView;
import org.eastway.echarts.client.ui.MedicationView;
import org.eastway.echarts.client.ui.MessageView;
import org.eastway.echarts.client.ui.EhrQueryListView;
import org.eastway.echarts.client.ui.PatientSummaryView;
import org.eastway.echarts.client.ui.PlaceLogRecordListView;
import org.eastway.echarts.client.ui.ProfileView;
import org.eastway.echarts.client.ui.ReferralView;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class MasterActivityMapper implements ActivityMapper {
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;
	private TicklerView<Tickler> ticklerView;
	private List<ColumnDefinition<Tickler>> ticklerColumnDefinitions;
	private MessageView<MessageProxy> messageView;
	private DemographicsView demographicsView;
	private ReferralView referralView;
	private AppointmentView<AppointmentProxy> appointmentView;
	private DiagnosisView<DiagnosisProxy> diagnosisView;
	private LinkView<LinkProxy> linkView;
	private AddressView<AddressProxy> addressView;
	private MedicationView<MedicationProxy> medicationView;
	private ARInfoView arInfoView;
	private DashboardView<LinkedHashMap<String, Long>> dashboardView;
	private ProfileView<UserProxy> profileView;
	private List<ColumnDefinition<UserProxy>> profileColumnDefinitions;
	private AppointmentDataProvider appointmentDataProvider;
	private PatientSummaryView patientSummaryView;
	private EchartsPlaceHistoryMapper historyMapper;
	private AppointmentListView appointmentListView;
	private EhrQueryListView patientQueryListView;
	private PlaceLogRecordListView placeLogRecordListView;

	@Inject
	public MasterActivityMapper(EchartsRequestFactory requestFactory,
							     PlaceController placeController,
							     TicklerView<Tickler> ticklerView,
							     EchartsPlaceHistoryMapper historyMapper,
							     List<ColumnDefinition<Tickler>> ticklerColumnDefinitions,
							     PatientSummaryView patientSummaryView,
							     MessageView<MessageProxy> messageView,
							     DemographicsView demographicsView,
							     ReferralView referralView,
							     AppointmentView<AppointmentProxy> appointmentView,
							     DiagnosisView<DiagnosisProxy> diagnosisView,
							     LinkView<LinkProxy> linkView,
							     AddressView<AddressProxy> addressView,
							     MedicationView<MedicationProxy> medicationView,
							     ARInfoView arInfoView,
							     DashboardView<LinkedHashMap<String, Long>> dashboardView,
							     ProfileView<UserProxy> profileView,
							     List<ColumnDefinition<UserProxy>> profileColumnDefinitions,
							     AppointmentDataProvider appointmentDataProvider,
							     AppointmentListView appointmentListView,
							     EhrQueryListView patientQueryListView,
							     PlaceLogRecordListView placeLogRecordListView) {
		super();
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.historyMapper = historyMapper;
		this.ticklerView = ticklerView;
		this.ticklerColumnDefinitions = ticklerColumnDefinitions;
		this.patientSummaryView = patientSummaryView;
		this.messageView = messageView;
		this.demographicsView = demographicsView;
		this.referralView = referralView;
		this.appointmentView = appointmentView;
		this.diagnosisView = diagnosisView;
		this.linkView = linkView;
		this.addressView = addressView;
		this.medicationView = medicationView;
		this.arInfoView = arInfoView;
		this.dashboardView = dashboardView;
		this.profileView = profileView;
		this.profileColumnDefinitions = profileColumnDefinitions;
		this.appointmentDataProvider = appointmentDataProvider;
		this.appointmentListView = appointmentListView;
		this.patientQueryListView = patientQueryListView;
		this.placeLogRecordListView = placeLogRecordListView;
	}

	@Override
	public Activity getActivity(Place place) {
		logPlaceChange(place);
		if (place instanceof TicklerPlace)
			return new TicklerActivity((TicklerPlace) place, requestFactory, ticklerColumnDefinitions, placeController, ticklerView);
		else if (place instanceof PatientSummaryPlace)
			return new PatientSummaryActivity((PatientSummaryPlace) place, requestFactory, patientSummaryView);
		else if (place instanceof MessagePlace)
			return new MessageActivity((MessagePlace) place, requestFactory, messageView);
		else if (place instanceof DemographicsPlace)
			return new DemographicsActivity((DemographicsPlace) place, requestFactory, demographicsView);
		else if (place instanceof ReferralPlace)
			return new ReferralActivity((ReferralPlace) place, requestFactory, referralView);
		else if (place instanceof AppointmentPlace)
			return new AppointmentActivity((AppointmentPlace) place, requestFactory, appointmentView, appointmentDataProvider);
		else if (place instanceof DiagnosisPlace)
			return new DiagnosisActivity((DiagnosisPlace) place, requestFactory, diagnosisView);
		else if (place instanceof LinkPlace)
			return new LinkActivity((LinkPlace) place, requestFactory, linkView);
		else if (place instanceof AddressPlace)
			return new AddressActivity((AddressPlace) place, requestFactory, addressView);
		else if (place instanceof MedicationPlace)
			return new MedicationActivity((MedicationPlace) place, requestFactory, medicationView);
		else if (place instanceof TreatmentPlanPlace)
			return new TreatmentPlanActivity((TreatmentPlanPlace) place, requestFactory);
		else if (place instanceof ServiceHistoryPlace)
			return new ServiceHistoryActivity((ServiceHistoryPlace) place, requestFactory);
		else if (place instanceof ARInfoPlace)
			return new ARInfoActivity((ARInfoPlace) place, requestFactory, arInfoView);
		else if (place instanceof DashboardPlace)
			return new DashboardActivity((DashboardPlace) place, placeController, dashboardView, requestFactory);
		else if (place instanceof ProfilePlace)
			return new ProfileActivity((ProfilePlace) place, profileColumnDefinitions, requestFactory, profileView);
		else if (place instanceof LabPlace)
			return new LabActivity((LabPlace) place);
		else if (place instanceof PhysicianOrderPlace)
			return new PhysicianOrderActivity((PhysicianOrderPlace) place);
		else if (place instanceof StaffAnalysisPlace)
			return new StaffAnalysisActivity((StaffAnalysisPlace) place);
		else if (place instanceof DashboardFramePlace)
			return new DashboardFrameActivity((DashboardFramePlace) place);
		else if (place instanceof AppointmentReportListPlace)
			return new AppointmentListActivity(requestFactory, appointmentListView);
		else if (place instanceof EhrQueryListPlace)
			return new EhrQueryListActivity((EhrQueryListPlace)place, requestFactory, placeController, patientQueryListView);
		else if (place instanceof PlaceLogRecordListPlace)
			return new PlaceLogRecordListActivity(requestFactory, (PlaceLogRecordListPlace)place, placeLogRecordListView);
		return null;
	}

	EchartsConstants constants = GWT.create(EchartsConstants.class);

	private void logPlaceChange(Place place) {
		String token = historyMapper.getToken(place);
		PlaceLogRecordRequest request = requestFactory.placeLogRecordRequest();
		PlaceLogRecordProxy proxy = request.create(PlaceLogRecordProxy.class);
		proxy.setAppVersion(constants.version());
		proxy.setLogLevel(Level.INFO.toString());
		proxy.setTimestamp(new Date().getTime());
		proxy.setUrl(Window.Location.createUrlBuilder().setHash(token).buildString());
		proxy.setUserAgent(Window.Navigator.getUserAgent());
		proxy.setUsername(EchartsUser.userName);
		request.persist().using(proxy).fire();
	}
}
