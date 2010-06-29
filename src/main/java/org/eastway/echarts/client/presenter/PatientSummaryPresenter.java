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
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter implements Presenter {

	private EHR ehr;
	private Display display;
	private long ehrId;
	private EHRServicesAsync ehrServices;

	public PatientSummaryPresenter(Display display,
			HandlerManager eventBus, EHRServicesAsync ehrServices,
			long ehrId) {
		this.ehrId = ehrId;
		this.display = display;
		this.ehrServices = ehrServices;
	}

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchEhr();
	}

	private void fetchEhr() {
		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR ehr) {
				setEhr(ehr);
				setPersonalData();
			}
		};
		ehrServices.getEhr(ehrId, Cookies.getCookie("sessionId"), callback);
	}

	protected void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	void setPersonalData() {
		LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
		// TODO: the first value here could easily be set by
		// patient.getPatientIdTitle() or some such.  This way it could
		// be retrieved from the database.
		Patient patient = ehr.getSubject();
		Demographics demographics = ehr.getDemographics();
		data.add(new String[] { "Case Number : ", patient.getCaseNumber() });
		data.add(new String[] { "Name : ", patient.getName() });
		data.add(new String[] { "Gender : ", demographics.getGender().getDescriptor() });
		data.add(new String[] { "DOB : ", demographics.getDob().toString() });
		data.add(new String[] { "Ethnicity : ", demographics.getEthnicity().getDescriptor() });
		data.add(new String[] { "Preferred Language : ", demographics.getPreferredLanguage() });
		data.add(new String[] { "Race : ", demographics.getRace().getDescriptor() });
		data.add(new String[] { "Insurance Type : ", demographics.getInsuranceType() });
		data.add(new String[] { "SSN : ", patient.getSsn() });
		display.setData(data);
	}
}
