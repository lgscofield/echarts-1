package org.eastway.echarts.client.presenter;

import java.sql.Date;

import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;

public class DemographicsPresenter extends EchartsPresenter<DemographicsPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setPreferredLanguage(String preferredLanguage);

		void setInsuranceType(String insuranceType);

		void setGender(String gender);

		void setRace(String race);

		void setEthnicity(String ethnicity);

		void setDob(Date date);
	}

	public DemographicsPresenter(Display display,
			HandlerManager eventBus, Patient patient) {
		super(display, eventBus);
		setData(patient);
	}

	private void setData(Patient patient) {
		display.setPreferredLanguage(patient.getPreferredLanguage());
		display.setInsuranceType(patient.getInsuranceType());
		display.setGender(patient.getGender());
		display.setRace(patient.getRace());
		display.setEthnicity(patient.getEthncity());
		display.setDob(patient.getDob());
	}
}
