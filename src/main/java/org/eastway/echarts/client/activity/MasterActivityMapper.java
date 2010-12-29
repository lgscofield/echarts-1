package org.eastway.echarts.client.activity;

import java.util.LinkedHashMap;
import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.LabPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedSomSignaturesPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.place.ProviderSignaturesPlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.StaffHistoryPlace;
import org.eastway.echarts.client.place.SupervisorSignaturesPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.request.ARInfoProxy;
import org.eastway.echarts.client.request.AddressProxy;
import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.request.DemographicsProxy;
import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.LinkProxy;
import org.eastway.echarts.client.request.MedicationProxy;
import org.eastway.echarts.client.request.MessageProxy;
import org.eastway.echarts.client.request.ReferralProxy;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.ui.ARInfoView;
import org.eastway.echarts.client.ui.AddressView;
import org.eastway.echarts.client.ui.AppointmentView;
import org.eastway.echarts.client.ui.DashboardView;
import org.eastway.echarts.client.ui.DemographicsView;
import org.eastway.echarts.client.ui.DiagnosisView;
import org.eastway.echarts.client.ui.LinkView;
import org.eastway.echarts.client.ui.MedicationView;
import org.eastway.echarts.client.ui.MessageView;
import org.eastway.echarts.client.ui.PatientSummaryView;
import org.eastway.echarts.client.ui.ProfileView;
import org.eastway.echarts.client.ui.ReferralView;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class MasterActivityMapper implements ActivityMapper {
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;
	private TicklerView<Tickler> ticklerView;
	private List<ColumnDefinition<Tickler>> ticklerColumnDefinitions;
	private PatientSummaryView<EHRProxy> patientSummaryView;
	private List<ColumnDefinition<EHRProxy>> patientSummaryColumnDefinitions;
	private MessageView<MessageProxy> messageView;
	private DemographicsView<DemographicsProxy> demographicsView;
	private List<ColumnDefinition<DemographicsProxy>> demographicsColumnDefinitions;
	private List<ColumnDefinition<ReferralProxy>> referralColumnDefinitions;
	private ReferralView<ReferralProxy> referralView;
	private AppointmentView<AppointmentProxy> appointmentView;
	private DiagnosisView<DiagnosisProxy> diagnosisView;
	private List<ColumnDefinition<DiagnosisProxy>> diagnosisColumnDefinitions;
	private LinkView<LinkProxy> linkView;
	private AddressView<AddressProxy> addressView;
	private MedicationView<MedicationProxy> medicationView;
	private ARInfoView<ARInfoProxy> arInfoView;
	private List<ColumnDefinition<ARInfoProxy>> arInfoColumnDefinitions;
	private DashboardView<LinkedHashMap<String, Long>> dashboardView;
	private ProfileView<UserProxy> profileView;
	private List<ColumnDefinition<UserProxy>> profileColumnDefinitions;

	@Inject
	public MasterActivityMapper(EchartsRequestFactory requestFactory,
							     PlaceController placeController,
							     TicklerView<Tickler> ticklerView,
							     List<ColumnDefinition<Tickler>> ticklerColumnDefinitions,
							     PatientSummaryView<EHRProxy> patientSummaryView,
							     List<ColumnDefinition<EHRProxy>> patientSummaryColumnDefinitions,
							     MessageView<MessageProxy> messageView,
							     DemographicsView<DemographicsProxy> demographicsView,
							     List<ColumnDefinition<DemographicsProxy>> demographicsColumnDefinitions,
							     ReferralView<ReferralProxy> referralView,
							     List<ColumnDefinition<ReferralProxy>> referralColumnDefinitions,
							     AppointmentView<AppointmentProxy> appointmentView,
							     DiagnosisView<DiagnosisProxy> diagnosisView,
							     List<ColumnDefinition<DiagnosisProxy>> diagnosisColumnDefinitions,
							     LinkView<LinkProxy> linkView,
							     AddressView<AddressProxy> addressView,
							     MedicationView<MedicationProxy> medicationView,
							     ARInfoView<ARInfoProxy> arInfoView,
							     List<ColumnDefinition<ARInfoProxy>> arInfoColumnDefinitions,
							     DashboardView<LinkedHashMap<String, Long>> dashboardView,
							     ProfileView<UserProxy> profileView,
							     List<ColumnDefinition<UserProxy>> profileColumnDefinitions) {
		super();
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.ticklerView = ticklerView;
		this.ticklerColumnDefinitions = ticklerColumnDefinitions;
		this.patientSummaryView = patientSummaryView;
		this.patientSummaryColumnDefinitions = patientSummaryColumnDefinitions;
		this.messageView = messageView;
		this.demographicsView = demographicsView;
		this.demographicsColumnDefinitions = demographicsColumnDefinitions;
		this.referralView = referralView;
		this.referralColumnDefinitions = referralColumnDefinitions;
		this.appointmentView = appointmentView;
		this.diagnosisView = diagnosisView;
		this.diagnosisColumnDefinitions = diagnosisColumnDefinitions;
		this.linkView = linkView;
		this.addressView = addressView;
		this.medicationView = medicationView;
		this.arInfoView = arInfoView;
		this.arInfoColumnDefinitions = arInfoColumnDefinitions;
		this.dashboardView = dashboardView;
		this.profileView = profileView;
		this.profileColumnDefinitions = profileColumnDefinitions;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace)
			return new TicklerActivity((TicklerPlace) place, requestFactory, ticklerColumnDefinitions, placeController, ticklerView);
		else if (place instanceof PatientSummaryPlace)
			return new PatientSummaryActivity((PatientSummaryPlace) place, requestFactory, patientSummaryColumnDefinitions, patientSummaryView);
		else if (place instanceof MessagePlace)
			return new MessageActivity((MessagePlace) place, requestFactory, messageView);
		else if (place instanceof DemographicsPlace)
			return new DemographicsActivity((DemographicsPlace) place, requestFactory, demographicsColumnDefinitions, demographicsView);
		else if (place instanceof ReferralPlace)
			return new ReferralActivity((ReferralPlace) place, requestFactory, referralColumnDefinitions, referralView);
		else if (place instanceof AppointmentPlace)
			return new AppointmentActivity((AppointmentPlace) place, requestFactory, appointmentView);
		else if (place instanceof DiagnosisPlace)
			return new DiagnosisActivity((DiagnosisPlace) place, requestFactory, diagnosisColumnDefinitions, diagnosisView);
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
			return new ARInfoActivity((ARInfoPlace) place, requestFactory, arInfoColumnDefinitions, arInfoView);
		else if (place instanceof DashboardPlace)
			return new DashboardActivity((DashboardPlace) place, placeController, dashboardView, requestFactory);
		else if (place instanceof ProfilePlace)
			return new ProfileActivity((ProfilePlace) place, profileColumnDefinitions, requestFactory, profileView);
		else if (place instanceof LabPlace)
			return new LabActivity((LabPlace) place);
		else if (place instanceof ProviderSignaturesPlace)
			return new ProviderSignaturesActivity();
		else if (place instanceof SupervisorSignaturesPlace)
			return new SupervisorSignaturesActivity();
		else if (place instanceof MedSomSignaturesPlace)
			return new MedSomSignaturesActivity();
		else if (place instanceof StaffHistoryPlace)
			return new StaffHistoryActivity();
		return null;
	}

}
