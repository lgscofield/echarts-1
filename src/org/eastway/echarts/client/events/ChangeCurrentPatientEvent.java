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
package org.eastway.echarts.client.events;

import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeCurrentPatientEvent extends GwtEvent<ChangeCurrentPatientEventHandler> {
	public static Type<ChangeCurrentPatientEventHandler> TYPE = new Type<ChangeCurrentPatientEventHandler>();
	private PatientDTO patient;

	public ChangeCurrentPatientEvent(PatientDTO patient) {
		this.patient = patient;
	}

	@Override
	protected void dispatch(ChangeCurrentPatientEventHandler handler) {
		handler.onChangeCurrentPatient(this);
	}

	@Override
	public Type<ChangeCurrentPatientEventHandler> getAssociatedType() {
		return TYPE;
	}

	public PatientDTO getPatient() {
		return patient;
	}
}
