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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Referral {
	@PersistenceContext
	transient EntityManager entityManager;
    @Id
    @Column(name = "case_number")
    private String id;
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
	@Version
	@Column(name = "version")
	private Integer version;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return program;
	}

	public void setUCI(String UCI) {
		this.UCI = UCI;
	}

	public String getUCI() {
		return UCI;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	public String getReferralType() {
		return referralType;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setTakenByStaff(String takenByStaff) {
		this.takenByStaff = takenByStaff;
	}

	public String getTakenByStaff() {
		return takenByStaff;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}

	public String getLevelOfCare() {
		return levelOfCare;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	public Date getLastService() {
		return lastService;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setUPId(String uPId) {
		UPId = uPId;
	}

	public String getUPId() {
		return UPId;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Referral().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Referral findReferral(String id) {
		if (id == null)
			return null;
		return entityManager().find(Referral.class, id);
	}
}
