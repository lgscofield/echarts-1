package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AddressPlace extends Place {
	private String caseNumber;

	public AddressPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("address")
	public static class Tokenizer implements PlaceTokenizer<AddressPlace> {
		@Override
		public AddressPlace getPlace(String token) {
			return new AddressPlace(token);
		}

		@Override
		public String getToken(AddressPlace place) {
			return place.getCaseNumber();
		}
	}
}
