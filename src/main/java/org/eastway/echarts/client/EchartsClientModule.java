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
package org.eastway.echarts.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.DemographicsColumnDefinitionsImpl;
import org.eastway.echarts.client.common.DiagnosisColumnDefinitionsImpl;
import org.eastway.echarts.client.common.TicklerColumnDefinitionsImpl;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.TicklerPresenter;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.DashboardViewImpl;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.client.view.TicklerViewImpl;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.PlaceManager;

public class EchartsClientModule extends AbstractGinModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).in(Singleton.class);
		bind(AppController.class).in(Singleton.class);
		bind(CachingDispatchAsync.class).to(CachingDispatchAsyncImpl.class).in(Singleton.class);

		bind(GetTickler.class).in(Singleton.class);
		bind(DashboardPresenter.class).in(Singleton.class);
		bind(new TypeLiteral<DashboardView<LinkedHashMap<String, Long>>>() {}).to(DashboardViewImpl.class);

		bind(TicklerPresenter.class).in(Singleton.class);
		bind(new TypeLiteral<TicklerView<Tickler>>() {}).to(TicklerViewImpl.class);
		bind(new TypeLiteral<List<ColumnDefinition<Tickler>>>() {}).to(TicklerColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<Diagnosis>>>() {}).to(DiagnosisColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<Demographics>>>() {}).to(DemographicsColumnDefinitionsImpl.class).in(Singleton.class);
	}

}
