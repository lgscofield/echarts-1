package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DashboardFramePlace extends Place {
	private String url;

	public DashboardFramePlace(String url) {
		this.url = url;
	}

	public static class Tokenizer implements PlaceTokenizer<DashboardFramePlace> {
		@Override
		public DashboardFramePlace getPlace(String token) {
			return new DashboardFramePlace(token);
		}

		@Override
		public String getToken(DashboardFramePlace place) {
			return place.getUrl();
		}
	}

	public String getUrl() {
		return url;
	}
}
