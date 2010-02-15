package org.eastway.echarts.client.presenter;

import com.google.gwt.event.shared.HandlerManager;

import org.eastway.echarts.client.PatientServicesAsync;

public class ServiceHistoryPresenter extends EchartsPresenter<ServiceHistoryPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setName(String name);
	}

	public ServiceHistoryPresenter(Display display, HandlerManager eventBus,
				PatientServicesAsync patientSvc,
				String patientId) {
		super(display, eventBus);
		display.setName(patientId);
	}
}
