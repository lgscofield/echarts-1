package org.eastway.echarts.client;

import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.presenter.TicklerPresenter;

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
			return new TicklerPresenter((TicklerPlace) place, clientFactory);
		return null;
	}

}
