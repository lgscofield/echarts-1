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

import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class DiagnosisPresenter extends Presenter<DiagnosisPresenter.Display> {

	public interface Display extends EchartsDisplay, Diagnosis {
		public void nextRecord();
	}

	private EHR ehr;

	public DiagnosisPresenter(Display display, HandlerManager eventBus, EHR ehr) {
		super(display, eventBus);
		this.ehr = ehr;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setData();
	}

	public void setData() {
		for (Diagnosis diagnosis : ehr.getDiagnoses()) {
			display.setAxis1A(diagnosis.getAxis1A());
			display.setAxis1B(diagnosis.getAxis1B());
			display.setAxis1C(diagnosis.getAxis1C());
			display.setAxis1D(diagnosis.getAxis1D());
			display.setAxis1E(diagnosis.getAxis1E());
			display.setAxis2A(diagnosis.getAxis2A());
			display.setAxis2B(diagnosis.getAxis2B());
			display.setAxis2C(diagnosis.getAxis2C());
			display.setAxis3(diagnosis.getAxis3());
			display.setAxis4(diagnosis.getAxis4());
			display.setCurrentGAF(diagnosis.getCurrentGAF());
			display.setHighestGAF(diagnosis.getHighestGAF());
			display.setDate(diagnosis.getDate());
			display.nextRecord();
		}
	}
}
