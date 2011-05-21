package org.eastway.echarts.client.ui;

import java.util.List;

import org.eastway.echarts.client.request.PlaceLogRecordProxy;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.Range;

public interface PlaceLogRecordListView extends IsWidget {

	interface Presenter {
		void requestData();
	}

	Range getVisibleRange();

	void setRowCount(int count, boolean b);

	int getPageSize();

	int getPageStart();

	void setRowData(int pageStart, List<PlaceLogRecordProxy> rowData);

	ColumnSortList getColumnSortList();

	String getColumnName(Column<?, ?> column);

	void setPresenter(Presenter presenter);

	void setVisibleRange(Range range);

}
