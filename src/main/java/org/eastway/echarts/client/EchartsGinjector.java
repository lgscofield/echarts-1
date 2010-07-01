package org.eastway.echarts.client;

import net.customware.gwt.dispatch.client.gin.ClientDispatchModule;
import net.customware.gwt.presenter.client.place.PlaceManager;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ ClientDispatchModule.class, EchartsClientModule.class })
public interface EchartsGinjector extends Ginjector {
	AppController getAppController();

	PlaceManager getPlaceManager();
}
