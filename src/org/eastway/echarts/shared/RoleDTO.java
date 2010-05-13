package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RoleDTO implements Serializable {
	private int id;
	private String roleName;
	private byte[] permission;

	public RoleDTO() { }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setPermission(byte[] permission) {
		this.permission = permission;
	}

	public byte[] getPermission() {
		return permission;
	}
}
