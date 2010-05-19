package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CodeDTO implements Serializable {
	private long codeId;
	private String columnName;
	private String value;
	private String descriptor;

	public CodeDTO() { }

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
}
