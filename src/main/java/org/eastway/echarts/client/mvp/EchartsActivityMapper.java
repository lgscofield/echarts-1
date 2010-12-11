package org.eastway.echarts.client.mvp;

import org.eastway.echarts.client.EchartsClientFactory;
import org.eastway.echarts.client.activity.EhrActivity;
import org.eastway.echarts.client.activity.MessageActivity;
import org.eastway.echarts.client.activity.PatientSummaryActivity;
import org.eastway.echarts.client.activity.TicklerActivity;
import org.eastway.echarts.client.place.EhrPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
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
		return null;
	}

}
