package org.eastway.echarts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.RoleDTO;

@Entity
public class Role {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "Role_Id")
	private int id;
	private String roleName;
	private byte[] permission;

	public Role() { }

	public Role(int id) {
		this.id = id;
	}

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

	public RoleDTO toDto() {
		RoleDTO roleDto = new RoleDTO();
		roleDto.setId(this.getId());
		roleDto.setPermission(this.getPermission());
		roleDto.setRoleName(this.getRoleName());
		return roleDto;
	}
}
