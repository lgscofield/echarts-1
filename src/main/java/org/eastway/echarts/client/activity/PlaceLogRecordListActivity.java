package org.eastway.echarts.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.PlaceLogRecordListPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.PlaceLogRecordProxy;
import org.eastway.echarts.client.ui.PlaceLogRecordListView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class PlaceLogRecordListActivity implements Activity, PlaceLogRecordListView.Presenter {

	private EchartsRequestFactory requestFactory;
	private PlaceLogRecordListPlace place;
	private PlaceLogRecordListView view;
	private Receiver<List<PlaceLogRecordProxy>> lastDataReceiver;
	private List<PlaceLogRecordProxy> inMemoryList = new ArrayList<PlaceLogRecordProxy>();

	public PlaceLogRecordListActivity(EchartsRequestFactory requestFactory, PlaceLogRecordListPlace place, PlaceLogRecordListView view) {
		this.requestFactory = requestFactory;
		this.place = place;
		this.view = view;
	}

	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {
		onStop();
	}

	@Override
	public void onStop() {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.setVisibleRange(new Range(0, 20));
		requestData();
		panel.setWidget(view);
	}

	private Long nextKey = null;
	private int lastPageStart = 0;
	private boolean endOfData = false;

	@Override
	public void requestData() {
		Range range = view.getVisibleRange();

		if (endOfData || (lastPageStart != 0 && range.getStart() <= lastPageStart)) {
			view.setRowData(0, inMemoryList);
			view.setRowCount(inMemoryList.size(), endOfData);
			return;
		}
		lastPageStart = range.getStart();

		lastDataReceiver = new Receiver<List<PlaceLogRecordProxy>>() {
			@Override
			public void onSuccess(List<PlaceLogRecordProxy> response) {
				if (this == lastDataReceiver) {
					if (response.size() > view.getPageSize()) {
						nextKey = response.get(response.size()-1).getTimestamp();
						response.remove(response.size()-1);
					} else {
						nextKey = null;
						endOfData = true;
						view.setRowCount(inMemoryList.size(), true);
					}
					if ((response.size() > 0) && !endOfData) {
						inMemoryList.addAll(response);
						view.setRowData(0, inMemoryList);
					} else if (endOfData) {
						inMemoryList.addAll(response);
						view.setRowData(0, inMemoryList);
						view.setRowCount(inMemoryList.size(), true);
					}
				}
			}
		};
		requestFactory.placeLogRecordRequest().findPlaceLogRecordsByUsername(EchartsUser.userName, nextKey, range.getLength()+1).fire(lastDataReceiver);
	}
}
