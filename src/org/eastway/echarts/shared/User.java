package org.eastway.echarts.shared;

import java.sql.Date;

public interface User {

	interface Role {
		public final static int ADMINISTRATOR		       = 1;
		public final static int PSYCHIATRIST		       = 2;
		public final static int NURSE			       = 3;
		public final static int COMMUNITY_SUPPORT_SPECIALIST   = 5;
		public final static int THERAPIST	               = 6;
		public final static int RESIDENTIAL_SUPPORT_SPECIALIST = 7;
		public final static int CLINICAL_ADMINISTRATOR	       = 8;
		public final static int HUMAN_RESOURCES		       = 9;
		public final static int ACCOUNTS_RECEIVABLE 	       = 10;
		public final static int GENERAL_ADMINISTRATOR 	       = 11;
		public final static int UNPRIVILEGED 		       = 12;
	}

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
