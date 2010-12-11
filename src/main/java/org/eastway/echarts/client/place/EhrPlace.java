package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EhrPlace extends Place {

	private String caseNumber;

	public EhrPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public static class Tokenizer implements PlaceTokenizer<EhrPlace> {
		@Override
		public EhrPlace getPlace(String token) {
			return new EhrPlace(token);
		}

		@Override
		public String getToken(EhrPlace place) {
			return place.getCaseNumber();
		}
	}
}
