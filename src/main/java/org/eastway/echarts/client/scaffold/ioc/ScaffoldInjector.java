package org.eastway.echarts.client.scaffold.ioc;

import org.eastway.echarts.client.scaffold.ScaffoldApp;

import com.google.gwt.inject.client.Ginjector;

public interface ScaffoldInjector extends Ginjector {
	ScaffoldApp getScaffoldApp();
}
