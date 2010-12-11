package org.eastway.echarts.client;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.rpc.DemographicsProxy;
import org.eastway.echarts.client.rpc.EHRProxy;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.MessageProxy;
import org.eastway.echarts.client.ui.MessageView;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

@GinModules({ EchartsClientModule.class })
public interface EchartsClientFactory extends Ginjector {
	EventBus getEventBus();
	PlaceController getPlaceController();
	TicklerView<Tickler> getTicklerView();
	EchartsRequestFactory getRequestFactory();
	List<ColumnDefinition<Tickler>> getTicklerColumnDefinitions();
	ActivityMapper getActivityMapper();
	DashboardPresenter getDashboard();
	EHRView<EHRProxy> getEhrView();
	PatientSummaryView<EHRProxy> getPatientSummaryView();
	List<ColumnDefinition<EHRProxy>> getPatientSummaryColumnDefinitions();
	MessageView<MessageProxy> getMessageView();
	DemographicsView<DemographicsProxy> getDemographicsView();
	List<ColumnDefinition<DemographicsProxy>> getDemographicsColumnDefinitions();
}
