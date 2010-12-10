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

import org.eastway.echarts.client.place.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.style.client.GlobalResources;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Echarts implements EntryPoint {
	private SimplePanel appWidget = new SimplePanel();
	private TicklerPlace defaultPlace = new TicklerPlace();

	@Override
	public void onModuleLoad() {
		RootPanel.get("page-loading-message").setVisible(false);
		EchartsUser.sessionId = Cookies.getCookie("session_id");
		EchartsUser.userName = Cookies.getCookie("echarts_user");
		EchartsUser.staffId = Cookies.getCookie("staff_id");
		//EchartsUser.staffId = "5434"; // for testing
		GlobalResources.resources().css().ensureInjected();
		Window.enableScrolling(false);

		EchartsClientFactory clientFactory = GWT.create(EchartsClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new EchartsActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        EchartsPlaceHistoryMapper historyMapper= GWT.create(EchartsPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        clientFactory.getRequestFactory().initialize(eventBus);

        RootPanel.get().add(appWidget);
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
	}
}
