package org.eastway.echarts.client.ui;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.HasContextMenuHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Label;

public class ContextMenuLabel extends Label implements HasContextMenuHandlers {
	public ContextMenuLabel() {
		super();
	}

	public ContextMenuLabel(String text) {
		super(text);
	}

	public ContextMenuLabel(String text, boolean wordWrap) {
		super(text, wordWrap);
	}

	@Override
	public HandlerRegistration addContextMenuHandler(ContextMenuHandler handler) {
		return addDomHandler(handler, ContextMenuEvent.getType());
	}
}
