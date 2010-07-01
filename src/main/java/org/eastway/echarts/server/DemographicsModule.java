package org.eastway.echarts.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class DemographicsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetDemographicsHandler.class);
	}

}
