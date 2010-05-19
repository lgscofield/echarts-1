package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.CodeDTO;

@Entity
@Table(name="Codes")
public class Code {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codeId;
	private String columnName;
	private String value;
	private String descriptor;

	public Code() { }

	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}

	public long getCodeId() {
		return codeId;
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

	public CodeDTO toDto() {
		CodeDTO dto = new CodeDTO();
		dto.setCodeId(this.getCodeId());
		dto.setColumnName(this.getColumnName());
		dto.setDescriptor(this.getDescriptor());
		dto.setValue(this.getValue());
		return dto;
	}
}
