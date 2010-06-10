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

public interface Patient {

	public void setEhr(EHR ehr);

	public EHR getEhr();

	public void setEhrId(long ehrId);

	public long getEhrId();

	public void setId(long id);

	public long getId();

	public String getName();

	public void setFirstName(String firstName);

	public String getFirstName();

	public void setLastName(String lastName);

	public String getLastName();

	public void setSuffix(String suffix);

	public String getSuffix();

	public void setAlias(String alias);

	public String getAlias();

	public void setCaseStatus(Code caseStatus);

	public Code getCaseStatus();

	public void setSsn(String ssn);

	public String getSsn();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setMiddleInitial(String middleInitial);

	public String getMiddleInitial();

	public PatientDTO toDto();
}