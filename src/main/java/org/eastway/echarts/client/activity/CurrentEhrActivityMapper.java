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
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.StaffAnalysisPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.CurrentEhrView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class CurrentEhrActivityMapper implements ActivityMapper {

	private EchartsRequestFactory requestFactory;
	private CurrentEhrView view;

	@Inject
	public CurrentEhrActivityMapper(EchartsRequestFactory requestFactory, CurrentEhrView view) {
		this.requestFactory = requestFactory;
		this.view = view;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof PatientSummaryPlace) {
			return new CurrentEhrActivity(((PatientSummaryPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof MessagePlace) {
			return new CurrentEhrActivity(((MessagePlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof DemographicsPlace) {
			return new CurrentEhrActivity(((DemographicsPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof ReferralPlace) {
			return new CurrentEhrActivity(((ReferralPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof AppointmentPlace) {
			return new CurrentEhrActivity(((AppointmentPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof DiagnosisPlace) {
			return new CurrentEhrActivity(((DiagnosisPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof LinkPlace) {
			return new CurrentEhrActivity(((LinkPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof AddressPlace) {
			return new CurrentEhrActivity(((AddressPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof MedicationPlace) {
			return new CurrentEhrActivity(((MedicationPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof TreatmentPlanPlace) {
			return new CurrentEhrActivity(((TreatmentPlanPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof ServiceHistoryPlace) {
			return new CurrentEhrActivity(((ServiceHistoryPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof ARInfoPlace) {
			return new CurrentEhrActivity(((ARInfoPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof DashboardPlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof ProfilePlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof LabPlace) {
			return new CurrentEhrActivity(((LabPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof PhysicianOrderPlace) {
			return new CurrentEhrActivity(((PhysicianOrderPlace) place).getCaseNumber(), view, requestFactory);
		} else if (place instanceof StaffAnalysisPlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof DashboardFramePlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof AppointmentReportListPlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		} else if (place instanceof EhrQueryListPlace) {
			return new CurrentEhrActivity(null, view, requestFactory);
		}
		return null;
	}
}
