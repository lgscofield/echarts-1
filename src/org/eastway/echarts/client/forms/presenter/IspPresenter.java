package org.eastway.echarts.client.forms.presenter;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;

public class IspPresenter extends EchartsPresenter<IspPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);
	}

	private Patient patient;
	private PatientServicesAsync patientSvc;

	public IspPresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		this.patientSvc = patientSvc;
		setData();
	}

	private void setData() {
		display.setPatientId(patient.getPatientId());
	}
}
