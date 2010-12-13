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
	Long getId();

	void setId(Long id);

	void setCaseNumber(String caseNumber);

	String getCaseNumber();

	void setAssignmentDate(Date assignmentDate);

	Date getAssignmentDate();

	void setService(String service);

	String getService();

	void setStaff(String staff);

	String getStaff();

	void setOrderDate(Date orderDate);

	Date getOrderDate();

	void setDisposition(String disposition);

	String getDisposition();

	void setStaffName(String staffName);

	String getStaffName();

	void setTermDate(Date termDate);

	Date getTermDate();

	void setPlanId(Integer planId);

	Integer getPlanId();

	void setTrtEpisode(Short trtEpisode);

	Short getTrtEpisode();

	void setProgram(String program);

	String getProgram();

	void setLastEdit(Date lastEdit);

	Date getLastEdit();

	void setLastEditBy(String lastEditBy);

	String getLastEditBy();

	void setPatient(PatientProxy patient);

	PatientProxy getPatient();

	void setDemographics(DemographicsProxy demographics);

	DemographicsProxy getDemographics();
}