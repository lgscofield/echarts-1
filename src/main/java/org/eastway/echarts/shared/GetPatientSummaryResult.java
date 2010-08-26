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
package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class GetPatientSummaryResult implements Result {

	private Patient patient;
	private Demographics demographics;
	private String provider;
	private List<String> providers;

	public GetPatientSummaryResult() { }

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	public Demographics getDemographics() {
		return demographics;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProvider() {
		return provider;
	}

	public void setProviders(List<String> providers) {
		this.providers = providers;
	}

	public List<String> getProviders() {
		return providers;
	}
}
