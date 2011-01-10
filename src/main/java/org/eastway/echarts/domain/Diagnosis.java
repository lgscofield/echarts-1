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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Diagnosis {
	@PersistenceContext
	transient EntityManager entityManager;
	@ManyToOne
	@JoinColumn(name = "axis1a")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis1A;
	@ManyToOne
	@JoinColumn(name = "axis1b")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis1B;
	@ManyToOne
	@JoinColumn(name = "axis1c")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis1C;
	@ManyToOne
	@JoinColumn(name = "axis1d")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis1D;
	@ManyToOne
	@JoinColumn(name = "axis1e")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis1E;
	@ManyToOne
	@JoinColumn(name = "axis2a")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis2A;
	@ManyToOne
	@JoinColumn(name = "axis2b")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis2B;
	@ManyToOne
	@JoinColumn(name = "axis2c")
	@NotFound(action=NotFoundAction.IGNORE)
	private DiagnosisCode axis2C;
	private String axis3;
	private String axis4;
	private String caseNumber;
	private Integer currentGAF;
	private Date date;
	private Integer highestGAF;
	@Id
	private Long id;
	private Date lastEdit;
	private String lastEditBy;
	@Version
	@Column(name = "version")
	private Integer version;

	public DiagnosisCode getAxis1A() {
		return axis1A;
	}

	public DiagnosisCode getAxis1B() {
		return axis1B;
	}

	public DiagnosisCode getAxis1C() {
		return axis1C;
	}

	public DiagnosisCode getAxis1D() {
		return axis1D;
	}

	public DiagnosisCode getAxis1E() {
		return axis1E;
	}

	public DiagnosisCode getAxis2A() {
		return axis2A;
	}

	public DiagnosisCode getAxis2B() {
		return axis2B;
	}

	public DiagnosisCode getAxis2C() {
		return axis2C;
	}

	public String getAxis3() {
		return axis3;
	}

	public String getAxis4() {
		return axis4;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public Integer getCurrentGAF() {
		return currentGAF;
	}

	public Date getDate() {
		return date;
	}

	public Integer getHighestGAF() {
		return highestGAF;
	}

	public Long getId() {
		return id;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setAxis1A(DiagnosisCode axis1A) {
		this.axis1A = axis1A;
	}

	public void setAxis1B(DiagnosisCode axis1B) {
		this.axis1B = axis1B;
	}

	public void setAxis1C(DiagnosisCode axis1C) {
		this.axis1C = axis1C;
	}

	public void setAxis1D(DiagnosisCode axis1D) {
		this.axis1D = axis1D;
	}

	public void setAxis1E(DiagnosisCode axis1E) {
		this.axis1E = axis1E;
	}

	public void setAxis2A(DiagnosisCode axis2A) {
		this.axis2A = axis2A;
	}

	public void setAxis2B(DiagnosisCode axis2B) {
		this.axis2B = axis2B;
	}

	public void setAxis2C(DiagnosisCode axis2C) {
		this.axis2C = axis2C;
	}

	public void setAxis3(String axis3) {
		this.axis3 = axis3;
	}

	public void setAxis4(String axis4) {
		this.axis4 = axis4;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setCurrentGAF(Integer currentGAF) {
		this.currentGAF = currentGAF;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setHighestGAF(Integer highestGAF) {
		this.highestGAF = highestGAF;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

    public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
        EntityManager em = new Diagnosis().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static Diagnosis findDiagnosis(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Diagnosis.class, id);
	}

	public static List<Diagnosis> findDiagnosesByCaseNumber(String caseNumber) {
		return entityManager().createQuery("SELECT d FROM Diagnosis d WHERE d.caseNumber = :caseNumber ORDER BY d.date DESC", Diagnosis.class)
			.setParameter("caseNumber", caseNumber)
			.getResultList();
	}
}
