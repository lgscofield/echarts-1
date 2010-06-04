package org.eastway.echarts.shared;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.Patient;

public interface EHR {
	public void setId(long id);

	public long getId();

	public void setTimeCreated(Date timeCreated);

	public Date getTimeCreated();

	public void setSubject(Patient subject);

	public Patient getSubject();

	public void setAssignments(List<Assignment> assignments);

	public List<Assignment> getAssignments();

	public void setDemographics(Demographics demographics);

	public Demographics getDemographics();

	public EHRDTO toDto();
}