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
import javax.persistence.Id;
import javax.persistence.Version;

import org.eastway.echarts.server.EchartsEntityManagerFactory;

@Entity
public class DbServerConfig {

	@Id
	private String name;
	private String value;
	@Version
	@Column(name = "version")
	private Integer version;


	public void setId(String id) {
		this.name = id;
	}

	public String getId() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		return EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
	}

	public static DbServerConfig findDbServerConfig(String id) {
		if (id == null)
			return null;
		EntityManager em = entityManager();
		try {
			DbServerConfig dsc = em.find(DbServerConfig.class, id);
			return dsc;
		} finally {
			em.close();
		}
	}
}
