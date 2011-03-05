package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DemographicsPlace extends Place {
	private String caseNumber;

	public DemographicsPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("demographics")
	public static class Tokenizer implements PlaceTokenizer<DemographicsPlace> {
		@Override
		public DemographicsPlace getPlace(String token) {
			return new DemographicsPlace(token);
		}

		@Override
		public String getToken(DemographicsPlace place) {
			return place.getCaseNumber();
		}
	}
}
