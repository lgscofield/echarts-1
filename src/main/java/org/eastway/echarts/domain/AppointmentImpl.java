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
package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.AppointmentDTO;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Appointment")
public class AppointmentImpl implements Appointment {

	private String activity;
	private Date appointmentDate;
	private String caseNumber;
	private float endTime;
	@Id
	private int id;
	private String location;
	@Type(type = "text")
	private String notes;
	private float priority;
	private String staff;
	private float startTime;

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
	public float getEndTime() {
		return endTime;
	}

	@Override
	public int getId() {
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
	public float getStartTime() {
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
	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}

	@Override
	public void setId(int id) {
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
	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	@Override
	public AppointmentDTO toDto() {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setActivity(activity);
		dto.setAppointmentDate(appointmentDate);
		dto.setCaseNumber(caseNumber);
		dto.setEndTime(endTime);
		dto.setId(id);
		dto.setLocation(location);
		dto.setNotes(notes);
		dto.setPriority(priority);
		dto.setStaff(staff);
		dto.setStartTime(startTime);
		return dto;
	}

}