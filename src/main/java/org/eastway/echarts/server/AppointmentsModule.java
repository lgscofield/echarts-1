package org.eastway.echarts.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class AppointmentsModule extends ActionHandlerModule {
	@Override
	protected void configureHandlers() {
		bindHandler(GetAppointmentsHandler.class);
	}
}
