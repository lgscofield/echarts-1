package org.eastway.echarts.server;


import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class AssignmentsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetAssignmentsHandler.class);
	}
}
