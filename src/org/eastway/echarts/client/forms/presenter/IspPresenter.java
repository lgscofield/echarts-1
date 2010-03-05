package org.eastway.echarts.client.forms.presenter;

import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;

public class IspPresenter extends EchartsPresenter<IspPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);
	}

	private Patient patient;

	public IspPresenter(Display display, HandlerManager eventBus,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		setData();
	}

	private void setData() {
		display.setPatientId(patient.getPatientId());
	}
}
