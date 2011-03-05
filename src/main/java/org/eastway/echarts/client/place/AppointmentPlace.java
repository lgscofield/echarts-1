package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AppointmentPlace extends Place {
	private String caseNumber;

	public AppointmentPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("appointment")
	public static class Tokenizer implements PlaceTokenizer<AppointmentPlace> {
		@Override
		public AppointmentPlace getPlace(String token) {
			return new AppointmentPlace(token);
		}

		@Override
		public String getToken(AppointmentPlace place) {
			return place.getCaseNumber();
		}
	}
}
