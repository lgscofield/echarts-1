package org.eastway.echarts.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.PlaceManager;

public class EchartsClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).in(Singleton.class);
		bind(AppController.class).in(Singleton.class);
		bind(CachingDispatchAsync.class);
	}

}
