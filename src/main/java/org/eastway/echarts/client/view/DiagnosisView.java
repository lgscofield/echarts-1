package org.eastway.echarts.client.view;

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.user.client.ui.Widget;

public interface DiagnosisView<T> {
	public interface Presenter<T> { }

	void setPresenter(Presenter<T> presenter);
	Widget asWidget();
	void setRowData(List<T> diagnoses);
	void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
}
