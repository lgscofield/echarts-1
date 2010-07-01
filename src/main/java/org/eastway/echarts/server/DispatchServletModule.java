package org.eastway.echarts.server;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {
	@Override
	public void configureServlets() {
		serve("/echarts/dispatch").with(DispatchServiceServlet.class);
	}
}
