package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class LabPlace extends Place {
	private String caseNumber;

	public LabPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("lab")
	public static class Tokenizer implements PlaceTokenizer<LabPlace> {
		@Override
		public LabPlace getPlace(String token) {
			return new LabPlace(token);
		}

		@Override
		public String getToken(LabPlace place) {
			return place.getCaseNumber();
		}
	}
}
