package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ReferralPlace extends Place {
	private String caseNumber;

	public ReferralPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public static class Tokenizer implements PlaceTokenizer<ReferralPlace> {
		@Override
		public ReferralPlace getPlace(String token) {
			return new ReferralPlace(token);
		}

		@Override
		public String getToken(ReferralPlace place) {
			return place.getCaseNumber();
		}
	}
}
