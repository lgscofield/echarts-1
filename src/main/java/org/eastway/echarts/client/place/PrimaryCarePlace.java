package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PrimaryCarePlace extends Place {
	private String caseNumber;

	public PrimaryCarePlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("pc")
	public static class Tokenizer implements PlaceTokenizer<PrimaryCarePlace> {
		@Override
		public PrimaryCarePlace getPlace(String token) {
			return new PrimaryCarePlace(token);
		}

		@Override
		public String getToken(PrimaryCarePlace place) {
			return place.getCaseNumber();
		}
	}
}
