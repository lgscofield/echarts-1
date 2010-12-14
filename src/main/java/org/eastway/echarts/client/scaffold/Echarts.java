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
package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.scaffold.ioc.DesktopInjectorWrapper;
import org.eastway.echarts.client.scaffold.ioc.InjectorWrapper;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;

public class Echarts implements EntryPoint {
	final private InjectorWrapper injectorWrapper = GWT.create(DesktopInjectorWrapper.class);

	@Override
	public void onModuleLoad() {
		EchartsUser.sessionId = Cookies.getCookie("session_id");
		EchartsUser.userName = Cookies.getCookie("echarts_user");
		EchartsUser.staffId = Cookies.getCookie("staff_id");
		GlobalResources.resources().css().ensureInjected();

		injectorWrapper.getInjector().getScaffoldApp().run();
	}
}
