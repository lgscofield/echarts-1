package org.eastway.echarts.shared;

import java.util.Date;


public interface Assignment {

	public void setId(Integer id);

	public Integer getId();

	public void setAssignmentDate(Date assignmentDate);

	public Date getAssignmentDate();

	public void setService(String service);

	public String getService();

	public void setStaff(String staff);

	public String getStaff();

	public void setOrderDate(Date orderDate);

	public Date getOrderDate();

	public void setDisposition(String disposition);

	public String getDisposition();

	public void setStaffName(String staffName);

	public String getStaffName();

	public void setTermDate(Date termDate);

	public Date getTermDate();

	public void setPlanId(Integer planId);

	public Integer getPlanId();

	public void setTrtEpisode(Integer trtEpisode);

	public Integer getTrtEpisode();

	public void setProgram(String program);

	public String getProgram();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setEhr(EHR ehr);

	public EHR getEhr();

	public AssignmentDTO toDto();
}