package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DbServerConfigDTO implements DbServerConfig, Serializable {

	private String name;
	private String value;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public DbServerConfigDTO toDto() {
		return this;
	}

}
