package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Result;

public class SaveProfileResult implements Result {

	private User user;

	public SaveProfileResult() { }

	public SaveProfileResult(User user) {
		this.setUser(user);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
