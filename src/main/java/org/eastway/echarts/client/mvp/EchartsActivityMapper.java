package org.eastway.echarts.client.mvp;

import java.util.List;

import org.eastway.echarts.client.activity.ARInfoActivity;
import org.eastway.echarts.client.activity.AddressActivity;
import org.eastway.echarts.client.activity.AppointmentActivity;
import org.eastway.echarts.client.activity.DemographicsActivity;
import org.eastway.echarts.client.activity.DiagnosisActivity;
import org.eastway.echarts.client.activity.EhrActivity;
import org.eastway.echarts.client.activity.LinkActivity;
import org.eastway.echarts.client.activity.MedicationActivity;
import org.eastway.echarts.client.activity.MessageActivity;
import org.eastway.echarts.client.activity.PatientSummaryActivity;
import org.eastway.echarts.client.activity.ReferralActivity;
import org.eastway.echarts.client.activity.ServiceHistoryActivity;
import org.eastway.echarts.client.activity.TicklerActivity;
import org.eastway.echarts.client.activity.TreatmentPlanActivity;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.EhrPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.rpc.ARInfoProxy;
import org.eastway.echarts.client.rpc.AddressProxy;
import org.eastway.echarts.client.rpc.AppointmentProxy;
import org.eastway.echarts.client.rpc.DemographicsProxy;
import org.eastway.echarts.client.rpc.DiagnosisProxy;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.LinkProxy;
import org.eastway.echarts.client.rpc.MedicationProxy;
import org.eastway.echarts.client.rpc.MessageProxy;
import org.eastway.echarts.client.rpc.ReferralProxy;
import org.eastway.echarts.client.scaffold.ioc.ScaffoldInjector;
import org.eastway.echarts.client.ui.ARInfoView;
import org.eastway.echarts.client.ui.AddressView;
import org.eastway.echarts.client.ui.LinkView;
import org.eastway.echarts.client.ui.MedicationView;
import org.eastway.echarts.client.ui.MessageView;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.DiagnosisView;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.client.view.ReferralView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class EchartsActivityMapper implements ActivityMapper {
	private ScaffoldInjector clientFactory;
	private EchartsRequestFactory requestFactory;
	private EventBus eventBus;
	private PlaceController placeController;
	private TicklerView<Tickler> ticklerView;
	private List<ColumnDefinition<Tickler>> ticklerColumnDefinitions;
	private EHRView<EHRProxy> ehrView;
	private PatientSummaryView<EHRProxy> patientSummaryView;
	private List<ColumnDefinition<EHRProxy>> patientSummaryColumnDefinitions;
	private MessageView<MessageProxy> messageView;
	private DemographicsView<DemographicsProxy> demographicsView;
	private List<ColumnDefinition<DemographicsProxy>> demographicsColumnDefinitions;
	private List<ColumnDefinition<ReferralProxy>> referralColumnDefinitions;
	private ReferralView<ReferralProxy> referralView;
	private AppointmentView<AppointmentProxy> appointmentView;
	private List<ColumnDefinition<AppointmentProxy>> appointmentColumnDefinitions;
	private DiagnosisView<DiagnosisProxy> diagnosisView;
	private List<ColumnDefinition<DiagnosisProxy>> diagnosisColumnDefinitions;
	private LinkView<LinkProxy> linkView;
	private AddressView<AddressProxy> addressView;
	private List<ColumnDefinition<AddressProxy>> addressColumnDefinitions;
	private MedicationView<MedicationProxy> medicationView;
	private ARInfoView<ARInfoProxy> arInfoView;
	private List<ColumnDefinition<ARInfoProxy>> arInfoColumnDefinitions;

	@Inject
	public EchartsActivityMapper(EchartsRequestFactory requestFactory,
							     EventBus eventBus,
							     PlaceController placeController,
							     TicklerView<Tickler> ticklerView,
							     List<ColumnDefinition<Tickler>> ticklerColumnDefinitions,
							     EHRView<EHRProxy> ehrView,
							     PatientSummaryView<EHRProxy> patientSummaryView,
							     List<ColumnDefinition<EHRProxy>> patientSummaryColumnDefinitions,
							     MessageView<MessageProxy> messageView,
							     DemographicsView<DemographicsProxy> demographicsView,
							     List<ColumnDefinition<DemographicsProxy>> demographicsColumnDefinitions,
							     ReferralView<ReferralProxy> referralView,
							     List<ColumnDefinition<ReferralProxy>> referralColumnDefinitions,
							     AppointmentView<AppointmentProxy> appointmentView,
							     List<ColumnDefinition<AppointmentProxy>> appointmentColumnDefinitions,
							     DiagnosisView<DiagnosisProxy> diagnosisView,
							     List<ColumnDefinition<DiagnosisProxy>> diagnosisColumnDefinitions,
							     LinkView<LinkProxy> linkView,
							     AddressView<AddressProxy> addressView,
							     List<ColumnDefinition<AddressProxy>> addressColumnDefinitions,
							     MedicationView<MedicationProxy> medicationView,
							     ARInfoView<ARInfoProxy> arInfoView,
							     List<ColumnDefinition<ARInfoProxy>> arInfoColumnDefinitions) {
		super();
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.ticklerView = ticklerView;
		this.ticklerColumnDefinitions = ticklerColumnDefinitions;
		this.ehrView = ehrView;
		this.patientSummaryView = patientSummaryView;
		this.patientSummaryColumnDefinitions = patientSummaryColumnDefinitions;
		this.messageView = messageView;
		this.demographicsView = demographicsView;
		this.demographicsColumnDefinitions = demographicsColumnDefinitions;
		this.referralView = referralView;
		this.referralColumnDefinitions = referralColumnDefinitions;
		this.appointmentView = appointmentView;
		this.appointmentColumnDefinitions = appointmentColumnDefinitions;
		this.diagnosisView = diagnosisView;
		this.diagnosisColumnDefinitions = diagnosisColumnDefinitions;
		this.linkView = linkView;
		this.addressView = addressView;
		this.medicationView = medicationView;
		this.addressColumnDefinitions = addressColumnDefinitions;
		this.arInfoView = arInfoView;
		this.arInfoColumnDefinitions = arInfoColumnDefinitions;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace)
			return new TicklerActivity((TicklerPlace) place, requestFactory, eventBus, ticklerColumnDefinitions, placeController, ticklerView);
		else if (place instanceof EhrPlace)
			return new EhrActivity((EhrPlace) place, requestFactory, eventBus, placeController, ehrView);
		else if (place instanceof PatientSummaryPlace)
			return new PatientSummaryActivity((PatientSummaryPlace) place, requestFactory, patientSummaryColumnDefinitions, patientSummaryView);
		else if (place instanceof MessagePlace)
			return new MessageActivity((MessagePlace) place, requestFactory, messageView);
		else if (place instanceof DemographicsPlace)
			return new DemographicsActivity((DemographicsPlace) place, requestFactory, demographicsColumnDefinitions, demographicsView);
		else if (place instanceof ReferralPlace)
			return new ReferralActivity((ReferralPlace) place, requestFactory, referralColumnDefinitions, referralView);
		else if (place instanceof AppointmentPlace)
			return new AppointmentActivity((AppointmentPlace) place, requestFactory, appointmentColumnDefinitions, appointmentView);
		else if (place instanceof DiagnosisPlace)
			return new DiagnosisActivity((DiagnosisPlace) place, requestFactory, diagnosisColumnDefinitions, diagnosisView);
		else if (place instanceof LinkPlace)
			return new LinkActivity((LinkPlace) place, requestFactory, linkView);
		else if (place instanceof AddressPlace)
			return new AddressActivity((AddressPlace) place, requestFactory, addressColumnDefinitions, addressView);
		else if (place instanceof MedicationPlace)
			return new MedicationActivity((MedicationPlace) place, requestFactory, medicationView);
		else if (place instanceof TreatmentPlanPlace)
			return new TreatmentPlanActivity((TreatmentPlanPlace) place, clientFactory);
		else if (place instanceof ServiceHistoryPlace)
			return new ServiceHistoryActivity((ServiceHistoryPlace) place, clientFactory);
		else if (place instanceof ARInfoPlace)
			return new ARInfoActivity((ARInfoPlace) place, requestFactory, arInfoColumnDefinitions, arInfoView);
		return null;
	}

}
