package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.shared.HandlerManager;

public class PatientTabPresenter extends EchartsPresenter<PatientTabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);

		String getPatientId();
	}

	public PatientTabPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
	}
}
