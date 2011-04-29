package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AppointmentReportListPlace extends Place {
	@Prefix("ar")
	public static class Tokenizer implements PlaceTokenizer<AppointmentReportListPlace> {
		@Override
		public AppointmentReportListPlace getPlace(String token) {
			return new AppointmentReportListPlace();
		}

		@Override
		public String getToken(AppointmentReportListPlace place) {
			return "";
		}
	}
}
