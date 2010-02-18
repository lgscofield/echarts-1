package org.eastway.echarts.shared;

import java.sql.Date;

public class User {
	private static String staffId, userName, staffName, program, status, office,
			officePhone, officeExt, staffDescription, staffNpi,
			sessionId;
	private static Integer jobClassId, extendedPermissions;
	private static Date hireDate, termDate;

	public User() { }

	public User(String staffId, String userName, String staffName,
			String program, String status, String office,
			String officePhone, String officeExt,
			String staffDescription, String staffNpi,
			String sessionId, Integer jobClassId,
			Integer extendedPermissions, Date hireDate,
			Date termDate) {
		User.setStaffId(staffId);
		User.setUserName(userName);
		User.setStaffName(staffName);
		User.setProgram(program);
		User.setStatus(status);
		User.setOffice(office);
		User.setOfficePhone(officePhone);
		User.setOfficeExt(officeExt);
		User.setStaffDescription(staffDescription);
		User.setStaffNpi(staffNpi);
		User.setSessionId(sessionId);
		User.setJobClassId(jobClassId);
		User.setExtendedPermissions(extendedPermissions);
		User.setHireDate(hireDate);
		User.setTermDate(termDate);
	}

	public static void setStaffId(String staffId) {
		User.staffId = staffId;
	}

	public static String getStaffId() {
		return staffId;
	}

	public static void setUserName(String userName) {
		User.userName = userName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setStaffName(String staffName) {
		User.staffName = staffName;
	}

	public static String getStaffName() {
		return staffName;
	}

	public static void setProgram(String program) {
		User.program = program;
	}

	public static String getProgram() {
		return program;
	}

	public static void setStatus(String status) {
		User.status = status;
	}

	public static String getStatus() {
		return status;
	}

	public static void setOffice(String office) {
		User.office = office;
	}

	public static String getOffice() {
		return office;
	}

	public static void setOfficePhone(String officePhone) {
		User.officePhone = officePhone;
	}

	public static String getOfficePhone() {
		return officePhone;
	}

	public static void setOfficeExt(String officeExt) {
		User.officeExt = officeExt;
	}

	public static String getOfficeExt() {
		return officeExt;
	}

	public static void setStaffDescription(String staffDescription) {
		User.staffDescription = staffDescription;
	}

	public static String getStaffDescription() {
		return staffDescription;
	}

	public static void setStaffNpi(String staffNpi) {
		User.staffNpi = staffNpi;
	}

	public static String getStaffNpi() {
		return staffNpi;
	}

	public static void setSessionId(String sessionId) {
		User.sessionId = sessionId;
	}

	public static String getSessionId() {
		return sessionId;
	}

	public static void setJobClassId(Integer jobClassId) {
		User.jobClassId = jobClassId;
	}

	public static Integer getJobClassId() {
		return jobClassId;
	}

	public static void setExtendedPermissions(Integer extendedPermissions) {
		User.extendedPermissions = extendedPermissions;
	}

	public static Integer getExtendedPermissions() {
		return extendedPermissions;
	}

	public static void setHireDate(Date hireDate) {
		User.hireDate = hireDate;
	}

	public static Date getHireDate() {
		return hireDate;
	}

	public static void setTermDate(Date termDate) {
		User.termDate = termDate;
	}

	public static Date getTermDate() {
		return termDate;
	}
}
