package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DiagnosisPlace extends Place {
	private String caseNumber;

	public DiagnosisPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public static class Tokenizer implements PlaceTokenizer<DiagnosisPlace> {
		@Override
		public DiagnosisPlace getPlace(String token) {
			return new DiagnosisPlace(token);
		}

		@Override
		public String getToken(DiagnosisPlace place) {
			return place.getCaseNumber();
		}
	}
}
