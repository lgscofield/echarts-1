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

import org.eastway.echarts.client.common.AddressColumnDefinitionsImpl;
import org.eastway.echarts.client.common.AppointmentColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.DemographicsColumnDefinitionsImpl;
import org.eastway.echarts.client.common.DiagnosisColumnDefinitionsImpl;
import org.eastway.echarts.client.common.PatientSummaryColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ReferralColumnDefinitionsImpl;
import org.eastway.echarts.client.common.TicklerColumnDefinitionsImpl;
import org.eastway.echarts.client.place.EchartsPlaceController;
import org.eastway.echarts.client.place.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.place.PlaceControllerProvider;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.ProfilePresenter;
import org.eastway.echarts.client.rpc.AddressProxy;
import org.eastway.echarts.client.rpc.AppointmentProxy;
import org.eastway.echarts.client.rpc.DemographicsProxy;
import org.eastway.echarts.client.rpc.DiagnosisProxy;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.ReferralProxy;
import org.eastway.echarts.client.rpc.UserProxy;
import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.DashboardViewImpl;
import org.eastway.echarts.client.view.ProfileView;
import org.eastway.echarts.client.view.ProfileViewImpl;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.client.view.TicklerViewImpl;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class EchartsClientModule extends AbstractGinModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(AppController.class).in(Singleton.class);
		bind(EchartsRequestFactory.class).in(Singleton.class);
		bind(ActivityMapper.class).to(EchartsActivityMapper.class).in(Singleton.class);
		bind(EchartsPlaceHistoryMapper.class).in(Singleton.class);
		bind(EchartsClientFactory.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(GetTickler.class).in(Singleton.class);
		bind(DashboardPresenter.class).in(Singleton.class);
		bind(new TypeLiteral<DashboardView<LinkedHashMap<String, Long>>>() {}).to(DashboardViewImpl.class);

		bind(new TypeLiteral<TicklerView<Tickler>>() {}).to(TicklerViewImpl.class);
		bind(new TypeLiteral<List<ColumnDefinition<Tickler>>>() {}).to(TicklerColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<DiagnosisProxy>>>() {}).to(DiagnosisColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<DemographicsProxy>>>() {}).to(DemographicsColumnDefinitionsImpl.class).in(Singleton.class);

		bind(ProfilePresenter.class).in(Singleton.class);
		bind(new TypeLiteral<List<ColumnDefinition<UserProxy>>>() {}).to(ProfileColumnDefinitionsImpl.class).in(Singleton.class);
		bind(new TypeLiteral<ProfileView<UserProxy>>() {}).to(ProfileViewImpl.class);

		bind(new TypeLiteral<List<ColumnDefinition<AddressProxy>>>() {}).to(AddressColumnDefinitionsImpl.class).in(Singleton.class);

		bind(EchartsOracle.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<EHRProxy>>>() {}).to(PatientSummaryColumnDefinitionsImpl.class).in(Singleton.class);
		bind(new TypeLiteral<List<ColumnDefinition<AppointmentProxy>>>() {}).to(AppointmentColumnDefinitionsImpl.class).in(Singleton.class);
		bind(new TypeLiteral<List<ColumnDefinition<ReferralProxy>>>() {}).to(ReferralColumnDefinitionsImpl.class).in(Singleton.class);
	}

}
