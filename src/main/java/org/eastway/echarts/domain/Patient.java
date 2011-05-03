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
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@SecondaryTable(name = "Tickler",
		pkJoinColumns=@PrimaryKeyJoinColumn(name="case_number"))
public class Patient {
	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	@ManyToOne
	private Code caseStatus;
	private String ssn;
	private String lastEditBy;
	@NotNull
	private Date lastEdit;
	@Column(name="HIPAA", table="tickler")
	private Date hipaaDateCompleted;
	@Column(name="ISP", table="tickler")
	private Date ispDateCompleted;
	@Column(name="AoD_Goal", table="tickler")
	private Date aodGoal;
	@Column(name="ISP6", table="tickler")
	private Date ispReviewDateCompleted;
	@Column(name="LOC_Ad", table="tickler")
	private Date levelOfCareAdmissionDate;
	@Column(name="LOC_CS", table="tickler")
	private Date levelOfCareCs;
	@Column(name="HTH_HX", table="tickler")
	private Date healthHistoryDateCompleted;
	@Column(name="FAF", table="tickler")
	private Date financialDateCompleted;
	@Column(name="FAF_XX", table="tickler", scale=18, precision=0)
	private Long titleTwenty;
	@Column(name="DA", table="tickler")
	private Date diagnosticAssessmentDateCompleted;
	@Column(name="OOCi", table="tickler")
	private Date outcomesConsumerDateCompleted;
	@Column(name="OOCW", table="tickler")
	private Date outcomesProviderDateCompleted;
	@Column(name="OOCP", table="tickler")
	private Date outcomesAgencyDateCompleted;
	@Column(name="Date_Stamp", table="tickler")
	private Date dateStamp;
	@Version
	@Column(name = "version")
	private Integer version;

	public String getId() {
		return caseNumber;
	}

