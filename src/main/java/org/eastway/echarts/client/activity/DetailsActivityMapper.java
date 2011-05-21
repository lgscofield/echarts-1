package org.eastway.echarts.client.activity;

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
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.DashboardSideBarView;
import org.eastway.echarts.client.ui.EhrSideBarView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class DetailsActivityMapper implements ActivityMapper {

	private EchartsRequestFactory requestFactory;
	private EventBus eventBus;
	private PlaceController placeController;
	private DashboardSideBarView dashboardSideBarView;
	private EhrSideBarView ehrSideBarView;

	@Inject
	public DetailsActivityMapper(EchartsRequestFactory requestFactory,
							     EventBus eventBus,
							     PlaceController placeController,
							     DashboardSideBarView dashboardSideBarView,
							     EhrSideBarView ehrSideBarView) {
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.dashboardSideBarView = dashboardSideBarView;
		this.ehrSideBarView = ehrSideBarView;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof PatientSummaryPlace) {
			ehrSideBarView.selectPatientSummaryPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((PatientSummaryPlace) place).getCaseNumber());
		} else if (place instanceof MessagePlace) {
			ehrSideBarView.selectMessagePlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((MessagePlace) place).getCaseNumber());
		} else if (place instanceof DemographicsPlace) {
			ehrSideBarView.selectDemographicsPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((DemographicsPlace) place).getCaseNumber());
		} else if (place instanceof ReferralPlace) {
			ehrSideBarView.selectReferralPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((ReferralPlace) place).getCaseNumber());
		} else if (place instanceof AppointmentPlace) {
			ehrSideBarView.selectAppointmentPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((AppointmentPlace) place).getCaseNumber());
		} else if (place instanceof DiagnosisPlace) {
			ehrSideBarView.selectDiagnosisPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((DiagnosisPlace) place).getCaseNumber());
		} else if (place instanceof LinkPlace) {
			ehrSideBarView.selectLinkPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((LinkPlace) place).getCaseNumber());
		} else if (place instanceof AddressPlace) {
			ehrSideBarView.selectAddressPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((AddressPlace) place).getCaseNumber());
		} else if (place instanceof MedicationPlace) {
			ehrSideBarView.selectMedicationPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((MedicationPlace) place).getCaseNumber());
		} else if (place instanceof TreatmentPlanPlace) {
			ehrSideBarView.selectTreatmentPlanPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((TreatmentPlanPlace) place).getCaseNumber());
		} else if (place instanceof ServiceHistoryPlace) {
			ehrSideBarView.selectServiceHistoryPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((ServiceHistoryPlace) place).getCaseNumber());
		} else if (place instanceof ARInfoPlace) {
			ehrSideBarView.selectARInfoPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((ARInfoPlace) place).getCaseNumber());
		} else if (place instanceof DashboardPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof ProfilePlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof LabPlace) {
			ehrSideBarView.selectLabPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((LabPlace) place).getCaseNumber());
		} else if (place instanceof PhysicianOrderPlace) {
			ehrSideBarView.selectPhysicianOrderPlace();
			return new EhrSideBarActivity(ehrSideBarView, placeController, ((PhysicianOrderPlace) place).getCaseNumber());
		} else if (place instanceof StaffAnalysisPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof DashboardFramePlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof AppointmentReportListPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof EhrQueryListPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		} else if (place instanceof PlaceLogRecordListPlace) {
			return new DashboardSideBarActivity(dashboardSideBarView, requestFactory, placeController);
		}
		return null;
	}

}
