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
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.shared.GetProfileViewData;
import org.eastway.echarts.shared.GetProfileViewDataResult;
import org.eastway.echarts.shared.User;

import com.google.inject.Inject;

@SuppressWarnings("serial")
public class ProfileColumnDefinitionsImpl extends ArrayList<ColumnDefinition<User>> {

	private Map<String, String> costCenters;

	@Inject
	public ProfileColumnDefinitionsImpl(EventBus eventBus, CachingDispatchAsync dispatch) {
		dispatch.execute(new GetProfileViewData(EchartsUser.sessionId), new EchartsCallback<GetProfileViewDataResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetProfileViewDataResult result) {
				setCostCenters(result.getCostCenters());
				setColumnDefinitions();
			}
		});

	}

	private void setCostCenters(Map<String, String> costCenters) {
		this.costCenters = costCenters;
	}

	private void setColumnDefinitions() {
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getStaffName());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Name";
			}

		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getUsername());
				} catch(NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Username";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getStaffId().isEmpty() ? "" : t.getStaffId());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "MIS Number";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getStaffDescription().isEmpty() ? "" : t.getStaffDescription());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Staff Description";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getRole().getRoleName().isEmpty() ? "" : t.getRole().getRoleName());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Role";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
			}

			@Override
			public String getHeader(User t) {
				return "Program";
			}

			@Override
			public String getData(User t) {
				try {
					return t.getProgram();
				} catch(NullPointerException e) {
					return "";
				}
			}

			@Override
			public Map<String, String> getMap(User t) {
				return costCenters;
			}

			@Override
			public void setData(User t, String program) {
				t.setProgram(program);
			}

			@Override
			public boolean isMap() {
				return true;
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
			}

			@Override
			public String getHeader(User t) {
				return "Credential 1";
			}

			@Override
			public String getData(User t) {
				try {
					return t.getCred1();
				} catch (NullPointerException e) {
					return "";
				}
			}

			@Override
			public void setData(User t, String cred1) {
				t.setCred1(cred1);
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
			}

			@Override
			public String getHeader(User t) {
				return "Credential 2";
			}

			@Override
			public String getData(User t) {
				try {
					return t.getCred2();
				} catch (NullPointerException e) {
					return "";
				}
			}

			@Override
			public void setData(User t, String cred2) {
				t.setCred2(cred2);
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getOffice().isEmpty() ? "" : t.getOffice());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Office";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getOfficePhone().isEmpty() ? "" : t.getOfficePhone());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Office Phone";
			}
		});
		this.add(new ColumnDefinition<User>() {
			@Override
			public void render(User t, StringBuilder sb) {
				try {
					sb.append(t.getSupervisor().isEmpty() ? "" : t.getSupervisor());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(User t) {
				return "Supervisor";
			}
		});
	}
}
