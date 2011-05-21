package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PlaceLogRecordListPlace extends Place {
	@Prefix("plr")
	public static class Tokenizer implements PlaceTokenizer<PlaceLogRecordListPlace> {
		@Override
		public PlaceLogRecordListPlace getPlace(String token) {
			return new PlaceLogRecordListPlace();
		}

		@Override
		public String getToken(PlaceLogRecordListPlace place) {
			return "";
		}
	}
}
