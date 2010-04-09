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
package org.eastway.echarts.appcontroller.client;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class AppController implements ValueChangeHandler<String> {
	protected final HandlerManager eventBus;
	protected HasWidgets container;

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	public abstract void bind();

	public void go(final HasWidgets container) {
		this.container = container;

		if (History.getToken().equals("dashboard")) {
			History.fireCurrentHistoryState();
		} else if ("".equals(History.getToken())) {
			History.newItem("dashboard");
		}
	}
}
