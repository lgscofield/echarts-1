package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class LinkPlace extends Place {
	private String caseNumber;

	public LinkPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("link")
	public static class Tokenizer implements PlaceTokenizer<LinkPlace> {
		@Override
		public LinkPlace getPlace(String token) {
			return new LinkPlace(token);
		}

		@Override
		public String getToken(LinkPlace place) {
			return place.getCaseNumber();
		}
	}
}
