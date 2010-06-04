package org.eastway.echarts.shared;

import java.util.Date;


public interface Patient {

	public void setEhr(EHR ehr);

	public EHR getEhr();

	public void setEhrId(long ehrId);

	public long getEhrId();

	public void setId(long id);

	public long getId();

	public String getName();

	public void setFirstName(String firstName);

	public String getFirstName();

	public void setLastName(String lastName);

	public String getLastName();

	public void setSuffix(String suffix);

	public String getSuffix();

	public void setAlias(String alias);

	public String getAlias();

	public void setCaseStatus(String caseStatus);

	public String getCaseStatus();

	public void setSsn(String ssn);

	public String getSsn();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setMiddleInitial(String middleInitial);

	public String getMiddleInitial();

	public PatientDTO toDto();
}