package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PatientSummaryPlace extends Place {

	private String caseNumber;

	public PatientSummaryPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("patientsummary")
	public static class Tokenizer implements PlaceTokenizer<PatientSummaryPlace> {
		@Override
		public PatientSummaryPlace getPlace(String token) {
			return new PatientSummaryPlace(token);
		}

		@Override
		public String getToken(PatientSummaryPlace place) {
			return place.getCaseNumber();
		}
	}
}
