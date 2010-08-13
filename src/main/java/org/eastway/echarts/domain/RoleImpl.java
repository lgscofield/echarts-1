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
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.Role;
import org.eastway.echarts.shared.RoleDTO;

@Entity
@Table(name = "Role")
public class RoleImpl implements Role {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "Role_Id")
	private int id;
	private String roleName;
	private Byte[] permission;

	public RoleImpl() { }

	public RoleImpl(int id) {
		this.id = id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setPermission(Byte[] permission) {
		this.permission = permission;
	}

	@Override
	public Byte[] getPermission() {
		return permission;
	}

	@Override
	public RoleDTO toDto() {
		RoleDTO roleDto = new RoleDTO();
		roleDto.setId(this.getId());
		roleDto.setPermission(this.getPermission());
		roleDto.setRoleName(this.getRoleName());
		return roleDto;
	}
}
