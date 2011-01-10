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

import org.eastway.echarts.domain.Referral;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Referral.class)
public interface ReferralProxy extends EntityProxy {

	void setId(String id);

	String getId();

	void setProgram(String program);

	String getProgram();

	void setUCI(String UCI);

	String getUCI();

	void setReferralSource(String referralSource);

	String getReferralSource();

	void setReferralType(String referralType);

	String getReferralType();

	void setReferralDate(Date referralDate);

	Date getReferralDate();

	void setTakenByStaff(String takenByStaff);

	String getTakenByStaff();

	void setAdmissionDate(Date admissionDate);

	Date getAdmissionDate();

	void setLevelOfCare(String levelOfCare);

	String getLevelOfCare();

	void setPlanType(String planType);

	String getPlanType();

	void setLastService(Date lastService);

	Date getLastService();

	void setDischargeDate(Date dischargeDate);

	Date getDischargeDate();

	void setDisposition(String disposition);

	String getDisposition();

	void setLastEdit(Date lastEdit);

	Date getLastEdit();

	void setLastEditBy(String lastEditBy);

	String getLastEditBy();

	void setUPId(String uPId);

	String getUPId();

	Integer getVersion();

	void setVersion(Integer version);

}