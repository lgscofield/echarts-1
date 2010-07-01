package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetDemographics implements Action<GetDemographicsResult> {

	GetDemographics() { }

	private String caseNumber;

	public GetDemographics(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
