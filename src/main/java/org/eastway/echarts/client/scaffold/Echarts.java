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
import org.eastway.echarts.style.client.GlobalResources;

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
		EchartsUser.userName = "ihilt";
		EchartsUser.sessionId = "CDB13CFCFA0A2274438F3B25C9FD466AB152D28B27986D934154E8A041BAFAF3BE53AE6DC8746264BB21F83D6BF111FE30E028A8A130FF4317A7E03E12FCB8C686DC3FC499733B123E9F74F8A3F2B353";
		EchartsUser.staffId = "5434"; // for testing
		GlobalResources.resources().css().ensureInjected();

		injectorWrapper.getInjector().getScaffoldApp().run();
	}
}
