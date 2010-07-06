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
public class ReferralDTO implements Serializable, Referral {

	private Date admissionDate;
	private Date dischargeDate;
	private String disposition;
	private Date lastEdit;
	private String lastEditBy;
	private Date lastService;
	private String levelOfCare;
	private String planType;
	private String program;
	private Date referralDate;
	private String referralSource;
	private String referralType;
	private String takenByStaff;
	private String UCI;
	private String UPId;
	private String caseNumber;

	@Override
	public Date getAdmissionDate() {
		return admissionDate;
	}

	@Override
	public Date getDischargeDate() {
		return dischargeDate;
	}

	@Override
	public String getDisposition() {
		return disposition;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public Date getLastService() {
		return lastService;
	}

	@Override
	public String getLevelOfCare() {
		return levelOfCare;
	}

	@Override
	public String getPlanType() {
		return planType;
	}

	@Override
	public String getProgram() {
		return program;
	}

	@Override
	public Date getReferralDate() {
		return referralDate;
	}

	@Override
	public String getReferralSource() {
		return referralSource;
	}

	@Override
	public String getReferralType() {
		return referralType;
	}

	@Override
	public String getTakenByStaff() {
		return takenByStaff;
	}

	@Override
	public String getUCI() {
		return UCI;
	}

	@Override
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Override
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Override
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	@Override
	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}

	@Override
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	@Override
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	@Override
	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	@Override
	public void setTakenByStaff(String takenByStaff) {
		this.takenByStaff = takenByStaff;
	}

	@Override
	public void setUCI(String UCI) {
		this.UCI = UCI;
	}

	@Override
	public String getUPId() {
		return UPId;
	}

	@Override
	public void setUPId(String UPId) {
		this.UPId = UPId;
	}

	@Override
	public ReferralDTO toDto() {
		return this;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
}
