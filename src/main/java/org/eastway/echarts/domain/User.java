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
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Configurable
@Entity
@Table(name="Echarts_User")
public class User implements java.io.Serializable {
	@Id
	@Column(name = "username")
	private String id;
	private String staffId;
	@ManyToOne
	@JoinColumn(name = "role_id")
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
	@OneToMany(orphanRemoval=true)
	@JoinColumn(referencedColumnName="staffId",name="staff")
	private Set<Assignment> assignments;
	@Version
	@Column(name = "version")
	private Integer version = 0;

	@PersistenceContext
	transient EntityManager entityManager;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String userName) {
		this.id = userName;
	}

	public String getUsername() {
		return id;
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

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		
	}

	public String getSessionId() {
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

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new User().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static User findUser(String id) {
		if (id == null)
			return null;
		List<User> results = entityManager()
				.createQuery("select o from User o where o.id = :id", User.class)
				.setParameter("id",id)
				.getResultList();
		if (results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Transactional
	public void flush() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.flush();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public User merge() {
		if (this.entityManager == null) this.entityManager = entityManager();
		User merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Version: ").append(getVersion()).append(", ");
		sb.append("UserName: ").append(getUsername()).append(", ");
		sb.append("StaffName: ").append(getStaffName()).append(", ");
		sb.append("StaffId: ").append(getStaffId()).append(", ");
		return sb.toString();
	}

	public static Boolean isSupervisor(String supervisor, String staff) {
		List<User> user = entityManager()
			.createQuery("SELECT o FROM User o where o.supervisor.staffId = :supervisor AND o.staffId = :staff", User.class)
			.setParameter("supervisor", supervisor)
			.setParameter("staff", staff)
			.getResultList();
		if (user != null && user.size() != 0)
			return true;
		return false;
	}

	public static List<User> findAssignments(String supervisor) {
		if (supervisor == null || supervisor.length() == 0)
			return null;
		return entityManager()
			.createQuery("SELECT o FROM User o WHERE o.supervisor.id = :supervisor", User.class)
			.setParameter("supervisor", supervisor)
			.getResultList();
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null) this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			User attached = User.findUser(this.id);
			this.entityManager.remove(attached);
		}
	}
}
