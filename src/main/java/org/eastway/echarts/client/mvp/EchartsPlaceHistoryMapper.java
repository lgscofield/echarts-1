package org.eastway.echarts.client.mvp;

import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TicklerPlace.Tokenizer;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ TicklerPlace.Tokenizer.class })
public interface EchartsPlaceHistoryMapper extends PlaceHistoryMapper {
}
