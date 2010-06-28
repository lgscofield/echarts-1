package org.eastway.echarts.shared;


public interface Role {

	public void setId(int id);

	public int getId();

	public void setRoleName(String roleName);

	public String getRoleName();

	public void setPermission(byte[] permission);

	public byte[] getPermission();

	public RoleDTO toDto();

}