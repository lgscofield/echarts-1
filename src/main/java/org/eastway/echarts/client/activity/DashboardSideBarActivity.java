package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.ui.DashboardSideBarView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DashboardSideBarActivity extends AbstractActivity {

	private DashboardSideBarView view;

	public DashboardSideBarActivity(DashboardSideBarView view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
	}
}
