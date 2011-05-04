package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class EhrQueryListPlace extends Place {
	private String query;

	public EhrQueryListPlace(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	@Prefix("pq")
	public static class Tokenizer implements PlaceTokenizer<EhrQueryListPlace> {
		@Override
		public EhrQueryListPlace getPlace(String token) {
			return new EhrQueryListPlace(token);
		}

		@Override
		public String getToken(EhrQueryListPlace place) {
			return place.getQuery();
		}
	}
}
