package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class MessagePlace extends Place {
	private String caseNumber;

	public MessagePlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("message")
	public static class Tokenizer implements PlaceTokenizer<MessagePlace> {
		@Override
		public MessagePlace getPlace(String token) {
			return new MessagePlace(token);
		}

		@Override
		public String getToken(MessagePlace place) {
			return place.getCaseNumber();
		}
	}
}
