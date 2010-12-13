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

import java.util.LinkedHashMap;
import java.util.List;

import org.eastway.echarts.client.activity.DetailsActivityMapper;
import org.eastway.echarts.client.activity.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.activity.MasterActivityMapper;
import org.eastway.echarts.client.common.ARInfoColumnDefinitionsImpl;
import org.eastway.echarts.client.common.AddressColumnDefinitionsImpl;
import org.eastway.echarts.client.common.AppointmentColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.DemographicsColumnDefinitionsImpl;
import org.eastway.echarts.client.common.DiagnosisColumnDefinitionsImpl;
import org.eastway.echarts.client.common.PatientSummaryColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.common.ReferralColumnDefinitionsImpl;
import org.eastway.echarts.client.common.TicklerColumnDefinitionsImpl;
import org.eastway.echarts.client.request.ARInfoProxy;
import org.eastway.echarts.client.request.AddressProxy;
import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.request.DemographicsProxy;
import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.LinkProxy;
import org.eastway.echarts.client.request.MedicationProxy;
import org.eastway.echarts.client.request.MessageProxy;
import org.eastway.echarts.client.request.ReferralProxy;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.scaffold.ScaffoldDesktopShell;
import org.eastway.echarts.client.ui.ARInfoView;
import org.eastway.echarts.client.ui.ARInfoViewImpl;
import org.eastway.echarts.client.ui.AddressView;
import org.eastway.echarts.client.ui.AddressViewImpl;
import org.eastway.echarts.client.ui.AppointmentView;
import org.eastway.echarts.client.ui.AppointmentViewImpl;
import org.eastway.echarts.client.ui.DashboardSideBarView;
import org.eastway.echarts.client.ui.DashboardSideBarViewImpl;
import org.eastway.echarts.client.ui.DashboardView;
import org.eastway.echarts.client.ui.DashboardViewImpl;
import org.eastway.echarts.client.ui.DemographicsView;
import org.eastway.echarts.client.ui.DemographicsViewImpl;
import org.eastway.echarts.client.ui.DiagnosisView;
import org.eastway.echarts.client.ui.DiagnosisViewImpl;
import org.eastway.echarts.client.ui.EHRView;
import org.eastway.echarts.client.ui.EHRViewImpl;
import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.ui.EhrSideBarView;
import org.eastway.echarts.client.ui.EhrSideBarViewImpl;
import org.eastway.echarts.client.ui.LinkView;
import org.eastway.echarts.client.ui.LinkViewImpl;
import org.eastway.echarts.client.ui.MedicationView;
import org.eastway.echarts.client.ui.MedicationViewImpl;
import org.eastway.echarts.client.ui.MessageView;
import org.eastway.echarts.client.ui.MessageViewImpl;
import org.eastway.echarts.client.ui.PatientSummaryView;
import org.eastway.echarts.client.ui.PatientSummaryViewImpl;
import org.eastway.echarts.client.ui.ProfileView;
import org.eastway.echarts.client.ui.ProfileViewImpl;
import org.eastway.echarts.client.ui.ReferralView;
import org.eastway.echarts.client.ui.ReferralViewImpl;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.client.ui.TicklerViewImpl;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class ScaffoldModule extends AbstractGinModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(EchartsRequestFactory.class).toProvider(RequestFactoryProvider.class).in(Singleton.class);
		bind(MasterActivityMapper.class).in(Singleton.class);
		bind(DetailsActivityMapper.class).in(Singleton.class);
		bind(EchartsPlaceHistoryMapper.class).in(Singleton.class);
		bind(ScaffoldInjector.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
		bind(ScaffoldDesktopShell.class).in(Singleton.class);

		bind(new TypeLiteral<TicklerView<Tickler>>() {})
			.to(TicklerViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<EHRView<EHRProxy>>() {})
			.to(EHRViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<PatientSummaryView<EHRProxy>>() {})
			.to(PatientSummaryViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<MessageView<MessageProxy>>() {})
			.to(MessageViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<DemographicsView<DemographicsProxy>>() {})
			.to(DemographicsViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<ReferralView<ReferralProxy>>() {})
			.to(ReferralViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<AppointmentView<AppointmentProxy>>() {})
			.to(AppointmentViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<DiagnosisView<DiagnosisProxy>>() {})
			.to(DiagnosisViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<LinkView<LinkProxy>>() {})
			.to(LinkViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<AddressView<AddressProxy>>() {})
			.to(AddressViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<MedicationView<MedicationProxy>>() {})
			.to(MedicationViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<ARInfoView<ARInfoProxy>>() {})
			.to(ARInfoViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<DashboardView<LinkedHashMap<String, Long>>>() {})
			.to(DashboardViewImpl.class).in(Singleton.class);
		bind(DashboardSideBarView.class)
			.to(DashboardSideBarViewImpl.class).in(Singleton.class);
		bind(EhrSideBarView.class)
			.to(EhrSideBarViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<ProfileView<UserProxy>>() {})
			.to(ProfileViewImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<ARInfoProxy>>>() {})
			.to(ARInfoColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<Tickler>>>() {})
			.to(TicklerColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<DiagnosisProxy>>>() {})
			.to(DiagnosisColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<DemographicsProxy>>>() {})
			.to(DemographicsColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<UserProxy>>>() {})
			.to(ProfileColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<AddressProxy>>>() {})
			.to(AddressColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<EHRProxy>>>() {})
			.to(PatientSummaryColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<AppointmentProxy>>>() {})
			.to(AppointmentColumnDefinitionsImpl.class).in(Singleton.class);

		bind(new TypeLiteral<List<ColumnDefinition<ReferralProxy>>>() {})
			.to(ReferralColumnDefinitionsImpl.class).in(Singleton.class);

		bind(EchartsOracle.class).in(Singleton.class);
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
			requestFactory.initialize(eventBus);
			return requestFactory;
		}
		
	}
}
