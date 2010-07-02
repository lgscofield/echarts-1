package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetAssignments implements Action<GetAssignmentsResult> {

	private String staffId;
	private String sessionId;

	GetAssignments() { }

	public GetAssignments(String sessionId, String staffId) {
		this.sessionId = sessionId;
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public String getSessionId() {
		return sessionId;
	}
}
