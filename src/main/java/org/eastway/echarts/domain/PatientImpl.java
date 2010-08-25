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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.PatientDTO;

@Entity
@Table(name = "Patient")
@SecondaryTable(name = "Tickler",
		pkJoinColumns=@PrimaryKeyJoinColumn(name="case_number"))
public class PatientImpl extends Patient {
	@Id
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	@ManyToOne
	private CodeImpl caseStatus;
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
	private Long isTitleTwenty;
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

	public PatientImpl() {}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void setCaseStatus(Code caseStatus) {
		this.caseStatus = (CodeImpl) caseStatus;
	}

	@Override
	public Code getCaseStatus() {
		return caseStatus;
	}

	@Override
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String getSsn() {
		return ssn;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public String getMiddleInitial() {
		return middleInitial;
	}

	@Override
	public Date getAodGoal() {
		return aodGoal;
	}

	@Override
	public Date getDateStamp() {
		return dateStamp;
	}

	@Override
	public Date getDiagnosticAssessmentDateCompleted() {
		return diagnosticAssessmentDateCompleted;
	}

	@Override
	public Date getFinancialDateCompleted() {
		return financialDateCompleted;
	}

	@Override
	public Date getHealthHistoryDateCompleted() {
		return healthHistoryDateCompleted;
	}

	@Override
	public Date getHipaaDateCompleted() {
		return hipaaDateCompleted;
	}

	@Override
	public Date getIspDateCompleted() {
		return ispDateCompleted;
	}

	@Override
	public Date getIspReviewDateCompleted() {
		return ispReviewDateCompleted;
	}

	@Override
	public Date getLevelOfCareAdmissionDate() {
		return levelOfCareAdmissionDate;
	}

	@Override
	public Date getLevelOfCareCs() {
		return levelOfCareCs;
	}

	@Override
	public Date getOutcomesAgencyDateCompleted() {
		return outcomesAgencyDateCompleted;
	}

	@Override
	public Date getOutcomesConsumerDateCompleted() {
		return outcomesConsumerDateCompleted;
	}

	@Override
	public Date getOutcomesProviderDateCompleted() {
		return outcomesProviderDateCompleted;
	}

	@Override
	public boolean isTitleTwenty() {
		return isTitleTwenty == null || isTitleTwenty == 0L ? false : true;
	}

	@Override
	public void setAodGoal(Date aodGoal) {
		this.aodGoal = aodGoal;
	}

	@Override
	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}

	@Override
	public void setDiagnosticAssessmentDateCompleted(Date diagnosticAssessmentDateCompleted) {
		this.diagnosticAssessmentDateCompleted = diagnosticAssessmentDateCompleted;
	}

	@Override
	public void setFinancialDateCompleted(Date financialDateCompleted) {
		this.financialDateCompleted = financialDateCompleted;
	}

	@Override
	public void setHealthHistoryDateCompleted(Date healthHistoryDateCompleted) {
		this.healthHistoryDateCompleted = healthHistoryDateCompleted;
	}

	@Override
	public void setHipaaDateCompleted(Date hipaaDateCompleted) {
		this.hipaaDateCompleted = hipaaDateCompleted;
	}

	@Override
	public void setIsTitleTwenty(boolean isTitleTwenty) {
		this.isTitleTwenty = (isTitleTwenty) ? 1L : 0L;
	}

	@Override
	public void setIspDateCompleted(Date ispDateCompleted) {
		this.ispDateCompleted = ispDateCompleted;
	}

	@Override
	public void setIspReviewDateCompleted(Date ispReviewDateCompleted) {
		this.ispReviewDateCompleted = ispReviewDateCompleted;
	}

	@Override
	public void setLevelOfCareAdmissionDate(Date levelOfCareAdmissionDate) {
		this.levelOfCareAdmissionDate = levelOfCareAdmissionDate;
	}

	@Override
	public void setLevelOfCareCs(Date levelOfCareCs) {
		this.levelOfCareCs = levelOfCareCs;
	}

	@Override
	public void setOutcomesAgencyDateCompleted(Date outcomesAgencyDateCompleted) {
		this.outcomesAgencyDateCompleted = outcomesAgencyDateCompleted;
	}

	@Override
	public void setOutcomesConsumerDateCompleted(Date outcomesConsumerDateCompleted) {
		this.outcomesConsumerDateCompleted = outcomesConsumerDateCompleted;
	}

	@Override
	public void setOutcomesProviderDateCompleted(Date outcomesProviderDateCompleted) {
		this.outcomesProviderDateCompleted = outcomesProviderDateCompleted;
	}

	@Override
	public PatientDTO toDto() {
		PatientDTO dto = new PatientDTO();
		dto.setAlias(alias);
		dto.setAodGoal(aodGoal);
		dto.setCaseNumber(caseNumber);
		dto.setCaseStatus(caseStatus == null ? new CodeDTO() : caseStatus.toDto());
		dto.setDateStamp(dateStamp);
		dto.setDiagnosticAssessmentDateCompleted(diagnosticAssessmentDateCompleted);
		dto.setFinancialDateCompleted(financialDateCompleted);
		dto.setFirstName(firstName);
		dto.setHealthHistoryDateCompleted(healthHistoryDateCompleted);
		dto.setHipaaDateCompleted(hipaaDateCompleted);
		dto.setIspDateCompleted(ispDateCompleted);
		dto.setIspReviewDateCompleted(ispReviewDateCompleted);
		dto.setIsTitleTwenty(isTitleTwenty());
		dto.setLastEdit(lastEdit);
		dto.setLastEditBy(lastEditBy);
		dto.setLastName(lastName);
		dto.setLevelOfCareAdmissionDate(levelOfCareAdmissionDate);
		dto.setLevelOfCareCs(levelOfCareCs);
		dto.setMiddleInitial(middleInitial);
		dto.setOutcomesAgencyDateCompleted(outcomesAgencyDateCompleted);
		dto.setOutcomesConsumerDateCompleted(outcomesConsumerDateCompleted);
		dto.setOutcomesProviderDateCompleted(outcomesProviderDateCompleted);
		dto.setSsn(ssn);
		dto.setSuffix(suffix);
		return dto;
	}

	@Override
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
}
