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

import java.util.List;

import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.MedicationProxy;
import org.eastway.echarts.shared.GetMedications;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class MedicationPresenter implements Presenter {

	public interface Display extends EchartsDisplay {
		public void nextRecord();

		public void setMedication(String medication);
	}

	private Display view;
	private GetMedications action;
	private EchartsRequestFactory requestFactory;

	public MedicationPresenter(Display view, EchartsRequestFactory requestFactory,
			GetMedications action) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		requestFactory.medicationRequest().findMedicationsByCaseNumber(action.getCaseNumber()).fire(new Receiver<List<MedicationProxy>>() {
			@Override
			public void onSuccess(List<MedicationProxy> response) {
				setData(response);
			}
		});
	}

	public void setData(List<MedicationProxy> medications) {
		for (MedicationProxy medication : medications) {
			view.setMedication(medication.getMedication());
			view.nextRecord();
		}
	}
}
