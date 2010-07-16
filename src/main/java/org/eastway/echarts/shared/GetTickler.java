package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetTickler implements Action<GetTicklerResult> {

	private String staffId;
	private String sessionId;

	GetTickler() { }

	public GetTickler(String sessionId, String staffId) {
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
