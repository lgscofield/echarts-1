package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

public class SaveProfile implements Action<SaveProfileResult> {

	private String sessionId;
	private User user;

	public SaveProfile() { }

	public SaveProfile(String sessionId) {
		this.setSessionId(sessionId);
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
