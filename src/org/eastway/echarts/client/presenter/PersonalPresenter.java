package org.eastway.echarts.client.presenter;

import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class PersonalPresenter extends Presenter<PersonalPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(Patient data);
	}

	private Patient patient;

	public PersonalPresenter(Display display, HandlerManager eventBus,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		setData();
	}

	private void setData() {
		display.setData(patient);
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
