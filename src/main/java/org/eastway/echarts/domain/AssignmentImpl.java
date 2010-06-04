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

import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.AssignmentDTO;
import org.eastway.echarts.shared.EHR;

@SuppressWarnings("serial")
@Entity
@Table(name = "Orders")
public class AssignmentImpl implements Serializable, Assignment {

	@Id
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    private EHRImpl ehr;

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
	public void setTrtEpisode(Integer trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

    @Override
	public Integer getTrtEpisode() {
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
	public void setEhr(EHR ehr) {
		this.ehr = (EHRImpl) ehr;
	}

    @Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public AssignmentDTO toDto() {
		AssignmentDTO dto = new AssignmentDTO();
		dto.setId(this.id);
		//dto.setEhr(this.ehr.toDto());
		dto.setAssignmentDate(this.assignmentDate);
		dto.setService(this.service);
		dto.setStaff(this.staff);
		dto.setOrderDate(this.orderDate);
		dto.setDisposition(this.disposition);
		dto.setStaffName(this.staffName);
		dto.setTermDate(this.termDate);
		dto.setPlanId(this.planId == null ? 0 : this.planId);
		dto.setTrtEpisode(this.trtEpisode == null ? 0 : this.trtEpisode);
		dto.setProgram(this.program);
		dto.setLastEdit(this.lastEdit);
		dto.setLastEditBy(this.lastEditBy);
		return dto;
	}
}
