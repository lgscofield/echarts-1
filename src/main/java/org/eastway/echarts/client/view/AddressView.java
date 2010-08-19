package org.eastway.echarts.client.view;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.user.client.ui.Widget;

public interface AddressView<T> {
	interface Presenter<T> { }

	void setPresenter(Presenter<T> presenter);
	Widget asWidget();
	void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
	void setRowData(List<T> rowData);
}
