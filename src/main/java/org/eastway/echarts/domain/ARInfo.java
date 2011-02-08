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
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name="arinfocatt")
public class ARInfo {

	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	@Column(name = "case_number")
	private String id;
	@Column(name="billcode")
	private String billCode;
	@Column(name="arstatus")
	private String arStatus;
	private Integer income;
	private Short dependents;
	@Column(name="spenddown", columnDefinition="money")
	private BigDecimal spendDown;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="admdate")
	private Date admissionDate;
	@Column(name="medicaidid")
	private String medicaidId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="txxappdate")
	private Date titleTwentyAppDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="txxredetermdate")
	private Date titleTwentyRedetermDate;
	@Column(name="txxeligibilitycategory")
	private String titleTwentyEligibilityCategory;
	private String uci;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="macregdate")
	private Date macRegDate;
	@Column(name="macregname")
	private String macRegName;
	@Version
	@Column(name = "version")
	private Integer version;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setArStatus(String arStatus) {
		this.arStatus = arStatus;
	}

	public String getArStatus() {
		return arStatus;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getIncome() {
		return income;
	}

	public void setDependents(Short dependents) {
		this.dependents = dependents;
	}

	public Short getDependents() {
		return dependents;
	}

	public void setSpendDown(BigDecimal spendDown) {
		this.spendDown = spendDown;
	}

	public BigDecimal getSpendDown() {
		return spendDown;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setMedicaidId(String medicaidId) {
		this.medicaidId = medicaidId;
	}

	public String getMedicaidId() {
		return medicaidId;
	}

	public void setTitleTwentyAppDate(Date titleTwentyAppDate) {
		this.titleTwentyAppDate = titleTwentyAppDate;
	}

	public Date getTitleTwentyAppDate() {
		return titleTwentyAppDate;
	}

	public void setTitleTwentyRedetermDate(Date titleTwentyRedetermDate) {
		this.titleTwentyRedetermDate = titleTwentyRedetermDate;
	}

	public Date getTitleTwentyRedetermDate() {
		return titleTwentyRedetermDate;
	}

	public void setTitleTwentyEligibilityCategory(
			String titleTwentyEligibilityCategory) {
		this.titleTwentyEligibilityCategory = titleTwentyEligibilityCategory;
	}

	public String getTitleTwentyEligibilityCategory() {
		return titleTwentyEligibilityCategory;
	}

	public void setUci(String uci) {
		this.uci = uci;
	}

	public String getUci() {
		return uci;
	}

	public void setMacRegDate(Date macRegDate) {
		this.macRegDate = macRegDate;
	}

	public Date getMacRegDate() {
		return macRegDate;
	}

	public void setMacRegName(String macRegName) {
		this.macRegName = macRegName;
	}

	public String getMacRegName() {
		return macRegName;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new ARInfo().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static ARInfo findARInfo(String id) {
		if(id == null)
			return null;
		return entityManager().find(ARInfo.class, id);
	}
}
