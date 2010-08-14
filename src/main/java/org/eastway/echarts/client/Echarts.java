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
package org.eastway.echarts.client;

import org.eastway.echarts.style.client.GlobalResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Echarts implements EntryPoint {
	private final EchartsGinjector injector = GWT.create(EchartsGinjector.class);
	private RootLayoutPanel root = RootLayoutPanel.get();

	@Override
	public void onModuleLoad() {
		String sessionId = Cookies.getCookie("sessionId");
		String username = Cookies.getCookie("echarts_user");
		String staffId = Cookies.getCookie("staff_id");

		if ((sessionId == null || sessionId == "null") ||
				(username == null || username == "null") ||
				(staffId == null || staffId == "null"))
			Window.Location.assign("http://ewsql.eastway.local/echarts/logout.aspx?continue=" + Window.Location.getHref());
		else {
			EchartsUser.sessionId = sessionId;
			EchartsUser.userName = username;
			EchartsUser.staffId = staffId;
			//EchartsUser.staffId = "5434"; // for testing
			GlobalResources.resources().css().ensureInjected();
			go();
		}
	}

	public void go() {
		AppController app = injector.getAppController();
		app.go(root);
		injector.getPlaceManager().fireCurrentPlace();
	}
}
