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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class Patient {
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	@Column(name="Patient_Id")
	private long id;

	@OneToOne(mappedBy="subject")
	private EHR ehr;

	@Column(name="ehr_id")
	private long ehrId;
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	private String caseStatus;
	private String ssn;
	private String lastEditBy;
	private Date lastEdit;

	public Patient() { }

	public Patient(long id) {
		this.setId(id);
	}

	public void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	public EHR getEhr() {
		return ehr;
	}

	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return getLastName() + ", " + getFirstName();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}
}
