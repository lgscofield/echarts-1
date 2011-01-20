package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LastSeenReportPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<LastSeenReportPlace> {
		@Override
		public LastSeenReportPlace getPlace(String token) {
			return new LastSeenReportPlace();
		}

		@Override
		public String getToken(LastSeenReportPlace place) {
			return "";
		}
	}
}
