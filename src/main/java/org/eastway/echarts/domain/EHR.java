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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.eastway.echarts.server.EchartsEntityManagerFactory;

import com.google.gwt.requestfactory.shared.Version;

@Entity
public class EHR {
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	@Column(name="ehr_id")
	private long id;

	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name="subject_id", insertable=false, updatable=false)
	private Patient patient;

	@OneToOne(targetEntity = Demographics.class)
	@JoinColumn(name="subject_id", insertable=false, updatable=false)
	private Demographics demographics;

	@OneToMany(targetEntity = Assignment.class)
	@JoinColumn(name="caseNumber")
	private List<Assignment> assignments;

	@Version
	private Integer version;

	@Column(name="time_created")
	private Date timeCreated;

	public EHR() { }

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

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

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public Integer getVersion() {
		return version;
	}

	public static EHR findEHR(Long id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			EHR ehr = em.find(EHR.class, id);
			return ehr;
		} finally {
			em.close();
		}
	}

	public static EHR findEHRByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			EHR ehr = em.createQuery(
					"SELECT ehr FROM EHR ehr WHERE ehr.patient = '" + caseNumber + "'", EHR.class)
					.getSingleResult();
			return ehr;
		} finally {
			em.close();
		}
	}
}
