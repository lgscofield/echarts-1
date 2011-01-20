package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class LastSeenReportActivity extends AbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/reports/lastseen.asp");
		panel.setWidget(frame);
		frame.setSize("100%", "100%");
	}
}
