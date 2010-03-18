package org.eastway.echarts.tab.client;

import org.eastway.echarts.appcontroller.client.AppController;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;

public class TabController extends AppController {

	public TabController(HandlerManager eventBus) {
		super(eventBus);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			if (token.equals("tab")) {
				
			}
		}
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}
}
