package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetLinks implements Action<GetLinksResult> {

	private String sessionId;
	private String caseNumber;

	GetLinks() { }

	public GetLinks(String sessionId, String caseNumber) {
		this.sessionId = sessionId;
		this.caseNumber = caseNumber;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
