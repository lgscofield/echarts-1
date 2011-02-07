package org.eastway.echarts.client.activity;

import java.util.List;

import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.LinkProxy;
import org.eastway.echarts.client.ui.DashboardSideBarView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DashboardSideBarActivity extends AbstractActivity {

	private DashboardSideBarView view;
	private EchartsRequestFactory requestFactory;
	private AcceptsOneWidget panel;

	public DashboardSideBarActivity(DashboardSideBarView view, EchartsRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		if (view.isLoaded()) {
			panel.setWidget(view);
			return;
		}
		this.panel = panel;
		fetchData();
	}

	private void fetchData() {
		requestFactory.linkRequest().findLinksByPlace("MainPage").fire(new Receiver<List<LinkProxy>>() {
			@Override
			public void onSuccess(List<LinkProxy> response) {
				if (response == null || response.size() == 0)
					return;
				view.setLinks(response);
				panel.setWidget(view);
			}
		});
	}
}
