package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DashboardPlace extends Place {
	@Prefix("")
	public static class Tokenizer implements PlaceTokenizer<DashboardPlace> {
		@Override
		public DashboardPlace getPlace(String token) {
			return new DashboardPlace();
		}

		@Override
		public String getToken(DashboardPlace place) {
			return "";
		}
	}
}
