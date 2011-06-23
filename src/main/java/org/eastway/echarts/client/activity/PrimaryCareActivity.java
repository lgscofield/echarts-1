package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.PrimaryCarePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class PrimaryCareActivity implements Activity {

	private String caseNumber;

	public PrimaryCareActivity(PrimaryCarePlace place) {
		this.caseNumber = place.getCaseNumber();
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
		Frame frame = new Frame(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/client/primarycare.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", caseNumber).buildString());
		panel.setWidget(frame);
		frame.setSize("100%",
				Window.getClientHeight() - frame.getOffsetHeight() + "px");

	}

}
