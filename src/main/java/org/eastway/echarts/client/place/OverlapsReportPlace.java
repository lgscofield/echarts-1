package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class OverlapsReportPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<OverlapsReportPlace> {
		@Override
		public OverlapsReportPlace getPlace(String token) {
			return new OverlapsReportPlace();
		}

		@Override
		public String getToken(OverlapsReportPlace place) {
			return "";
		}
	}
}
