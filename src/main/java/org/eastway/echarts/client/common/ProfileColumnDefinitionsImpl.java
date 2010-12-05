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
package org.eastway.echarts.client.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.shared.CodeProxy;
import org.eastway.echarts.shared.UserProxy;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.inject.Inject;

@SuppressWarnings("serial")
public class ProfileColumnDefinitionsImpl extends ArrayList<ColumnDefinition<UserProxy>> {

	private Map<String, String> costCenters;

	@Inject
	public ProfileColumnDefinitionsImpl(EchartsRequestFactory requestFactory) {
		requestFactory.codeRequest().findCodeByName("CostCenter").fire(new Receiver<List<CodeProxy>>() {
			@Override
			public void onSuccess(List<CodeProxy> response) {
				setCostCenters(response);
				setColumnDefinitions();
			}
		});
	}

	private void setCostCenters(List<CodeProxy> costCenters) {
		for (CodeProxy c : costCenters)
			this.costCenters.put(c.getValue(), c.getDescriptor());
	}

	private void setColumnDefinitions() {
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getStaffName() == null ? "" : t.getStaffName());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Name";
			}

		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getUsername() == null ? "" : t.getUsername());
				} catch(NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Username";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getStaffId() == null ? "" : t.getStaffId());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "MIS Number";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getStaffDescription() == null ? "" : t.getStaffDescription());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Staff Description";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getRole().getRoleName() == null ? "" : t.getRole().getRoleName());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Role";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Program";
			}

			@Override
			public String getData(UserProxy t) {
				try {
					return t.getProgram() == null ? "" : t.getProgram();
				} catch(NullPointerException e) {
					return "";
				}
			}

			@Override
			public Map<String, String> getMap(UserProxy t) {
				return costCenters;
			}

			@Override
			public void setData(UserProxy t, String program) {
				t.setProgram(program);
			}

			@Override
			public boolean isMap() {
				return true;
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Credential 1";
			}

			@Override
			public String getData(UserProxy t) {
				try {
					return t.getCred1() == null ? "" : t.getCred1();
				} catch (NullPointerException e) {
					return "";
				}
			}

			@Override
			public void setData(UserProxy t, String cred1) {
				t.setCred1(cred1);
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Credential 2";
			}

			@Override
			public String getData(UserProxy t) {
				try {
					return t.getCred2() == null ? "" : t.getCred2();
				} catch (NullPointerException e) {
					return "";
				}
			}

			@Override
			public void setData(UserProxy t, String cred2) {
				t.setCred2(cred2);
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getOffice() == null ? "" : t.getOffice());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Office";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getOfficePhone() == null ? "" : t.getOfficePhone());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Office Phone";
			}
		});
		this.add(new ColumnDefinition<UserProxy>() {
			@Override
			public void render(UserProxy t, StringBuilder sb) {
				try {
					sb.append(t.getSupervisor() == null ? "" : t.getSupervisor());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(UserProxy t) {
				return "Supervisor";
			}
		});
	}
}
