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
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import org.eastway.echarts.server.EchartsEntityManagerFactory;
import org.eastway.echarts.shared.PatientProxy;

import com.google.gwt.requestfactory.shared.Version;

@Entity
@Table(name = "Orders")
public class Assignment {

	@Id
	@Column(name = "orderid")
    private Integer id;

	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "caseNumber", insertable = false, updatable = false)
	private Patient patient;

	@ManyToOne(targetEntity = Demographics.class)
	@JoinColumn(name = "caseNumber", insertable = false, updatable = false)
	private Demographics demographics;

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
    private Short trtEpisode;

    private String program;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name="lastedit")
    private Date lastEdit;

    @Column(name="lasteditby")
    private String lastEditBy;

	private String caseNumber;

	@Version
	private Integer version;

    public Assignment() { }

    public void setId(Integer id) {
    	this.id = id;
    }

    public Integer getId() {
    	return id;
    }
    
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getStaff() {
		return staff;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public Date getTermDate() {
		return termDate;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setTrtEpisode(Short trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

	public Short getTrtEpisode() {
		return trtEpisode;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return program;
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

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setPatient(PatientProxy patient) {
		this.patient = (Patient) patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setDemographics(Demographics demographics) {
		this.demographics = (Demographics) demographics;
	}

	public Demographics getDemographics() {
		return demographics;
	}

	public Integer getVersion() {
		return version;
	}

	public static Assignment findAssignment(Long id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			Assignment assignment = em.find(Assignment.class, id);
			return assignment;
		} finally {
			em.close();
		}
	}

	public static List<Assignment> findAssignmentEntriesByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			List<Assignment> assignments = em.createQuery(
					"SELECT a From AssignmentImpl a Where a.disposition = 'Open' And a.service Like 'S%' And a.caseNumber = '" + caseNumber + "' Order By a.patient.lastName ASC, a.patient.firstName ASC, a.orderDate DESC", Assignment.class)
					.getResultList();
			return assignments;
		} finally {
			em.close();
		}
	}
}
