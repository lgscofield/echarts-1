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
public class AssignmentDTO implements Serializable, Assignment {
	private Integer id;
	private Date assignmentDate;
	private String service;
	private String staff;
	private Date orderDate;
	private String disposition;
	private String staffName;
	private Date termDate;
	private int planId;
	private short trtEpisode;
	private String program;
	private Date lastEdit;
	private String lastEditBy;
	private String caseNumber;
	private Patient patient;
	private Demographics demographics;

	public AssignmentDTO() { }

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	@Override
	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	@Override
	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String getService() {
		return this.service;
	}

	@Override
	public void setStaff(String staff) {
		this.staff = staff;
	}

	@Override
	public String getStaff() {
		return this.staff;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public Date getOrderDate() {
		return this.orderDate;
	}

	@Override
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	@Override
	public String getDisposition() {
		return this.disposition;
	}

	@Override
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Override
	public String getStaffName() {
		return this.staffName;
	}

	@Override
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Override
	public Date getTermDate() {
		return this.termDate;
	}

	@Override
	public Integer getPlanId() {
		return this.planId;
	}

	@Override
	public short getTrtEpisode() {
		return this.trtEpisode;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public String getProgram() {
		return this.program;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public Date getLastEdit() {
		return this.lastEdit;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public String getLastEditBy() {
		return this.lastEditBy;
	}

	@Override
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	@Override
	public void setTrtEpisode(short trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

	@Override
	public AssignmentDTO toDto() {
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

	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	@Override
	public Demographics getDemographics() {
		return demographics;
	}
}
