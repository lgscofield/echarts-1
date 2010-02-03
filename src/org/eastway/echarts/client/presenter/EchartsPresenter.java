package org.eastway.echarts.client.presenter;

import com.google.gwt.event.shared.HandlerManager;

public abstract class EchartsPresenter<D extends EchartsDisplay> {
	protected D display;
	protected HandlerManager eventBus;

	public EchartsPresenter(D display, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
	}

	public D getDisplay() {
		return display;
	}

	public HandlerManager getEventBus() {
		return eventBus;
	}
}
