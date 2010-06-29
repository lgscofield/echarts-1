package org.eastway.echarts.client.view;

import java.util.List;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;

public interface DashboardView<T> {

	interface Style extends CssResource {
		String alerts();
		String green();
		String yellow();
		String red();
		String button();
		String searchbox();
	}

	public interface Presenter<T> {

	}

	void addTab(Widget widget, String string);
	void setSelectedTab(int idx);
	Widget asWidget();
	void setPresenter(Presenter<T> presenter);
	void setPatientSearchData(List<String> list);
	void addPatientSearchData(String str);
}
