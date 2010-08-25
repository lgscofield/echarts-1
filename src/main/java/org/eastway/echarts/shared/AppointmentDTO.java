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
import java.sql.Time;
import java.util.Date;

@SuppressWarnings("serial")
public class AppointmentDTO implements Appointment, Serializable {

	private String activity;
	private Date appointmentDate;
	private String caseNumber;
	private Time endTime;
	private long id;
	private String location;
	private String notes;
	private float priority;
	private String staff;
	private Time startTime;

	@Override
	public String getActivity() {
		return activity;
	}

	@Override
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public Time getEndTime() {
		return endTime;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public float getPriority() {
		return priority;
	}

	@Override
	public String getStaff() {
		return staff;
	}

	@Override
	public Time getStartTime() {
		return startTime;
	}

	@Override
	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setPriority(float priority) {
		this.priority = priority;
	}

	@Override
	public void setStaff(String staff) {
		this.staff = staff;
	}

	@Override
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Override
	public AppointmentDTO toDto() {
		return this;
	}

}
