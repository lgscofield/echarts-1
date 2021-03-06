package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PhysicianOrderPlace extends Place {

	private String caseNumber;

	public PhysicianOrderPlace(String id) {
		this.caseNumber = id;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("physicianorder")
	public static class Tokenizer implements PlaceTokenizer<PhysicianOrderPlace> {
		@Override
		public PhysicianOrderPlace getPlace(String token) {
			return new PhysicianOrderPlace(token);
		}

		@Override
		public String getToken(PhysicianOrderPlace place) {
			return place.getCaseNumber();
		}
	}

}
