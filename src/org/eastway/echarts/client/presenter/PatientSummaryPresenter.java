package org.eastway.echarts.client.presenter;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter extends Presenter<PatientSummaryPresenter.Display> {

	private Patient patient;

	public PatientSummaryPresenter(Display display,
			HandlerManager eventBus, RpcServicesAsync singleton,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
	}

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setPersonalData();
	}

	protected void setPatient(Patient patient) {
		this.patient = patient;
	}

	void setPersonalData() {
		LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
		// TODO: the first value here could easily be set by
		// patient.getPatientIdTitle() or some such.  This way it could
		// be retrieved from the database.
		data.add(new String[] { "Case Number : ", patient.getCaseNumber() });
		data.add(new String[] { "Name : ", patient.getName() });
		data.add(new String[] { "Gender : ", patient.getDemographics().getGender() });
		data.add(new String[] { "DOB : ", patient.getDemographics().getDob().toString() });
		data.add(new String[] { "Ethnicity : ", patient.getDemographics().getEthnicity() });
		data.add(new String[] { "Preferred Language : ", patient.getDemographics().getPreferredLanguage() });
		data.add(new String[] { "Race : ", patient.getDemographics().getRace() });
		data.add(new String[] { "Insurance Type : ", patient.getDemographics().getInsuranceType() });
		data.add(new String[] { "SSN : ", patient.getSsn() });
		display.setData(data);
	}
}
