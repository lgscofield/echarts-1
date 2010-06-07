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


public interface Assignment {

	public void setId(Integer id);

	public Integer getId();

	public void setAssignmentDate(Date assignmentDate);

	public Date getAssignmentDate();

	public void setService(String service);

	public String getService();

	public void setStaff(String staff);

	public String getStaff();

	public void setOrderDate(Date orderDate);

	public Date getOrderDate();

	public void setDisposition(String disposition);

	public String getDisposition();

	public void setStaffName(String staffName);

	public String getStaffName();

	public void setTermDate(Date termDate);

	public Date getTermDate();

	public void setPlanId(Integer planId);

	public Integer getPlanId();

	public void setTrtEpisode(Integer trtEpisode);

	public Integer getTrtEpisode();

	public void setProgram(String program);

	public String getProgram();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setEhr(EHR ehr);

	public EHR getEhr();

	public AssignmentDTO toDto();
}