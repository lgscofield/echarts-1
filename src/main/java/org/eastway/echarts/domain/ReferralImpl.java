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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.Referral;
import org.eastway.echarts.shared.ReferralDTO;

@Entity
@Table(name = "Referral")
public class ReferralImpl implements Referral {
    @Id
    private String caseNumber;
	private String program;
	private String UCI;
	private String UPId;
	private String referralSource;
	private String referralType;
	private Date referralDate;
	private String takenByStaff;
	private Date admissionDate;
	private String levelOfCare;
	private String planType;
	private Date lastService;
	private Date dischargeDate;
	private String disposition;
	private Date lastEdit;
	private String lastEditBy;

	public ReferralImpl() { }

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public String getProgram() {
		return program;
	}

	@Override
	public void setUCI(String UCI) {
		this.UCI = UCI;
	}

	@Override
	public String getUCI() {
		return UCI;
	}

	@Override
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	@Override
	public String getReferralSource() {
		return referralSource;
	}

	@Override
	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	@Override
	public String getReferralType() {
		return referralType;
	}

	@Override
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	@Override
	public Date getReferralDate() {
		return referralDate;
	}

	@Override
	public void setTakenByStaff(String takenByStaff) {
		this.takenByStaff = takenByStaff;
	}

	@Override
	public String getTakenByStaff() {
		return takenByStaff;
	}

	@Override
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Override
	public Date getAdmissionDate() {
		return admissionDate;
	}

	@Override
	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}

	@Override
	public String getLevelOfCare() {
		return levelOfCare;
	}

	@Override
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Override
	public String getPlanType() {
		return planType;
	}

	@Override
	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	@Override
	public Date getLastService() {
		return lastService;
	}

	@Override
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Override
	public Date getDischargeDate() {
		return dischargeDate;
	}

	@Override
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	@Override
	public String getDisposition() {
		return disposition;
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
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public void setUPId(String uPId) {
		UPId = uPId;
	}

	@Override
	public String getUPId() {
		return UPId;
	}

	@Override
	public ReferralDTO toDto() {
		ReferralDTO dto = new ReferralDTO();
		dto.setAdmissionDate(admissionDate);
		dto.setDischargeDate(dischargeDate);
		dto.setDisposition(disposition);
		dto.setLastEdit(lastEdit);
		dto.setLastEditBy(lastEditBy);
		dto.setLastService(lastService);
		dto.setLevelOfCare(levelOfCare);
		dto.setPlanType(planType);
		dto.setProgram(program);
		dto.setReferralDate(referralDate);
		dto.setReferralSource(referralSource);
		dto.setReferralType(referralType);
		dto.setTakenByStaff(takenByStaff);
		dto.setUCI(UCI);
		dto.setUPId(UPId);
		return dto;
	}
}
