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

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.shared.GetPatientSummary;
import org.eastway.echarts.shared.GetPatientSummaryResult;

import com.google.gwt.user.client.ui.HasWidgets;

public class PatientSummaryPresenter implements Presenter {

	private Display display;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetPatientSummary action;

	public PatientSummaryPresenter(Display display,
			EventBus eventBus, CachingDispatchAsync dispatch, GetPatientSummary action) {
		this.display = display;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
	}

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}

	private void fetchData() {
		dispatch.executeWithCache(action, new EchartsCallback<GetPatientSummaryResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetPatientSummaryResult result) {
				setData(result);
			}
		});
	}

	void setData(GetPatientSummaryResult result) {
		LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
		// TODO: the first value here could easily be set by
		// patient.getPatientIdTitle() or some such.  This way it could
		// be retrieved from the database.
		data.add(new String[] { "Case Number : ", result.getCaseNumber() });
		data.add(new String[] { "Name : ", result.getName() });
		data.add(new String[] { "Gender : ", result.getGender() });
		data.add(new String[] { "DOB : ", result.getDob().toString() });
		data.add(new String[] { "Ethnicity : ", result.getEthnicity() });
		data.add(new String[] { "Preferred Language : ", result.getPreferredLanguage() });
		data.add(new String[] { "Race : ", result.getRace() });
		data.add(new String[] { "Insurance Type : ", result.getInsuranceType() });
		data.add(new String[] { "SSN : ", result.getSsn() });
		display.setData(data);
	}
}
