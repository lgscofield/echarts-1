package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StaffHistoryPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<StaffHistoryPlace> {
		@Override
		public StaffHistoryPlace getPlace(String token) {
			return new StaffHistoryPlace();
		}

		@Override
		public String getToken(StaffHistoryPlace place) {
			return "";
		}
	}
}
