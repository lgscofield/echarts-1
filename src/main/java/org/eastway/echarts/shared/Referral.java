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

public interface Referral {
	public void setProgram(String program);

	public String getProgram();

	public void setUCI(String UCI);

	public String getUCI();

	public void setUPId(String UPId);

	public String getUPId();

	public void setReferralSource(String referralSource);

	public String getReferralSource();

	public void setReferralType(String referralType);

	public String getReferralType();

	public void setReferralDate(Date referralDate);

	public Date getReferralDate();

	public void setTakenByStaff(String takenByStaff);

	public String getTakenByStaff();

	public void setAdmissionDate(Date admissionDate);

	public Date getAdmissionDate();

	public void setLevelOfCare(String levelOfCare);

	public String getLevelOfCare();

	public void setPlanType(String planType);

	public String getPlanType();

	public void setLastService(Date lastService);

	public Date getLastService();

	public void setDischargeDate(Date dischargeDate);

	public Date getDischargeDate();

	public void setDisposition(String disposition);

	public String getDisposition();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public ReferralDTO toDto();

	void setCaseNumber(String caseNumber);

	String getCaseNumber();
}