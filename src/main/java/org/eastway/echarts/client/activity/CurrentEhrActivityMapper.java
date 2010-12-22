package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.CurrentEhrWidget;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class CurrentEhrActivityMapper implements ActivityMapper {

	private EchartsRequestFactory requestFactory;

	@Inject
	public CurrentEhrActivityMapper(EchartsRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace) {
			CurrentEhrWidget.instance().setEhr(null);
		} else if (place instanceof PatientSummaryPlace) {
			CurrentEhrWidget.instance().setEhr(((PatientSummaryPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof MessagePlace) {
			CurrentEhrWidget.instance().setEhr(((MessagePlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof DemographicsPlace) {
			CurrentEhrWidget.instance().setEhr(((DemographicsPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof ReferralPlace) {
			CurrentEhrWidget.instance().setEhr(((ReferralPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof AppointmentPlace) {
			CurrentEhrWidget.instance().setEhr(((AppointmentPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof DiagnosisPlace) {
			CurrentEhrWidget.instance().setEhr(((DiagnosisPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof LinkPlace) {
			CurrentEhrWidget.instance().setEhr(((LinkPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof AddressPlace) {
			CurrentEhrWidget.instance().setEhr(((AddressPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof MedicationPlace) {
			CurrentEhrWidget.instance().setEhr(((MedicationPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof TreatmentPlanPlace) {
			CurrentEhrWidget.instance().setEhr(((TreatmentPlanPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof ServiceHistoryPlace) {
			CurrentEhrWidget.instance().setEhr(((ServiceHistoryPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof ARInfoPlace) {
			CurrentEhrWidget.instance().setEhr(((ARInfoPlace) place).getCaseNumber(), requestFactory);
			return CurrentEhrWidget.instance();
		} else if (place instanceof DashboardPlace) {
			CurrentEhrWidget.instance().setEhr(null);
		} else if (place instanceof ProfilePlace) {
			CurrentEhrWidget.instance().setEhr(null);
		}
		return null;
	}
}
