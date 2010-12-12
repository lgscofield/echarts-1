package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.TreatmentPlanPlace;
import org.eastway.echarts.client.scaffold.ioc.ScaffoldInjector;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

public class TreatmentPlanActivity extends AbstractActivity {

	private String caseNumber;

	public TreatmentPlanActivity(TreatmentPlanPlace place,
			ScaffoldInjector clientFactory) {
		this.caseNumber = place.getCaseNumber();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Frame frame = new Frame("http://" + EchartsUser.dbServerUrl + "/echarts-asp/client/treatmentplan.asp?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber);
		panel.setWidget(frame);
		frame.setSize("100%", "100%");
	}
}
