package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetDiagnoses implements Action<GetDiagnosesResult> {

	private String sessionId;
	private String caseNumber;

	GetDiagnoses() { }

	public GetDiagnoses(String sessionId, String caseNumber) {
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
