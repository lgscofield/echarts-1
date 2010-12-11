package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MedicationPlace extends Place {
	private String caseNumber;

	public MedicationPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public static class Tokenizer implements PlaceTokenizer<MedicationPlace> {
		@Override
		public MedicationPlace getPlace(String token) {
			return new MedicationPlace(token);
		}

		@Override
		public String getToken(MedicationPlace place) {
			return place.getCaseNumber();
		}
	}
}
