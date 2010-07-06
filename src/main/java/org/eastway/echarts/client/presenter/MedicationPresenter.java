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

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMedicationsResult;
import org.eastway.echarts.shared.Medication;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MedicationPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Medication {
		public void nextRecord();
	}

	private Display view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetMedications action;

	public MedicationPresenter(Display view,
			EventBus eventBus, CachingDispatchAsync dispatch,
			GetMedications action) {
		this.view = view;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.executeWithCache(action, new AsyncCallback<GetMedicationsResult>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetMedicationsResult result) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				setData(result.getMedications());
			}
		});
	}

	public void setData(List<Medication> medications) {
		for (Medication medication : medications) {
			view.setMedication(medication.getMedication());
			view.nextRecord();
		}
	}
}
