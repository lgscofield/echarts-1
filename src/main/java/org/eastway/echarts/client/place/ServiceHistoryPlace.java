package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ServiceHistoryPlace extends Place {
	private String caseNumber;

	public ServiceHistoryPlace(String token) {
		this.caseNumber = token;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	@Prefix("servicehistory")
	public static class Tokenizer implements PlaceTokenizer<ServiceHistoryPlace> {
		@Override
		public ServiceHistoryPlace getPlace(String token) {
			return new ServiceHistoryPlace(token);
		}

		@Override
		public String getToken(ServiceHistoryPlace place) {
			return place.getCaseNumber();
		}
	}
}
