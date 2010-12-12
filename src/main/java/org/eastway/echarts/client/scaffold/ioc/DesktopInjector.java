package org.eastway.echarts.client.scaffold.ioc;

import org.eastway.echarts.client.scaffold.ScaffoldDesktopApp;

import com.google.gwt.inject.client.GinModules;

@GinModules(value = {ScaffoldModule.class})
public interface DesktopInjector extends ScaffoldInjector {
	ScaffoldDesktopApp getScaffoldApp();
}
