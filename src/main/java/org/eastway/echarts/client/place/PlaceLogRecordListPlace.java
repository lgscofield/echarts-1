package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PlaceLogRecordListPlace extends Place {
	private String token = "";

	public String getToken() {
		return token;
	}

	public PlaceLogRecordListPlace(String token) {
		this.token = token;
	}

	@Prefix("plr")
	public static class Tokenizer implements PlaceTokenizer<PlaceLogRecordListPlace> {
		@Override
		public PlaceLogRecordListPlace getPlace(String token) {
			return new PlaceLogRecordListPlace(token);
		}

		@Override
		public String getToken(PlaceLogRecordListPlace place) {
			return place.getToken();
		}
	}
}
