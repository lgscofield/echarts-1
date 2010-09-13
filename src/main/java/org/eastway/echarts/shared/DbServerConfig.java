package org.eastway.echarts.shared;


public interface DbServerConfig {

	public void setName(String name);

	public String getName();

	public void setValue(String value);

	public String getValue();

	public DbServerConfigDTO toDto();

}