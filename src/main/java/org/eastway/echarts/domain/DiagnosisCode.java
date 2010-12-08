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
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "diagnosisaxis")
public class DiagnosisCode {
	@PersistenceContext
	transient EntityManager entityManager;

	@Id
	@Column(name = "icd9")
	private String id;
	private String description;
	private String dsmiv;
	@Version
	@Column(name = "version")
	private Integer version;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDsmiv(String dsmiv) {
		this.dsmiv = dsmiv;
	}

	public String getDsmiv() {
		return dsmiv;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}
}
