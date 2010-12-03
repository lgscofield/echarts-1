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

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.server.EchartsEntityManagerFactory;

import com.google.gwt.requestfactory.shared.Version;

@Entity
@Table(name = "Codes")
public class Code {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "code_id")
	private Long id;
	private String columnName;
	private String value;
	private String descriptor;
	@Version
	@Column(name = "version")
	private Integer version;

	public Code() { }

	public void setId(Long codeId) {
		this.id = codeId;
	}

	public Long getId() {
		return id;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static Code findCode(Long id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			Code code = em.find(Code.class, id);
			return code;
		} finally {
			em.close();
		}
	}

	public static List<Code> findCodeByName(String name) {
		if (name == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			List<Code> codes = em.createQuery(
					"SELECT c FROM Code c WHERE c.columnName = :name", Code.class)
					.setParameter("name", name)
					.getResultList();
			return codes;
		} finally {
			em.close();
		}
	}
}
