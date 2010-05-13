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

import java.util.Date;

import org.eastway.echarts.shared.RoleDTO;
import org.eastway.echarts.shared.UserDTO;

public class UserImpl {
	private static String staffId;
	private static String userName;
	private static String staffName;
	private static String program;
	private static String status;
	private static String office;
	private static String officePhone;
	private static String officeExt;
	private static String staffDescription;
	private static String staffNpi;
	private static Integer roleId;
	private static byte[] extendedPermissions;
	private static Date hireDate, termDate;
	private static String sessionId;
	private static RoleDTO role;
	private static long id;

	public UserImpl(UserDTO userDto) {
		UserImpl.extendedPermissions = userDto.getExtendedPermissions();
		UserImpl.hireDate = userDto.getHireDate();
		UserImpl.id = userDto.getId();
		UserImpl.office = userDto.getOffice();
		UserImpl.officeExt = userDto.getOfficeExt();
		UserImpl.officePhone = userDto.getOfficePhone();
		UserImpl.program = userDto.getProgram();
		UserImpl.role = userDto.getRole();
		UserImpl.roleId = userDto.getRoleId();
		UserImpl.staffDescription = userDto.getStaffDescription();
		UserImpl.staffId = userDto.getStaffId();
		UserImpl.staffName = userDto.getStaffName();
		UserImpl.staffNpi = userDto.getStaffNpi();
		UserImpl.status = userDto.getStatus();
		UserImpl.termDate = userDto.getTermDate();
		UserImpl.userName = userDto.getUserName();
	}

	public static void setId(long id) {
		UserImpl.id = id;
	}

	public static long getId() {
		return UserImpl.id;
	}

	public static void setRole(RoleDTO role) {
		UserImpl.role = role;
	}

	public static RoleDTO getRole() {
		return UserImpl.role;
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

	public static void setRoleId(Integer roleId) {
		UserImpl.roleId = roleId;
	}

	public static Integer getRoleId() {
		return roleId;
	}

	public static void setExtendedPermissions(byte[] extendedPermissions) {
		UserImpl.extendedPermissions = extendedPermissions;
	}

	public static byte[] getExtendedPermissions() {
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
