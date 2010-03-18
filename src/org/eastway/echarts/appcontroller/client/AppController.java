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
