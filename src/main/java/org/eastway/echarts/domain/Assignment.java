package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Orders")
public class Assignment implements Serializable {

	@Id
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    private EHR ehr;

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date assignmentDate;

    private String service;

    private String staff;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date orderDate;

    private String disposition;

    private String staffName;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date termDate;

    private Integer planId;

    private Integer trtEpisode;

    private String program;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date lastEdit;

    private String lastEditBy;

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

	public void setTrtEpisode(Integer trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

	public Integer getTrtEpisode() {
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

	public void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	public EHR getEhr() {
		return ehr;
	}
}