	public String getName() {
		return new StringBuilder()
			.append(getLastName())
			.append((getSuffix() == null ? ", " : " " + getSuffix() + ", "))
			.append(getFirstName())
			.append((getMiddleInitial() == null ? "" : " " + getMiddleInitial())).toString();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setCaseStatus(Code caseStatus) {
		this.caseStatus = (Code) caseStatus;
	}

	public Code getCaseStatus() {
		return caseStatus;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public Date getAodGoal() {
		return aodGoal;
	}

	public Date getDateStamp() {
		return dateStamp;
	}

	public Date getDiagnosticAssessmentDateCompleted() {
		return diagnosticAssessmentDateCompleted;
	}

	public Date getFinancialDateCompleted() {
		return financialDateCompleted;
	}

	public Date getHealthHistoryDateCompleted() {
		return healthHistoryDateCompleted;
	}

	public Date getHipaaDateCompleted() {
		return hipaaDateCompleted;
	}

	public Date getIspDateCompleted() {
		return ispDateCompleted;
	}

	public Date getIspReviewDateCompleted() {
		return ispReviewDateCompleted;
	}

	public Date getLevelOfCareAdmissionDate() {
		return levelOfCareAdmissionDate;
	}

	public Date getLevelOfCareCs() {
		return levelOfCareCs;
	}

	public Date getOutcomesAgencyDateCompleted() {
		return outcomesAgencyDateCompleted;
	}

	public Date getOutcomesConsumerDateCompleted() {
		return outcomesConsumerDateCompleted;
	}

	public Date getOutcomesProviderDateCompleted() {
		return outcomesProviderDateCompleted;
	}

	public Boolean getTitleTwenty() {
		return titleTwenty == null || titleTwenty == 0L ? false : true;
	}

	public void setAodGoal(Date aodGoal) {
		this.aodGoal = aodGoal;
	}

	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}

	public void setDiagnosticAssessmentDateCompleted(Date diagnosticAssessmentDateCompleted) {
		this.diagnosticAssessmentDateCompleted = diagnosticAssessmentDateCompleted;
	}

	public void setFinancialDateCompleted(Date financialDateCompleted) {
		this.financialDateCompleted = financialDateCompleted;
	}

	public void setHealthHistoryDateCompleted(Date healthHistoryDateCompleted) {
		this.healthHistoryDateCompleted = healthHistoryDateCompleted;
	}

	public void setHipaaDateCompleted(Date hipaaDateCompleted) {
		this.hipaaDateCompleted = hipaaDateCompleted;
	}

	public void setTitleTwenty(Boolean titleTwenty) {
		this.titleTwenty = (titleTwenty) ? 1L : 0L;
	}

	public void setIspDateCompleted(Date ispDateCompleted) {
		this.ispDateCompleted = ispDateCompleted;
	}

	public void setIspReviewDateCompleted(Date ispReviewDateCompleted) {
		this.ispReviewDateCompleted = ispReviewDateCompleted;
	}

	public void setLevelOfCareAdmissionDate(Date levelOfCareAdmissionDate) {
		this.levelOfCareAdmissionDate = levelOfCareAdmissionDate;
	}

	public void setLevelOfCareCs(Date levelOfCareCs) {
		this.levelOfCareCs = levelOfCareCs;
	}

	public void setOutcomesAgencyDateCompleted(Date outcomesAgencyDateCompleted) {
		this.outcomesAgencyDateCompleted = outcomesAgencyDateCompleted;
	}

	public void setOutcomesConsumerDateCompleted(Date outcomesConsumerDateCompleted) {
		this.outcomesConsumerDateCompleted = outcomesConsumerDateCompleted;
	}

	public void setOutcomesProviderDateCompleted(Date outcomesProviderDateCompleted) {
		this.outcomesProviderDateCompleted = outcomesProviderDateCompleted;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Patient().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Patient findPatient(String id) {
		if (id == null)
			return null;
		return entityManager().find(Patient.class, id);
	}

	@SuppressWarnings("unchecked")
	public static Long findPatientsLikeCount(String searchTerm) {
		return ((Number)entityManager().createNativeQuery("SELECT count(case_number + ' - ' + full_name) as search FROM patient inner join codes on patient.case_status = codes.code_id where column_name = 'CaseStatus' AND (case_number like :searchTerm + '%' OR last_name like :searchTerm + '%' OR first_name like :searchTerm + '%' OR first_name + ' ' + last_name like :searchTerm + '%')")
			.setParameter("searchTerm", searchTerm)
			.getSingleResult()).longValue();
	}

	@SuppressWarnings("unchecked")
	public static List<String> findPatientsLike(String searchTerm) {
		return entityManager().createNativeQuery("SELECT case_number + ' - ' + full_name as search FROM patient inner join codes on patient.case_status = codes.code_id where column_name = 'CaseStatus' AND (case_number like :searchTerm + '%' OR last_name like :searchTerm + '%' OR first_name like :searchTerm + '%' OR first_name + ' ' + last_name like :searchTerm + '%')")
			.setParameter("searchTerm", searchTerm)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<String> findPatientsEntriesLike(String searchTerm, Integer startPosition, Integer maxResult) {
		return entityManager().createNativeQuery("SELECT case_number + ' - ' + full_name as search FROM patient inner join codes on patient.case_status = codes.code_id where column_name = 'CaseStatus' AND (case_number like :searchTerm + '%' OR last_name like :searchTerm + '%' OR first_name like :searchTerm + '%' OR first_name + ' ' + last_name like :searchTerm + '%')")
			.setParameter("searchTerm", searchTerm)
			.setFirstResult(startPosition)
			.setMaxResults(maxResult)
			.getResultList();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CaseNumber: ").append(getCaseNumber()).append(", ")
			.append("FirstName: ").append(getFirstName()).append(", ")
			.append("LastName: ").append(getLastName()).append(", ")
			.append("MiddleInitial: ").append(getMiddleInitial()).append(", ")
			.append("Suffix: ").append(getSuffix()).append(", ")
			.append("Alias: ").append(getAlias()).append(", ")
			.append("CaseStatus: ").append(getCaseStatus()).append(", ")
			.append("SSN: ").append(getSsn()).append(", ")
			.append("LastEditBy: ").append(getLastEditBy()).append(", ")
			.append("LastEdit: ").append(getLastEdit());
		return sb.toString();
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null) this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			Patient attached = Patient.findPatient(this.caseNumber);
			this.entityManager.remove(attached);
		}
	}
}
