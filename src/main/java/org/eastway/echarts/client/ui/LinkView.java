package org.eastway.echarts.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface LinkView<T> extends IsWidget {
	public interface Presenter<T> { }

	void setData(List<String[]> list);
}
