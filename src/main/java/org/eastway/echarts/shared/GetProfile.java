/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

public class GetProfile implements Action<GetProfileResult> {

	private String sessionId;
	private String username;

	public GetProfile() { }

	public GetProfile(String sessionId, String username) {
		this.setSessionId(sessionId);
		this.setUsername(username);
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getUsername() {
		return username;
	}
}