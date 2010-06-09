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
import java.util.List;

@SuppressWarnings("serial")
public class EHRDTO implements Serializable, EHR {
	private long id;
	private Patient subject;
	private Demographics demographics;
	private Date timeCreated;
	private List<Assignment> assignments;
	private List<Contact> contacts;
	private List<Diagnosis> diagnoses;
	private List<Appointment> appointments;

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
	public void setSubject(Patient subject) {
		this.subject = subject;
	}

	@Override
	public Patient getSubject() {
		return subject;
	}

	@Override
	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	@Override
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public EHRDTO toDto() {
		return this;
	}

	@Override
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	@Override
	public Demographics getDemographics() {
		return this.demographics;
	}

	@Override
	public List<Contact> getContacts() {
		return contacts;
	}

	@Override
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	@Override
	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	@Override
	public List<Appointment> getAppointments() {
		return appointments;
	}

	@Override
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
