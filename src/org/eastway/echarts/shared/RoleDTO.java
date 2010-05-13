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
