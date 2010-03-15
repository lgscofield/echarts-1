package org.eastway.echarts.client;

import java.sql.Date;

import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.client.Cookies;

public class UserImpl {
	private static String staffId, userName, staffName, program, status, office,
			officePhone, officeExt, staffDescription, staffNpi;
	private static Integer jobClassId, extendedPermissions;
	private static Date hireDate, termDate;

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
		return Cookies.getCookie("sessionId");
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
