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

import java.util.Date;

public abstract class Patient {

	public String getName() {
		return new StringBuilder()
			.append(getLastName())
			.append((getSuffix() == null ? ", " : " " + getSuffix() + ", "))
			.append(getFirstName())
			.append((getMiddleInitial() == null ? "" : " " + getMiddleInitial())).toString();
	}

	public abstract void setFirstName(String firstName);

	public abstract String getFirstName();

	public abstract void setLastName(String lastName);

	public abstract String getLastName();

	public abstract void setSuffix(String suffix);

	public abstract String getSuffix();

	public abstract void setAlias(String alias);

	public abstract String getAlias();

	public abstract void setCaseStatus(Code caseStatus);

	public abstract Code getCaseStatus();

	public abstract void setSsn(String ssn);

	public abstract String getSsn();

	public abstract void setLastEditBy(String lastEditBy);

	public abstract String getLastEditBy();

	public abstract void setLastEdit(Date lastEdit);

	public abstract Date getLastEdit();

	public abstract void setCaseNumber(String caseNumber);

	public abstract String getCaseNumber();

	public abstract void setMiddleInitial(String middleInitial);

	public abstract String getMiddleInitial();

	public abstract void setHipaaDateCompleted(Date hipaaDateCompleted);

	public abstract Date getHipaaDateCompleted();

	public abstract void setIspDateCompleted(Date ispDateCompleted);

	public abstract Date getIspDateCompleted();

	public abstract void setAodGoal(Date aodGoal);

	public abstract Date getAodGoal();

	public abstract void setIspReviewDateCompleted(Date ispReviewDateCompleted);

	public abstract Date getIspReviewDateCompleted();

	public abstract void setLevelOfCareAdmissionDate(Date levelOfCareAdmissionDate);

	public abstract Date getLevelOfCareAdmissionDate();

	public abstract void setLevelOfCareCs(Date levelOfCareCs);

	public abstract Date getLevelOfCareCs();

	public abstract void setHealthHistoryDateCompleted(Date healthHistoryDateCompleted);

	public abstract Date getHealthHistoryDateCompleted();

	public abstract void setFinancialDateCompleted(Date financialDateCompleted);

	public abstract Date getFinancialDateCompleted();

	public abstract void setIsTitleTwenty(Boolean isTitleTwenty);

	public abstract Boolean isTitleTwenty();

	public abstract void setDiagnosticAssessmentDateCompleted(Date diagnosticAssessmentDateCompleted);

	public abstract Date getDiagnosticAssessmentDateCompleted();

	public abstract void setOutcomesConsumerDateCompleted(Date outcomesConsumerDateCompleted);

	public abstract Date getOutcomesConsumerDateCompleted();

	public abstract void setOutcomesProviderDateCompleted(Date outcomesProviderDateCompleted);

	public abstract Date getOutcomesProviderDateCompleted();

	public abstract void setOutcomesAgencyDateCompleted(Date outcomesAgencyDateCompleted);

	public abstract Date getOutcomesAgencyDateCompleted();

	public abstract void setDateStamp(Date dateStamp);

	public abstract Date getDateStamp();

	public abstract PatientDTO toDto();

}