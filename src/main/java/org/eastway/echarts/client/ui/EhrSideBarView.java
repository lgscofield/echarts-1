package org.eastway.echarts.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface EhrSideBarView extends IsWidget {

	public interface Presenter {
		void goTo(Place place);
	}

	void setPresenter(Presenter presenter);

	void setId(String id);
}
