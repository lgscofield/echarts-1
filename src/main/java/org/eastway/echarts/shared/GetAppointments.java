package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetAppointments implements Action<GetAppointmentsResult> {

	private String sessionId;
	private String caseNumber;

	GetAppointments() { }

	public GetAppointments(String sessionId, String caseNumber) {
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
