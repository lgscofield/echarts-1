package org.eastway.echarts.client.place;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class EchartsPlaceController extends PlaceController {
	@Inject
	public EchartsPlaceController(EventBus eventBus) {
		super(eventBus);
	}
}
