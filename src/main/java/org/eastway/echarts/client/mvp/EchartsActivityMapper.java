package org.eastway.echarts.client.mvp;

import org.eastway.echarts.client.EchartsClientFactory;
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
import org.eastway.echarts.client.activity.TicklerActivity;
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
import org.eastway.echarts.client.place.TicklerPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class EchartsActivityMapper implements ActivityMapper {
	private EchartsClientFactory clientFactory;

	@Inject
	public EchartsActivityMapper(EchartsClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TicklerPlace)
			return new TicklerActivity((TicklerPlace) place, clientFactory);
		else if (place instanceof EhrPlace)
			return new EhrActivity((EhrPlace) place, clientFactory);
		else if (place instanceof PatientSummaryPlace)
			return new PatientSummaryActivity((PatientSummaryPlace) place, clientFactory);
		else if (place instanceof MessagePlace)
			return new MessageActivity((MessagePlace) place, clientFactory);
		else if (place instanceof DemographicsPlace)
			return new DemographicsActivity((DemographicsPlace) place, clientFactory);
		else if (place instanceof ReferralPlace)
			return new ReferralActivity((ReferralPlace) place, clientFactory);
		else if (place instanceof AppointmentPlace)
			return new AppointmentActivity((AppointmentPlace) place, clientFactory);
		else if (place instanceof DiagnosisPlace)
			return new DiagnosisActivity((DiagnosisPlace) place, clientFactory);
		else if (place instanceof LinkPlace)
			return new LinkActivity((LinkPlace) place, clientFactory);
		else if (place instanceof AddressPlace)
			return new AddressActivity((AddressPlace) place, clientFactory);
		else if (place instanceof MedicationPlace)
			return new MedicationActivity((MedicationPlace) place, clientFactory);
		return null;
	}

}
