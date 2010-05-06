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
public class PatientDTO implements Serializable {
	private long id;
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
	private DemographicsDTO demographics;

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
			DemographicsDTO demographics) {
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
		setDemographics(demographics);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
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

	public void setDemographics(DemographicsDTO demographics) {
		this.demographics = demographics;
	}

	public DemographicsDTO getDemographics() {
		return demographics;
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
