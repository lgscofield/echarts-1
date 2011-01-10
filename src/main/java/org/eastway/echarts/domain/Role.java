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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Role {
	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "role_id")
	private Long id;
	private String roleName;
	@Version
	@Column(name = "version")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Role().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Role findRole(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Role.class, id);
	}
}
