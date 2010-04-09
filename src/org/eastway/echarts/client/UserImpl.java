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
package org.eastway.echarts.client;

import java.sql.Date;

import org.eastway.echarts.shared.UserData;

public class UserImpl {
	private static String staffId, userName, staffName, program, status, office,
			officePhone, officeExt, staffDescription, staffNpi;
	private static Integer jobClassId, extendedPermissions;
	private static Date hireDate, termDate;
	private static String sessionId;

	public UserImpl(UserData userData) {
		UserImpl.setStaffId(userData.getStaffId());
		UserImpl.setUserName(userData.getUserName());
		UserImpl.setStaffName(userData.getStaffName());
		UserImpl.setProgram(userData.getProgram());
		UserImpl.setStatus(userData.getStatus());
		UserImpl.setOffice(userData.getOffice());
		UserImpl.setOfficePhone(userData.getOfficePhone());
		UserImpl.setOfficeExt(userData.getOfficeExt());
		UserImpl.setStaffDescription(userData.getStaffDescription());
		UserImpl.setStaffNpi(userData.getStaffNpi());
		UserImpl.setJobClassId(userData.getJobClassId());
		UserImpl.setExtendedPermissions(userData.getExtendedPermissions());
		UserImpl.setHireDate(userData.getHireDate());
		UserImpl.setTermDate(userData.getTermDate());
	}

	public static void setStaffId(String staffId) {
		UserImpl.staffId = staffId;
	}

	public static String getStaffId() {
		return staffId;
	}

	public static void setUserName(String userName) {
		UserImpl.userName = userName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setStaffName(String staffName) {
		UserImpl.staffName = staffName;
	}

	public static String getStaffName() {
		return staffName;
	}

	public static void setProgram(String program) {
		UserImpl.program = program;
	}

	public static String getProgram() {
		return program;
	}

	public static void setStatus(String status) {
		UserImpl.status = status;
	}

	public static String getStatus() {
		return status;
	}

	public static void setOffice(String office) {
		UserImpl.office = office;
	}

	public static String getOffice() {
		return office;
	}

	public static void setOfficePhone(String officePhone) {
		UserImpl.officePhone = officePhone;
	}

	public static String getOfficePhone() {
		return officePhone;
	}

	public static void setOfficeExt(String officeExt) {
		UserImpl.officeExt = officeExt;
	}

	public static String getOfficeExt() {
		return officeExt;
	}

	public static void setStaffDescription(String staffDescription) {
		UserImpl.staffDescription = staffDescription;
	}

	public static String getStaffDescription() {
		return staffDescription;
	}

	public static void setStaffNpi(String staffNpi) {
		UserImpl.staffNpi = staffNpi;
	}

	public static String getStaffNpi() {
		return staffNpi;
	}

	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		UserImpl.sessionId = sessionId;
	}

	public static void setJobClassId(Integer jobClassId) {
		UserImpl.jobClassId = jobClassId;
	}

	public static Integer getJobClassId() {
		return jobClassId;
	}

	public static void setExtendedPermissions(Integer extendedPermissions) {
		UserImpl.extendedPermissions = extendedPermissions;
	}

	public static Integer getExtendedPermissions() {
		return extendedPermissions;
	}

	public static void setHireDate(Date hireDate) {
		UserImpl.hireDate = hireDate;
	}

	public static Date getHireDate() {
		return hireDate;
	}

	public static void setTermDate(Date termDate) {
		UserImpl.termDate = termDate;
	}

	public static Date getTermDate() {
		return termDate;
	}
}
