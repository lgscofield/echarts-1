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
package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.Assignment;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Assignment.class)
public interface AssignmentProxy extends EntityProxy {
	public void setId(Long id);

	public Long getId();

	public void setService(String service);

	public String getService();

	public void setStaffId(String staffId);

	public String getStaffId();

	public void setStaffName(String staffName);

	public String getStaffName();

	public String getCaseNumber();

	public void setCaseNumber(String caseNumber);

	public void setHipaaDateCompleted(Date hipaaDateCompleted);

	public Date getHipaaDateCompleted();

	public void setIspDateCompleted(Date ispDateCompleted);

	public Date getIspDateCompleted();

	public void setAodGoal(Date aodGoal);

	public Date getAodGoal();

	public void setIspReviewDateCompleted(Date ispReviewDateCompleted);

	public Date getIspReviewDateCompleted();

	public void setLevelOfCareAdmissionDate(Date levelOfCareAdmissionDate);

	public Date getLevelOfCareAdmissionDate();

	public void setLevelOfCareCs(Date levelOfCareCs);

	public Date getLevelOfCareCs();

	public void setHealthHistoryDateCompleted(Date healthHistoryDateCompleted);

	public Date getHealthHistoryDateCompleted();

	public void setFinancialDateCompleted(Date financialDateCompleted);

	public Date getFinancialDateCompleted();

	public void setTitleTwenty(Boolean titleTwenty);

	public Boolean getTitleTwenty();

	public void setDiagnosticAssessmentDateCompleted(Date diagnosticAssessmentDateCompleted);

	public Date getDiagnosticAssessmentDateCompleted();

	public void setOutcomesConsumerDateCompleted(Date outcomesConsumerDateCompleted);

	public Date getOutcomesConsumerDateCompleted();

	public void setOutcomesProviderDateCompleted(Date outcomesProviderDateCompleted);

	public Date getOutcomesProviderDateCompleted();

	public void setOutcomesAgencyDateCompleted(Date outcomesAgencyDateCompleted);

	public Date getOutcomesAgencyDateCompleted();

	public void setDateStamp(Date dateStamp);

	public Date getDateStamp();

	public void setName(String name);

	public String getName();

	public Date getNextAppointment();

	public void setNextAppointment(Date nextAppointment);

	public Integer getVersion();

	public Boolean getAlcoholDrug();

	public void setAlcoholDrug(Boolean isAlcoholDrug);
}