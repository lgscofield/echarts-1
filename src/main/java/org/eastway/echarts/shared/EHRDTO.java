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

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EHRDTO implements Serializable, EHR {
	private long id;
	private Date timeCreated;
	private Patient patient;
	private Demographics demographics;

	public EHRDTO() { }

	public EHRDTO(long id) {
		this.setId(id);
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	@Override
	public Date getTimeCreated() {
		return timeCreated;
	}

	@Override
	public EHRDTO toDto() {
		return this;
	}

	@Override
	public Patient getSubject() {
		return patient;
	}

	@Override
	public void setSubject(Patient patient) {
		this.patient = patient;
	}

	@Override
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	@Override
	public Demographics getDemographics() {
		return demographics;
	}
}
