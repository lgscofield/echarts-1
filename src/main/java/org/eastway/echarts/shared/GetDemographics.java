package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetDemographics implements Action<GetDemographicsResult> {

	GetDemographics() { }

	private String caseNumber;
	private String sessionId;

	public GetDemographics(String sessionId, String caseNumber) {
		this.sessionId = sessionId;
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public String getSessionId() {
		return sessionId;
	}
}
