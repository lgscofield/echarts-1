package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SupervisorSignaturesPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<SupervisorSignaturesPlace> {
		@Override
		public SupervisorSignaturesPlace getPlace(String token) {
			return new SupervisorSignaturesPlace();
		}

		@Override
		public String getToken(SupervisorSignaturesPlace place) {
			return "";
		}
	}
}
