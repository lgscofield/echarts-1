package org.eastway.echarts.client.activity;

import java.util.List;

import org.eastway.echarts.client.place.EhrQueryListPlace;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.EhrRequest;
import org.eastway.echarts.client.ui.EhrQueryListView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.Range;

public class EhrQueryListActivity implements Activity, EhrQueryListView.Presenter {

	private EchartsRequestFactory requestFactory;
	private EhrQueryListView view;
	private Receiver<Long> lastDataSizeReceiver;
	private EhrQueryListPlace place;
	private Receiver<List<EHRProxy>> lastDataReceiver;
	private PlaceController placeController;

	public EhrQueryListActivity(EhrQueryListPlace place, EchartsRequestFactory requestFactory, PlaceController placeController, EhrQueryListView view) {
		this.place = place;
		this.requestFactory = requestFactory;
		this.view = view;
		this.placeController = placeController;
	}

	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {
	}

	@Override
	public void onStop() {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		fetchData();
		view.setPresenter(this);
		panel.setWidget(view);
	}

	public void fetchData() {
		Range range = view.getVisibleRange();
		EhrRequest context = requestFactory.ehrRequest();

		lastDataSizeReceiver = new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				if (this == lastDataSizeReceiver) {
					int count = response.intValue();
					view.setRowCount(count, count != 1000);
				}
			}
		};

		context.findEhrsLikeCount(place.getQuery()).to(lastDataSizeReceiver);

		lastDataReceiver = new Receiver<List<EHRProxy>>() {
			@Override
			public void onSuccess(List<EHRProxy> response) {
				if (this == lastDataReceiver) {
					int size = response.size();
					if (size < view.getPageSize()) {
						view.setRowCount(view.getPageStart() + size, true);
					}
					if (size > 0) {
						view.setRowData(view.getPageStart(), response);
					}
				}
			}
		};
		ColumnSortInfo sortInfo = view.getColumnSortList().get(0);
		String orderBy = view.getColumnName(sortInfo.getColumn());
		String sortDirection = "DESC";
		boolean isAscending = view.getColumnSortList().get(0).isAscending();
		if (isAscending)
			sortDirection = "ASC";
		context.findEhrsEntriesLike(place.getQuery(), range.getStart(), range.getLength(), orderBy, sortDirection)
			.with("patient")
			.with("demographics.gender")
			.with("assignments")
			.to(lastDataReceiver);
		context.fire();
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

}
