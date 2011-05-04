package org.eastway.echarts.client.ui;

import java.util.List;

import org.eastway.echarts.client.request.EHRProxy;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.Range;

public interface EhrQueryListView extends IsWidget {
	interface Presenter {
		void fetchData();

		void goTo(Place place);
	}

	Range getVisibleRange();

	void setRowCount(int count, boolean b);

	int getPageSize();

	int getPageStart();

	void setRowData(int pageStart, List<EHRProxy> response);

	void setPresenter(Presenter presenter);

	ColumnSortList getColumnSortList();

	String getColumnName(Column<?, ?> column);
}
