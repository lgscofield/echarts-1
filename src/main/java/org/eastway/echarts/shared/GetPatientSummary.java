package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetPatientSummary implements Action<GetPatientSummaryResult> {

	private String sessionId;
	private String caseNumber;

	GetPatientSummary() { }

	public GetPatientSummary(String sessionId, String caseNumber) {
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
