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

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "vticklerlayout")
public class Assignment {

	@Id
	private Long id;

	private String service;

	@Column(name = "staff")
	private String staffId;

	@Column(name = "staffname")
	private String staffName;

	private String caseNumber;

	@Column(name = "full_name")
	private String name;

	@Column(name = "HIPAA")
	private Date hipaaDateCompleted;

	@Column(name = "ISP")
	private Date ispDateCompleted;

	@Column(name = "AoD_Goal")
	private Date aodGoal;

	@Column(name = "ISP6")
	private Date ispReviewDateCompleted;

	@Column(name = "LOC_Ad")
	private Date levelOfCareAdmissionDate;

	@Column(name = "LOC_CS")
	private Date levelOfCareCs;

	@Column(name = "HTH_HX")
	private Date healthHistoryDateCompleted;

	@Column(name = "FAF")
	private Date financialDateCompleted;

	@Column(name = "FAF_XX", scale = 18, precision = 0)
	private Long titleTwenty;

	@Column(name = "DA")
	private Date diagnosticAssessmentDateCompleted;

	@Column(name = "OOCi")
	private Date outcomesConsumerDateCompleted;

	@Column(name = "OOCW")
	private Date outcomesProviderDateCompleted;

	@Column(name = "OOCP")
	private Date outcomesAgencyDateCompleted;

	private Boolean isAlcoholDrug;

	@Column(name = "Date_Stamp")
	private Date dateStamp;

	@Column(name="nextappt")
	private Date nextAppointment;

	@Version
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setHipaaDateCompleted(Date hipaaDateCompleted) {
		this.hipaaDateCompleted = hipaaDateCompleted;
	}

	public Date getHipaaDateCompleted() {
		return hipaaDateCompleted;
	}

	public void setIspDateCompleted(Date ispDateCompleted) {
		this.ispDateCompleted = ispDateCompleted;
	}

	public Date getIspDateCompleted() {
		return ispDateCompleted;
	}

	public void setAodGoal(Date aodGoal) {
		this.aodGoal = aodGoal;
	}

	public Date getAodGoal() {
		return aodGoal;
	}

	public void setIspReviewDateCompleted(Date ispReviewDateCompleted) {
		this.ispReviewDateCompleted = ispReviewDateCompleted;
	}

	public Date getIspReviewDateCompleted() {
		return ispReviewDateCompleted;
	}

	public void setLevelOfCareAdmissionDate(Date levelOfCareAdmissionDate) {
		this.levelOfCareAdmissionDate = levelOfCareAdmissionDate;
	}

	public Date getLevelOfCareAdmissionDate() {
		return levelOfCareAdmissionDate;
	}

	public void setLevelOfCareCs(Date levelOfCareCs) {
		this.levelOfCareCs = levelOfCareCs;
	}

	public Date getLevelOfCareCs() {
		return levelOfCareCs;
	}

	public void setHealthHistoryDateCompleted(Date healthHistoryDateCompleted) {
		this.healthHistoryDateCompleted = healthHistoryDateCompleted;
	}

	public Date getHealthHistoryDateCompleted() {
		return healthHistoryDateCompleted;
	}

	public void setFinancialDateCompleted(Date financialDateCompleted) {
		this.financialDateCompleted = financialDateCompleted;
	}

	public Date getFinancialDateCompleted() {
		return financialDateCompleted;
	}

	public void setTitleTwenty(Boolean titleTwenty) {
		this.titleTwenty = (titleTwenty) ? 1L : 0L;
	}

	public Boolean getTitleTwenty() {
		return titleTwenty == null || titleTwenty == 0L ? false : true;
	}

	public void setDiagnosticAssessmentDateCompleted(
			Date diagnosticAssessmentDateCompleted) {
		this.diagnosticAssessmentDateCompleted = diagnosticAssessmentDateCompleted;
	}

	public Date getDiagnosticAssessmentDateCompleted() {
		return diagnosticAssessmentDateCompleted;
	}

	public void setOutcomesConsumerDateCompleted(
			Date outcomesConsumerDateCompleted) {
		this.outcomesConsumerDateCompleted = outcomesConsumerDateCompleted;
	}

	public Date getOutcomesConsumerDateCompleted() {
		return outcomesConsumerDateCompleted;
	}

	public void setOutcomesProviderDateCompleted(
			Date outcomesProviderDateCompleted) {
		this.outcomesProviderDateCompleted = outcomesProviderDateCompleted;
	}

	public Date getOutcomesProviderDateCompleted() {
		return outcomesProviderDateCompleted;
	}

	public void setOutcomesAgencyDateCompleted(Date outcomesAgencyDateCompleted) {
		this.outcomesAgencyDateCompleted = outcomesAgencyDateCompleted;
	}

	public Date getOutcomesAgencyDateCompleted() {
		return outcomesAgencyDateCompleted;
	}

	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}

	public Date getDateStamp() {
		return dateStamp;
	}

	public void setAlcoholDrug(Boolean isAlcoholDrug) {
		this.isAlcoholDrug = isAlcoholDrug;
	}

	public Boolean getAlcoholDrug() {
		return isAlcoholDrug;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNextAppointment(Date nextAppointment) {
		this.nextAppointment = nextAppointment;
	}

	public Date getNextAppointment() {
		return nextAppointment;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Assignment().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Assignment findAssignment(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Assignment.class, id);
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			Assignment attached = Assignment.findAssignment(this.id);
			this.entityManager.remove(attached);
		}
	}
}
