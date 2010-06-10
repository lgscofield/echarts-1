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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.PatientDTO;

@Entity
@Table(name = "Patient")
public class PatientImpl implements Patient {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "Patient_Id")
	private long id;

	@OneToOne(mappedBy = "subject")
	private EHRImpl ehr;

	@Column(name = "ehr_id")
	private long ehrId;
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	@ManyToOne
	private CodeImpl caseStatus;
	private String ssn;
	private String lastEditBy;
	@NotNull
	private Date lastEdit;

	public PatientImpl() {}

	public PatientImpl(long id) {
		this.setId(id);
	}

	@Override
	public void setEhr(EHR ehr) {
		this.ehr = (EHRImpl) ehr;
	}

	@Override
	public EHR getEhr() {
		return ehr;
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
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
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
	public void setCaseStatus(Code caseStatus) {
		this.caseStatus = (CodeImpl) caseStatus;
	}

	@Override
	public Code getCaseStatus() {
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
	public PatientDTO toDto() {
		PatientDTO patientDto = new PatientDTO();
		patientDto.setAlias(this.getAlias());
		patientDto.setCaseNumber(this.getCaseNumber());
		patientDto.setCaseStatus(this.getCaseStatus().toDto());
		patientDto.setFirstName(this.getFirstName());
		patientDto.setLastEdit(this.getLastEdit());
		patientDto.setLastEditBy(this.getLastEditBy());
		patientDto.setLastName(this.getLastName());
		patientDto.setMiddleInitial(this.getMiddleInitial());
		patientDto.setId(this.getId());
		patientDto.setEhrId(this.getEhrId());
		patientDto.setSsn(this.getSsn());
		patientDto.setSuffix(this.getSuffix());
		return patientDto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ")
			.append("Ehr Id: ").append(getEhrId()).append(", ")
			.append("CaseNumber: ").append(getCaseNumber()).append(", ")
			.append("FirstName: ").append(getFirstName()).append(", ")
			.append("LastName: ").append(getLastName()).append(", ")
			.append("MiddleInitial: ").append(getMiddleInitial()).append(", ")
			.append("Suffix: ").append(getSuffix()).append(", ")
			.append("Alias: ").append(getAlias()).append(", ")
			.append("CaseStatus: ").append(getCaseStatus()).append(", ")
			.append("SSN: ").append(getSsn()).append(", ")
			.append("LastEditBy: ").append(getLastEditBy()).append(", ")
			.append("LastEdit: ").append(getLastEdit());
		return sb.toString();
	}
}
