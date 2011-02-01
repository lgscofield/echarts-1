package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.StaffAnalysisPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class StaffAnalysisActivity extends AbstractActivity {

	private String staffId;

	public StaffAnalysisActivity(StaffAnalysisPlace place) {
		this.staffId = place.getStaffId();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/reports/staffanalysis.asp?staffid=" + staffId);
		panel.setWidget(frame);
		frame.setSize("100%", "100%");
	}

}
