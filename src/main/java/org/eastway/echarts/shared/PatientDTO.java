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
import java.util.Date;

@SuppressWarnings("serial")
public class PatientDTO extends Patient implements Serializable {
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	private Code caseStatus;
	private String ssn;
	private String lastEditBy;
	private Date lastEdit;
	private Date aodGoal;
	private Date dateStamp;
	private Date diagnosticAssessmentDateCompleted;
	private Date financialDateCompleted;
	private Date healthHistoryDateCompleted;
	private Date hipaaDateCompleted;
	private Date ispDateCompleted;
	private Date ispReviewDateCompleted;
	private Date levelOfCareAdmissionDate;
	private Date levelOfCareCs;
	private Date outcomesAgencyDateCompleted;
	private Date outcomesConsumerDateCompleted;
	private Date outcomesProviderDateCompleted;
	private boolean isTitleTwenty;

	public PatientDTO() { }

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
		this.caseStatus = caseStatus;
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
		return isTitleTwenty;
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
		this.isTitleTwenty = isTitleTwenty;
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
		return this;
	}
}
