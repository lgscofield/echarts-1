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
package org.eastway.echarts.shared;

import java.util.Date;

public interface User {
	static interface Role {
		public final static int ADMINISTRATOR		       = 1;
		public final static int PSYCHIATRIST		       = 2;
		public final static int NURSE			       = 3;
		public final static int COMMUNITY_SUPPORT_SPECIALIST   = 4;
		public final static int THERAPIST	               = 5;
		public final static int RESIDENTIAL_SUPPORT_SPECIALIST = 6;
		public final static int CLINICAL_ADMINISTRATOR	       = 7;
		public final static int HUMAN_RESOURCES		       = 8;
		public final static int ACCOUNTS_RECEIVABLE 	       = 9;
		public final static int GENERAL_ADMINISTRATOR 	       = 10;
		public final static int UNPRIVILEGED 		       = 11;
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

	void setRoleId(Integer roleId);
	Integer getRoleId();

	void setExtendedPermissions(byte[] extendedPermissions);
	byte[] getExtendedPermissions();

	void setHireDate(Date hireDate);
	Date getHireDate();

	void setTermDate(Date termDate);
	Date getTermDate();

	void setId(long id);
	long getId();
}
