/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.scaffold.ioc;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.common.TicklerColumnDefinitionsImpl;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.scaffold.request.EventSourceRequestTransport;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

public class ScaffoldModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(EchartsRequestFactory.class).toProvider(RequestFactoryProvider.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<Tickler>>>() {})
			.to(TicklerColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<UserProxy>>>() {})
			.to(ProfileColumnDefinitionsImpl.class).in(Singleton.class);
	}

	static class PlaceControllerProvider implements Provider<PlaceController> {

		private EventBus eventBus;

		@Inject
		PlaceControllerProvider(EventBus eventBus) {
			this.eventBus = eventBus;
		}

		@Override
		public PlaceController get() {
			return new PlaceController(eventBus);
		}
		
	}

	static class RequestFactoryProvider implements Provider<EchartsRequestFactory> {

		private final EventBus eventBus;

		@Inject
		RequestFactoryProvider(EventBus eventBus) {
			this.eventBus = eventBus;
		}

		@Override
		public EchartsRequestFactory get() {
			EchartsRequestFactory requestFactory = GWT.create(EchartsRequestFactory.class);
			EventSourceRequestTransport transport = new EventSourceRequestTransport(eventBus);
			requestFactory.initialize(eventBus, transport);
			return requestFactory;
		}
		
	}
}
