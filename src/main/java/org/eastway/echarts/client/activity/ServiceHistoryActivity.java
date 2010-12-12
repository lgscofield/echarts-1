package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.scaffold.ioc.ScaffoldInjector;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class ServiceHistoryActivity extends AbstractActivity {

	private String caseNumber;

	public ServiceHistoryActivity(ServiceHistoryPlace place,
			ScaffoldInjector clientFactory) {
		this.caseNumber = place.getCaseNumber();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/clienthistory.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber);
		panel.setWidget(frame);
		frame.setSize("100%", "100%");
	}

}
