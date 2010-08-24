package org.eastway.echarts.shared;

import java.io.Serializable;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

@SuppressWarnings("serial")
public class PatientListSuggestion implements Suggestion, Serializable {

	private String s;

	public PatientListSuggestion() { }

	public PatientListSuggestion(String s) {
		this.s = s;
	}

	@Override
	public String getDisplayString() {
		return s;
	}

	@Override
	public String getReplacementString() {
		return s;
	}

}
