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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eastway.echarts.server.EchartsEntityManagerFactory;

import com.google.gwt.requestfactory.shared.Version;

@Entity
@Table(name = "apptscatt")
public class Appointment {

	private String activity;
	@Column(name="apptdate")
	private Date appointmentDate;
	private String caseNumber;
	@Temporal(TemporalType.TIME)
	@Column(name="endtime")
	private Date endTime;
	@Id
	@Column(name="apptid", scale=18, precision=0)
	private Long id;
	private String location;
	private String notes;
	private Float priority;
	@Column(name="staffname")
	private String staff;
	@Temporal(TemporalType.TIME)
	@Column(name="starttime")
	private Date startTime;
	@Version
	private Integer version;

	public String getActivity() {
		return activity;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getNotes() {
		return notes;
	}

	public Float getPriority() {
		return priority;
	}

	public String getStaff() {
		return staff;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public void setPriority(Float priority) {
		this.priority = priority;
	}


	public void setStaff(String staff) {
		this.staff = staff;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public static Appointment findAppointment(Long id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			return em.find(Appointment.class, id);
		} finally {
			em.close();
		}
	}

	public static List<Appointment> findAppointmentEntriesByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			return em.createQuery("SELECT a From Appointment a WHERE a.caseNumber = :caseNumber Order By a.appointmentDate DESC", Appointment.class)
				.setParameter("caseNumber", caseNumber)
				.getResultList();
		} finally {
			em.close();
		}
	}
}
