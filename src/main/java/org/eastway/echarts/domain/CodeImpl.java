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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.CodeDTO;

import com.google.gwt.requestfactory.shared.Version;

@Entity
@Table(name="Codes")
public class CodeImpl implements Code {
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

	public CodeImpl() { }

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

	public CodeDTO toDto() {
		CodeDTO dto = new CodeDTO();
		dto.setId(this.getId());
		dto.setColumnName(this.getColumnName());
		dto.setDescriptor(this.getDescriptor());
		dto.setValue(this.getValue());
		return dto;
	}
}
