package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.DashboardFramePlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class DashboardFrameActivity extends AbstractActivity {

	private String url;

	public DashboardFrameActivity(DashboardFramePlace place) {
		this.url = place.getUrl();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame(buildUrl(url));
		panel.setWidget(frame);
		int height = Window.getClientHeight() - frame.getOffsetHeight() < 500 ? 500 : Window.getClientHeight() - frame.getOffsetHeight();
		frame.setSize("100%", height + "px");
	}

	private String buildUrl(String url) {
		return new UrlBuilder().setHost(EchartsUser.dbServerUrl)
				.setPath(url)
				.setProtocol(Window.Location.getProtocol())
				.setParameter("staffid", EchartsUser.staffId)
				.buildString();
	}
}
