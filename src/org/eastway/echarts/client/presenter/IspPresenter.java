package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.shared.HandlerManager;

public class IspPresenter extends EchartsPresenter<IspPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);
	}

	private String patientId;
	private PatientServicesAsync patientSvc;

	public IspPresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
		this.patientId = patientId;
		this.patientSvc = patientSvc;
		setData();
	}

	private void setData() {
		display.setPatientId(patientId);
	}
}
