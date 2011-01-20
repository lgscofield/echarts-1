package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PhysicianOrderQueueReportPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<PhysicianOrderQueueReportPlace> {
		@Override
		public PhysicianOrderQueueReportPlace getPlace(String token) {
			return new PhysicianOrderQueueReportPlace();
		}

		@Override
		public String getToken(PhysicianOrderQueueReportPlace place) {
			return "";
		}
	}
}
