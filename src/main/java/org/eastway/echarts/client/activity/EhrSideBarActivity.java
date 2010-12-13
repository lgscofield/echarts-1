package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.ui.EhrSideBarView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EhrSideBarActivity extends AbstractActivity implements EhrSideBarView.Presenter {

	private EhrSideBarView view;
	private PlaceController placeController;
	private String id;

	public EhrSideBarActivity(EhrSideBarView view,
							  PlaceController placeController,
							  String id) {
		this.view = view;
		this.placeController = placeController;
		this.id = id;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.setId(id);
		panel.setWidget(view.asWidget());
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

}
