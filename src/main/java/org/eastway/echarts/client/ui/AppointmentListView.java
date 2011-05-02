package org.eastway.echarts.client.ui;

import java.util.List;

import org.eastway.echarts.client.request.AppointmentReportProxy;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.Range;

public interface AppointmentListView extends IsWidget {
	interface Presenter {
		void requestReports();
	}

	void setPresenter(Presenter presenter);

	Range getVisibleRange();

	void setRowCount(int count, boolean b);

	int getPageSize();

	int getPageStart();

	void setRowData(int pageStart, List<AppointmentReportProxy> response);

	ColumnSortList getColumnSortList();

	List<AppointmentReportProxy> getVisibleItems();

	String getColumnName(Column<?, ?> column);
}
