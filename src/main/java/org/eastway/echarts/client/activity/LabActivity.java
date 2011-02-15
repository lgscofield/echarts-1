package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.LabPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class LabActivity extends AbstractActivity {

	private LabPlace place;

	public LabActivity(LabPlace place) {
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame(new UrlBuilder()
			.setProtocol(Window.Location.getProtocol())
			.setHost(EchartsUser.dbServerUrl)
			.setPath("/echarts-asp/client/labs.asp")
			.setParameter("PATID", place.getCaseNumber())
			.buildString());
		panel.setWidget(frame);
		frame.setSize("100%", Window.getClientHeight() - frame.getOffsetHeight() + "px");
	}
}
