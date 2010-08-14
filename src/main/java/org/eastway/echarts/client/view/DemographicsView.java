package org.eastway.echarts.client.view;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.user.client.ui.Widget;

public interface DemographicsView<T> {

	interface Presenter<T> { }

	Widget asWidget();
	void setPresenter(Presenter<T> presenter);
	void setRowData(T rowData);
	void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
}
