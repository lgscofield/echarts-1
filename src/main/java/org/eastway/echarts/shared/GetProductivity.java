package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetProductivity implements Action<GetProductivityResult> {

	private String sessionId;
	private String staffId;

	GetProductivity() { }

	public GetProductivity(String sessionId, String staffId) {
		this.sessionId = sessionId;
		this.staffId = staffId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getStaffId() {
		return staffId;
	}
}
