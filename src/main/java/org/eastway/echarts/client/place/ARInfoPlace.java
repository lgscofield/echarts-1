package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ARInfoPlace extends Place {
	private String caseNumber;

	public ARInfoPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("arinfo")
	public static class Tokenizer implements PlaceTokenizer<ARInfoPlace> {
		@Override
		public ARInfoPlace getPlace(String token) {
			return new ARInfoPlace(token);
		}

		@Override
		public String getToken(ARInfoPlace place) {
			return place.getCaseNumber();
		}
	}
}
