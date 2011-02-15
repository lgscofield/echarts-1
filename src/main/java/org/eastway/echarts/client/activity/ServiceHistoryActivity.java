package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class ServiceHistoryActivity extends AbstractActivity {

	private String caseNumber;
	private EchartsRequestFactory requestFactory;

	public ServiceHistoryActivity(ServiceHistoryPlace place, EchartsRequestFactory requestFactory) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/client/clienthistory.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", caseNumber)
				.buildString());
		panel.setWidget(frame);
		frame.setSize("100%", Window.getClientHeight() - frame.getOffsetHeight() + "px");
	}

}
