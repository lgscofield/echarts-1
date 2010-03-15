package org.eastway.echarts.shared;

import java.sql.Date;

public interface User {
	void setStaffId(String staffId);
	String getStaffId();

	void setUserName(String userName);
	String getUserName();

	void setStaffName(String staffName);
	String getStaffName();

	void setProgram(String program);
	String getProgram();

	void setStatus(String statuf);
	String getStatus();

	void setOffice(String office);
	String getOffice();

	void setOfficePhone(String officePhone);
	String getOfficePhone();

	void setOfficeExt(String officeExt);
	String getOfficeExt();

	void setStaffDescription(String staffDescription);
	String getStaffDescription();

	void setStaffNpi(String staffNpi);
	String getStaffNpi();

	void setJobClassId(Integer jobClassId);
	Integer getJobClassId();

	void setExtendedPermissions(Integer extendedPermissions);
	Integer getExtendedPermissions();

	void setHireDate(Date hireDate);
	Date getHireDate();

	void setTermDate(Date termDate);
	Date getTermDate();
}
