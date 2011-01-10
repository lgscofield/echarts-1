package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProviderSignaturesPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<ProviderSignaturesPlace> {
		@Override
		public ProviderSignaturesPlace getPlace(String token) {
			return new ProviderSignaturesPlace();
		}

		@Override
		public String getToken(ProviderSignaturesPlace place) {
			return "";
		}
	}
}
