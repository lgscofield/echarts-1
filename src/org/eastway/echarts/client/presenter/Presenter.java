package org.eastway.echarts.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class Presenter<D extends EchartsDisplay> {
	protected D display;
	protected HandlerManager eventBus;

	public Presenter(D display, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
	}

	public abstract void go(final HasWidgets container);

	public D getDisplay() {
		return display;
	}

	public HandlerManager getEventBus() {
		return eventBus;
	}
}
