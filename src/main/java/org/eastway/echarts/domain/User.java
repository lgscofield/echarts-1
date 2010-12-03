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
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eastway.echarts.server.EchartsEntityManagerFactory;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.gwt.requestfactory.shared.Version;

@Entity
@Table(name="Echarts_User")
public class User {
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
	private Role role;
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
	@NotFound(action=NotFoundAction.IGNORE)
	private User supervisor;
	private String cred1;
	private String cred2;
	@Version
	private Integer version;

	public String getId() {
		return username;
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

	public String getSupervisor() {
		return supervisor.getStaffName();
	}

	public void setSupervisor(String supervisor) {
		
	}

	public String getSessionId() {
//		for (SessionIdLog sessionId : sessionIds)
//			return sessionId.getSessionId();
		return null;
	}

	public void setSessionId(String sessionId) {
		// TODO Auto-generated method stub
		
	}

	public void setCred1(String cred1) {
		this.cred1 = cred1;
	}

	public String getCred1() {
		return cred1;
	}

	public void setCred2(String cred2) {
		this.cred2 = cred2;
	}

	public String getCred2() {
		return cred2;
	}

	public Integer getVersion() {
		return version;
	}

	public static User findUser(String id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	public void persist() {
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(this);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
}
