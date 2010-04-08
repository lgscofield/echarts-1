package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface OpenEhrEventHandler extends EventHandler {
	void onOpenPatient(OpenEhrEvent event);
}
