package org.eastway.echarts.client.common;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.shared.Tickler;

public class TicklerColumnDefinitionsFactory<T> {
	public static List<ColumnDefinition<Tickler>> getTicklerColumnDefinitions() {
		return TicklerColumnDefinitionsImpl.getInstance();
	}

	public static List<ColumnDefinition<Tickler>> getTestTicklerColumnDefinitions() {
		return new ArrayList<ColumnDefinition<Tickler>>();
	}
}
