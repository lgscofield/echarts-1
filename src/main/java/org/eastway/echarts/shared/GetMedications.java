package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetMedications implements Action<GetMedicationsResult> {

	private String sessionId;
	private String caseNumber;

	GetMedications() { }

	public GetMedications(String sessionId, String caseNumber) {
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
