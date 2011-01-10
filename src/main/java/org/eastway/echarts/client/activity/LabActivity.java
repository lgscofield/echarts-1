package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.LabPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class LabActivity extends AbstractActivity {

	private LabPlace place;

	public LabActivity(LabPlace place) {
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/labs.asp?PATID=" + place.getCaseNumber());
		panel.setWidget(frame);
		frame.setSize("100%", "100%");
	}
}
