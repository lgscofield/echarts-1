package org.eastway.echarts.client.view;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.shared.EHR;

import com.google.gwt.app.client.NotificationMole;
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
		String label();
	}

	public interface Presenter<T> {
		void onItemSelected(String row);

		void changeCurrentEhr(EHR ehr);

		void openEhr(String text);

		void getPatientList();
	}

	void addTab(Widget widget, String string);
	void setSelectedTab(int idx);
	Widget asWidget();
	void setPresenter(Presenter<T> presenter);
	void setPatientSearchData(List<String> list);
	void addPatientSearchData(String str);
	void setCurrentEhrData(ArrayList<String[]> data);
	NotificationMole getMole();
	void reset();
	void setProductivity(String productivity, String color);
	void setBonusProjection(String bonusProjection);
}
