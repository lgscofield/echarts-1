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

@SuppressWarnings("serial")
public class UserDTO implements User, Serializable {
	private String staffId;
	private String username;
	private String staffName;
	private String program;
	private String status;
	private String office;
	private String officePhone;
	private String officeExt;
	private String staffDescription;
	private String staffNpi;
	Byte[] extendedPermissions;
	private Date hireDate, termDate;
	private long id;
	private Role role;
	private String supervisor;
	private String sessionId;

	public UserDTO() { };

	@Override
	public Byte[] getExtendedPermissions() {
		return extendedPermissions;
	}

	@Override
	public Date getHireDate() {
		return hireDate;
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
	public String getUsername() {
		return username;
	}

	@Override
	public void setExtendedPermissions(Byte[] permissions) {
		this.extendedPermissions = permissions;
	}

	@Override
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
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
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String getSupervisor() {
		return supervisor;
	}

	@Override
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public UserDTO toDto() {
		return this;
	}

	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
