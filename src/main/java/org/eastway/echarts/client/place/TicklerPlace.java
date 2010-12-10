package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TicklerPlace extends Place {
	private String ticklerName;

	public TicklerPlace(String token) {
		this.ticklerName = token;
	}

	public TicklerPlace() {
	}

	public String getTicklerName() {
		return ticklerName;
	}

	public static class Tokenizer implements PlaceTokenizer<TicklerPlace> {
		@Override
		public TicklerPlace getPlace(String token) {
			return new TicklerPlace(token);
		}

		@Override
		public String getToken(TicklerPlace place) {
			return place.getTicklerName();
		}
	}
}
