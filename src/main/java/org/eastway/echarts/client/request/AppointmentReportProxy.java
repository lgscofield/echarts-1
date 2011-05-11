package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.AppointmentReport;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(AppointmentReport.class)
public interface AppointmentReportProxy extends EntityProxy {
	public void setId(Long id);

	public Long getId();

	public void setFullName(String fullName);

	public String getFullName();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setApptDate(Date apptDate);

	public Date getApptDate();

	public void setStartTime(Date startTime);

	public Date getStartTime();

	public void setEndTime(Date endTime);

	public Date getEndTime();

	public void setActivity(String activity);

	public String getActivity();

	public void setStaffName(String staffName);

	public String getStaffName();

	public void setNotes(String notes);

	public String getNotes();

	public void setVersion(Integer version);

	public Integer getVersion();

	public EntityProxyId<AppointmentReportProxy> stableId();
}
