package org.eastway.echarts.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import org.eastway.echarts.shared.Patient;

public class ServiceHistoryPresenter extends Presenter<ServiceHistoryPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setName(String name);
	}

	public ServiceHistoryPresenter(Display display, HandlerManager eventBus,
				Patient patient) {
		super(display, eventBus);
		display.setName(patient.getCaseNumber());
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
