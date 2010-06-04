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
public class PatientDTO implements Serializable, Patient {
	private long id;
	private long ehrId;
	private EHR ehr;
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

	public PatientDTO() { }

	public PatientDTO(String alias,
			String caseNumber,
			String caseStatus,
			String firstName,
			Date lastEdit,
			String lastEditBy,
			String lastName,
			String middleInitial,
			long id,
			long ehrId,
			String ssn,
			String suffix,
			Demographics demographics) {
		setAlias(alias);
		setCaseNumber(caseNumber);
		setCaseStatus(caseStatus);
		setFirstName(firstName);
		setLastEdit(lastEdit);
		setLastEditBy(lastEditBy);
		setLastName(lastName);
		setMiddleInitial(middleInitial);
		setId(id);
		setEhrId(ehrId);
		setSsn(ssn);
		setSuffix(suffix);
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	@Override
	public long getEhrId() {
		return ehrId;
	}

	@Override
	public String getName() {
		return getLastName() + ", " + getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	@Override
	public String getCaseStatus() {
		return caseStatus;
	}

	@Override
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String getSsn() {
		return ssn;
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
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public String getMiddleInitial() {
		return middleInitial;
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	@Override
	public PatientDTO toDto() {
		return this;
	}
}
