package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetContacts implements Action<GetContactsResult> {

	private String sessionId;
	private String caseNumber;

	GetContacts() { }

	public GetContacts(String sessionId, String caseNumber) {
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
