package org.eastway.echarts.appcontroller.client;

import org.eastway.echarts.dashboard.client.AdministratorDashboard;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

public class AdministratorController extends AppController {

	public AdministratorController(HandlerManager eventBus) {
		super(eventBus);
	}

	@Override
	public void bind() {
		History.addValueChangeHandler(this);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			if (token.equals("dashboard")) {
				container.clear();
				container.add(new AdministratorDashboard(eventBus));
			}
		}
	}
}
