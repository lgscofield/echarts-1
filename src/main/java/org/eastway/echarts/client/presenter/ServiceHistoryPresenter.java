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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import org.eastway.echarts.shared.PatientDTO;

public class ServiceHistoryPresenter extends Presenter<ServiceHistoryPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setName(String name);
	}

	public ServiceHistoryPresenter(Display display, HandlerManager eventBus,
				PatientDTO patient) {
		super(display, eventBus);
		display.setName(patient.getCaseNumber());
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}