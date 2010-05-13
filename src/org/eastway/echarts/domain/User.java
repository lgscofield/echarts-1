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
package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.UserDTO;

@Entity
@Table(name="[User]")
public class User {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	private long id;
	private String username;
	private String staffId;
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private Role role;
	private byte[] extPerm;
	private String staffName;
	private String program;
	private Date hireDate;
	private String status;
	private Date termDate;
	private String office;
	private String officePhone;
	private String officeExt;
	private String staffDescription;
	private String staffNpi;

	public User() {	}

	public User(long id) {
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setExtPerm(byte[] extPerm) {
		this.extPerm = extPerm;
	}

	public byte[] getExtPerm() {
		return extPerm;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return program;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public Date getTermDate() {
		return termDate;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOffice() {
		return office;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficeExt(String officeExt) {
		this.officeExt = officeExt;
	}

	public String getOfficeExt() {
		return officeExt;
	}

	public void setStaffDescription(String staffDescription) {
		this.staffDescription = staffDescription;
	}

	public String getStaffDescription() {
		return staffDescription;
	}

	public void setStaffNpi(String staffNpi) {
		this.staffNpi = staffNpi;
	}

	public String getStaffNpi() {
		return staffNpi;
	}

	public UserDTO toDto() {
		UserDTO userDto = new UserDTO();
		userDto.setExtendedPermissions(this.getExtPerm());
		userDto.setHireDate(this.getHireDate());
		userDto.setId(this.getId());
		userDto.setOffice(this.getOffice());
		userDto.setOfficeExt(this.getOfficeExt());
		userDto.setOfficePhone(this.getOfficePhone());
		userDto.setProgram(this.getProgram());
		userDto.setRole(this.getRole().toDto());
		userDto.setRoleId(this.getRole().getId());
		userDto.setStaffDescription(this.getStaffDescription());
		userDto.setStaffId(this.getStaffId());
		userDto.setStaffName(this.getStaffName());
		userDto.setStaffNpi(this.getStaffNpi());
		userDto.setStatus(this.getStatus());
		userDto.setTermDate(this.getTermDate());
		userDto.setUserName(this.getUsername());
		return userDto;
	}
}
