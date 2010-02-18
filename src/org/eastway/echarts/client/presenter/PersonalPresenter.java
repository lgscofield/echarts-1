package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;

public class PersonalPresenter extends EchartsPresenter<PersonalPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(Patient data);
	}

	private Patient patient;

	public PersonalPresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		setData();
	}

	private void setData() {
		display.setData(patient);
	}
}
