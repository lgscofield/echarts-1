package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.StaffAnalysisPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class StaffAnalysisActivity extends AbstractActivity {

	private String staffId;

	public StaffAnalysisActivity(StaffAnalysisPlace place) {
		this.staffId = place.getStaffId();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame(new UrlBuilder()
			.setProtocol(Window.Location.getProtocol())
			.setHost(EchartsUser.dbServerUrl)
			.setPath("/echarts-asp/reports/staffanalysis.asp")
			.setParameter("staffid", EchartsUser.staffId)
			.buildString());
		panel.setWidget(frame);
		frame.setSize("100%", Window.getClientHeight() - frame.getOffsetHeight() + "px");
	}

}
