package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TreatmentPlanPlace extends Place {
	private String caseNumber;

	public TreatmentPlanPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("treatmentplan")
	public static class Tokenizer implements PlaceTokenizer<TreatmentPlanPlace> {
		@Override
		public TreatmentPlanPlace getPlace(String token) {
			return new TreatmentPlanPlace(token);
		}

		@Override
		public String getToken(TreatmentPlanPlace place) {
			return place.getCaseNumber();
		}
	}
}
