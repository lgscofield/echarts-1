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

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eastway.echarts.shared.Role;
import org.eastway.echarts.shared.User;
import org.eastway.echarts.shared.UserDTO;

@Entity
@Table(name="Echarts_User")
public class UserImpl implements User {
	@ManyToMany
	@JoinTable(name="user_session_map",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="session_id"))
	private Collection<SessionIdLog> sessionIds;
	@Id
	private String username;
	private String staffId;
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private RoleImpl role;
	private Byte[] extendedPermissions;
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
	@ManyToOne
	private UserImpl supervisor;
	private String cred1;
	private String cred2;

	public UserImpl() {	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	@Override
	public void setRole(Role role) {
		this.role = (RoleImpl) role;
	}

	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Override
	public String getStaffName() {
		return staffName;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public String getProgram() {
		return program;
	}

	@Override
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public Date getHireDate() {
		return hireDate;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Override
	public Date getTermDate() {
		return termDate;
	}

	@Override
	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public String getOffice() {
		return office;
	}

	@Override
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Override
	public String getOfficePhone() {
		return officePhone;
	}

	@Override
	public void setOfficeExt(String officeExt) {
		this.officeExt = officeExt;
	}

	@Override
	public String getOfficeExt() {
		return officeExt;
	}

	@Override
	public void setStaffDescription(String staffDescription) {
		this.staffDescription = staffDescription;
	}

	@Override
	public String getStaffDescription() {
		return staffDescription;
	}

	@Override
	public void setStaffNpi(String staffNpi) {
		this.staffNpi = staffNpi;
	}

	@Override
	public String getStaffNpi() {
		return staffNpi;
	}

	@Override
	public UserDTO toDto() {
		UserDTO userDto = new UserDTO();
		userDto.setExtendedPermissions(this.getExtendedPermissions());
		userDto.setHireDate(this.getHireDate());
		userDto.setOffice(this.getOffice());
		userDto.setOfficeExt(this.getOfficeExt());
		userDto.setOfficePhone(this.getOfficePhone());
		userDto.setProgram(this.getProgram());
		userDto.setRole(role.toDto());
		userDto.setStaffDescription(this.getStaffDescription());
		userDto.setStaffId(this.getStaffId());
		userDto.setStaffName(this.getStaffName());
		userDto.setStaffNpi(this.getStaffNpi());
		userDto.setStatus(this.getStatus());
		userDto.setSupervisor(supervisor.getStaffName());
		userDto.setTermDate(this.getTermDate());
		userDto.setUsername(this.getUsername());
		userDto.setCred1(cred1);
		userDto.setCred2(cred2);
		return userDto;
	}

	@Override
	public Byte[] getExtendedPermissions() {
		return extendedPermissions;
	}

	@Override
	public String getSupervisor() {
		return supervisor.getStaffName();
	}

	@Override
	public void setExtendedPermissions(Byte[] extendedPermissions) {
		this.extendedPermissions = extendedPermissions;
	}

	@Override
	public void setSupervisor(String supervisor) {
		
	}

	@Override
	public String getSessionId() {
		for (SessionIdLog sessionId : sessionIds)
			return sessionId.getSessionId();
		return null;
	}

	@Override
	public void setSessionId(String sessionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCred1(String cred1) {
		this.cred1 = cred1;
	}

	@Override
	public String getCred1() {
		return cred1;
	}

	@Override
	public void setCred2(String cred2) {
		this.cred2 = cred2;
	}

	@Override
	public String getCred2() {
		return cred2;
	}
}
