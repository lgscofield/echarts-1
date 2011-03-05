package org.eastway.echarts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class StaffAnalysisPlace extends Place {
	private String staffId;

	public StaffAnalysisPlace(String staffId) {
		this.staffId = staffId;
	}

	@Prefix("staffanalysis")
	public static class Tokenizer implements PlaceTokenizer<StaffAnalysisPlace> {
		@Override
		public StaffAnalysisPlace getPlace(String token) {
			return new StaffAnalysisPlace(token);
		}

		@Override
		public String getToken(StaffAnalysisPlace place) {
			return place.getStaffId();
		}
	}

	public String getStaffId() {
		return staffId;
	}
}
