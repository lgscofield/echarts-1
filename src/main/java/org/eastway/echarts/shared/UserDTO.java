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

import java.io.Serializable;
import java.util.Date;

import org.eastway.echarts.client.UserImpl;

@SuppressWarnings("serial")
public class UserDTO implements User, Serializable {
	private String staffId;
	private String userName;
	private String staffName;
	private String program;
	private String status;
	private String office;
	private String officePhone;
	private String officeExt;
	private String staffDescription;
	private String staffNpi;
	private Integer roleId;
	byte[] extendedPermissions;
	private Date hireDate, termDate;
	private long id;
	private RoleDTO role;

	public void initUser() {
		new UserImpl(this);
	}

	public UserDTO() { };

	public UserDTO(String staffId, String userName, String staffName,
			String program, String status, String office,
			String officePhone, String officeExt,
			String staffDescription, String staffNpi,
			Integer roleId, byte[] extendedPermissions,
			Date hireDate, Date termDate) {
		this.setStaffId(staffId);
		this.setUserName(userName);
		this.setStaffName(staffName);
		this.setProgram(program);
		this.setStatus(status);
		this.setOffice(office);
		this.setOfficePhone(officePhone);
		this.setOfficeExt(officeExt);
		this.setStaffDescription(staffDescription);
		this.setStaffNpi(staffNpi);
		this.setRoleId(roleId);
		this.setExtendedPermissions(extendedPermissions);
		this.setHireDate(hireDate);
		this.setTermDate(termDate);
	}

	@Override
	public byte[] getExtendedPermissions() {
		return extendedPermissions;
	}

	@Override
	public Date getHireDate() {
		return hireDate;
	}

	@Override
	public Integer getRoleId() {
		return roleId;
	}

	@Override
	public String getOffice() {
		return office;
	}

	@Override
	public String getOfficeExt() {
		return officeExt;
	}

	@Override
	public String getOfficePhone() {
		return officePhone;
	}

	@Override
	public String getProgram() {
		return program;
	}

	@Override
	public String getStaffDescription() {
		return staffDescription;
	}

	@Override
	public String getStaffId() {
		return staffId;
	}

	@Override
	public String getStaffName() {
		return staffName;
	}

	@Override
	public String getStaffNpi() {
		return staffNpi;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public Date getTermDate() {
		return termDate;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setExtendedPermissions(byte[] permissions) {
		this.extendedPermissions = permissions;
	}

	@Override
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public void setOfficeExt(String officeExt) {
		this.officeExt = officeExt;
	}

	@Override
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public void setStaffDescription(String staffDescription) {
		this.staffDescription = staffDescription;
	}

	@Override
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Override
	public void setStaffNpi(String staffNpi) {
		this.staffNpi = staffNpi;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public RoleDTO getRole() {
		return role;
	}
}
