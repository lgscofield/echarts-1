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
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Medication {
	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	private Long id;
	private String medication;
	private String caseNumber;
	private Date lastEdit;
	private String lastEditBy;
	private String medMonitoring;
	private String labsOrdered;
	@Version
	@Column(name = "version")
	private Integer version;

	public Long getId() {
		return id;
	}

	public String getMedication() {
		return medication;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setMedMonitoring(String medMonitoring) {
		this.medMonitoring = medMonitoring;
	}

	public String getMedMonitoring() {
		return medMonitoring;
	}

	public void setLabsOrdered(String labsOrdered) {
		this.labsOrdered = labsOrdered;
	}

	public String getLabsOrdered() {
		return labsOrdered;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Medication().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Medication findMedication(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Medication.class, id);
	}

	public static List<Medication> findMedicationsByCaseNumber(String caseNumber) {
		return entityManager().createQuery("SELECT m FROM Medication m WHERE m.caseNumber = :caseNumber", Medication.class)
			.setParameter("caseNumber", caseNumber)
			.getResultList();
	}
}
