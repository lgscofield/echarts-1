package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProfilePlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<ProfilePlace> {
		@Override
		public ProfilePlace getPlace(String token) {
			return new ProfilePlace();
		}

		@Override
		public String getToken(ProfilePlace place) {
			return "";
		}
	}
}
