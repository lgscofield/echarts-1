package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MedSomSignaturesPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<MedSomSignaturesPlace> {
		@Override
		public MedSomSignaturesPlace getPlace(String token) {
			return new MedSomSignaturesPlace();
		}

		@Override
		public String getToken(MedSomSignaturesPlace place) {
			return "";
		}
	}
}
