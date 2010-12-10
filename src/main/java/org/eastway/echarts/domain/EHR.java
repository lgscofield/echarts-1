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
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.TableGenerator;

import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class EHR {
	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	@Column(name="ehr_id")
	private Long id;

	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name="subject_id", insertable=false, updatable=false)
	private Patient patient;

	@OneToOne(targetEntity = Demographics.class)
	@JoinColumn(name="subject_id", insertable=false, updatable=false)
	private Demographics demographics;

	@Version
	@Column(name = "version")
	private Integer version;

	@Column(name="time_created")
	private Date timeCreated;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
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

	public Integer getVersion() {
		return version;
	}

	// maybe gwt will someday be able to handle these
	public void setAssignments(List<Assignment> assignments) {
	}

	public List<Assignment> getAssignments() {
		return null;
	}

    public static final EntityManager entityManager() {
        EntityManager em = new EHR().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static EHR findEHR(Long id) {
		if (id == null)
			return null;
		return entityManager().find(EHR.class, id);
	}

	public static EHR findEHRByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		return entityManager().createQuery(
			"SELECT ehr FROM EHR ehr WHERE ehr.patient.caseNumber = :caseNumber", EHR.class)
				.setParameter("caseNumber", caseNumber)
				.getSingleResult();
	}
}
