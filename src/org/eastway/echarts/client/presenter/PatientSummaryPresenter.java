/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.presenter;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter extends Presenter<PatientSummaryPresenter.Display> {

	private PatientDTO patient;

	public PatientSummaryPresenter(Display display,
			HandlerManager eventBus, EHRServicesAsync ehrServicesAsync,
			PatientDTO patient) {
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

	protected void setPatient(PatientDTO patient) {
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
