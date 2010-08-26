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

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eastway.echarts.shared.ARInfo;
import org.eastway.echarts.shared.ARInfoDTO;

@Entity
@Table(name="arinfocatt")
public class ARInfoImpl implements ARInfo {

	@Id
	private String caseNumber;
	@Column(name="billcode")
	private String billCode;
	@Column(name="arstatus")
	private String arStatus;
	private Integer income;
	private Short dependents;
	@Column(name="spenddown", columnDefinition="money")
	private BigDecimal spendDown;
	@Temporal(TemporalType.DATE)
	@Column(name="admdate")
	private Date admissionDate;
	@Column(name="medicaidid")
	private String medicaidId;
	@Temporal(TemporalType.DATE)
	@Column(name="txxappdate")
	private Date titleTwentyAppDate;
	@Temporal(TemporalType.DATE)
	@Column(name="txxredetermdate")
	private Date titleTwentyRedetermDate;
	@Column(name="txxeligibilitycategory")
	private String titleTwentyEligibilityCategory;
	private String uci;
	@Temporal(TemporalType.DATE)
	@Column(name="macregdate")
	private Date macRegDate;
	@Column(name="macregname")
	private String macRegName;

	public ARInfoImpl() { }

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	@Override
	public String getBillCode() {
		return billCode;
	}

	@Override
	public void setArStatus(String arStatus) {
		this.arStatus = arStatus;
	}

	@Override
	public String getArStatus() {
		return arStatus;
	}

	@Override
	public void setIncome(Integer income) {
		this.income = income;
	}

	@Override
	public Integer getIncome() {
		return income;
	}

	@Override
	public void setDependents(Short dependents) {
		this.dependents = dependents;
	}

	@Override
	public Short getDependents() {
		return dependents;
	}

	@Override
	public void setSpendDown(BigDecimal spendDown) {
		this.spendDown = spendDown;
	}

	@Override
	public BigDecimal getSpendDown() {
		return spendDown;
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
	public void setMedicaidId(String medicaidId) {
		this.medicaidId = medicaidId;
	}

	@Override
	public String getMedicaidId() {
		return medicaidId;
	}

	@Override
	public void setTitleTwentyAppDate(Date titleTwentyAppDate) {
		this.titleTwentyAppDate = titleTwentyAppDate;
	}

	@Override
	public Date getTitleTwentyAppDate() {
		return titleTwentyAppDate;
	}

	@Override
	public void setTitleTwentyRedetermDate(Date titleTwentyRedetermDate) {
		this.titleTwentyRedetermDate = titleTwentyRedetermDate;
	}

	@Override
	public Date getTitleTwentyRedetermDate() {
		return titleTwentyRedetermDate;
	}

	@Override
	public void setTitleTwentyEligibilityCategory(
			String titleTwentyEligibilityCategory) {
		this.titleTwentyEligibilityCategory = titleTwentyEligibilityCategory;
	}

	@Override
	public String getTitleTwentyEligibilityCategory() {
		return titleTwentyEligibilityCategory;
	}

	@Override
	public void setUci(String uci) {
		this.uci = uci;
	}

	@Override
	public String getUci() {
		return uci;
	}

	@Override
	public void setMacRegDate(Date macRegDate) {
		this.macRegDate = macRegDate;
	}

	@Override
	public Date getMacRegDate() {
		return macRegDate;
	}

	@Override
	public void setMacRegName(String macRegName) {
		this.macRegName = macRegName;
	}

	@Override
	public String getMacRegName() {
		return macRegName;
	}

	@Override
	public ARInfoDTO toDto() {
		ARInfoDTO dto = new ARInfoDTO();
		dto.setAdmissionDate(admissionDate);
		dto.setArStatus(arStatus);
		dto.setBillCode(billCode);
		dto.setCaseNumber(caseNumber);
		dto.setDependents(dependents);
		dto.setIncome(income);
		dto.setMacRegDate(macRegDate);
		dto.setMacRegName(macRegName);
		dto.setMedicaidId(medicaidId);
		dto.setSpendDown(spendDown);
		dto.setTitleTwentyAppDate(titleTwentyAppDate);
		dto.setTitleTwentyEligibilityCategory(titleTwentyEligibilityCategory);
		dto.setTitleTwentyRedetermDate(titleTwentyRedetermDate);
		dto.setUci(uci);
		return dto;
	}
}
