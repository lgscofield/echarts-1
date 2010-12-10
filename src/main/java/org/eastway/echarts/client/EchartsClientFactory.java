package org.eastway.echarts.client;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;

@GinModules({ EchartsClientModule.class })
public interface EchartsClientFactory extends Ginjector {
	EventBus getEventBus();
	PlaceController getPlaceController();
	TicklerView<Tickler> getTicklerView();
	EchartsRequestFactory getRequestFactory();
	List<ColumnDefinition<Tickler>> getTicklerColumnDefinitions();
	ActivityMapper getActivityMapper();
}
