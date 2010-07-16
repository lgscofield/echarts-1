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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.AssignmentDTO;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.Patient;

@SuppressWarnings("serial")
@Entity
@Table(name = "Orders")
public class AssignmentImpl implements Serializable, Assignment {

	@Id
	@Column(name = "orderid")
    private Integer id;

	@ManyToOne(targetEntity = PatientImpl.class)
	@JoinColumn(name = "Case#", insertable = false, updatable = false)
	private PatientImpl patient;

	@ManyToOne(targetEntity = DemographicsImpl.class)
	@JoinColumn(name = "Case#", insertable = false, updatable = false)
	private DemographicsImpl demographics;

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date assignmentDate;

    private String service;

    private String staff;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name="orderdate")
    private Date orderDate;

    private String disposition;

    @Column(name="staffname")
    private String staffName;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name="termdate")
    private Date termDate;

    @Null
    @Column(name="planid")
    private Integer planId;

    @Null
    @Column(name="trtepisode")
    private short trtEpisode;

    private String program;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name="lastedit")
    private Date lastEdit;

    @Column(name="lasteditby")
    private String lastEditBy;

    @Column(name="Case#")
	private String caseNumber;

    public AssignmentImpl() { }

    @Override
    public void setId(Integer id) {
    	this.id = id;
    }

    @Override
    public Integer getId() {
    	return id;
    }
    
    @Override
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

    @Override
	public Date getAssignmentDate() {
		return assignmentDate;
	}

    @Override
	public void setService(String service) {
		this.service = service;
	}

    @Override
	public String getService() {
		return service;
	}

    @Override
	public void setStaff(String staff) {
		this.staff = staff;
	}

    @Override
	public String getStaff() {
		return staff;
	}

    @Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

    @Override
	public Date getOrderDate() {
		return orderDate;
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
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    @Override
	public String getStaffName() {
		return staffName;
	}

    @Override
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

    @Override
	public Date getTermDate() {
		return termDate;
	}

    @Override
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

    @Override
	public Integer getPlanId() {
		return planId;
	}

    @Override
	public void setTrtEpisode(short trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

    @Override
	public short getTrtEpisode() {
		return trtEpisode;
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
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setPatient(Patient patient) {
		this.patient = (PatientImpl) patient;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public void setDemographics(Demographics demographics) {
		this.demographics = (DemographicsImpl) demographics;
	}

	@Override
	public Demographics getDemographics() {
		return demographics;
	}

	@Override
	public AssignmentDTO toDto() {
		AssignmentDTO dto = new AssignmentDTO();
		dto.setId(this.id);
		dto.setCaseNumber(caseNumber);
		dto.setAssignmentDate(this.assignmentDate);
		dto.setService(this.service);
		dto.setStaff(this.staff);
		dto.setOrderDate(this.orderDate);
		dto.setDisposition(this.disposition);
		dto.setStaffName(this.staffName);
		dto.setTermDate(this.termDate);
		dto.setPlanId(this.planId == null ? 0 : this.planId);
		dto.setTrtEpisode(this.trtEpisode);
		dto.setPatient(patient.toDto());
		dto.setDemographics(demographics.toDto());
		dto.setProgram(this.program);
		dto.setLastEdit(this.lastEdit);
		dto.setLastEditBy(this.lastEditBy);
		return dto;
	}
}
