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
package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.User;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@RooGwtMirroredFrom(User.class)
@ProxyFor(User.class)
public interface UserProxy extends EntityProxy {
	void setCred1(String cred1);
	String getCred1();

	void setCred2(String cred2);
	String getCred2();

	void setStaffId(String staffId);
	String getStaffId();

	void setStaffName(String staffName);
	String getStaffName();

	void setProgram(String program);
	String getProgram();

	void setStatus(String statuf);
	String getStatus();

	void setOffice(String office);
	String getOffice();

	void setOfficePhone(String officePhone);
	String getOfficePhone();

	void setOfficeExt(String officeExt);
	String getOfficeExt();

	void setStaffDescription(String staffDescription);
	String getStaffDescription();

	void setStaffNpi(String staffNpi);
	String getStaffNpi();

	void setHireDate(Date hireDate);
	Date getHireDate();

	void setTermDate(Date termDate);
	Date getTermDate();

	void setSupervisor(UserProxy supervisor);
	UserProxy getSupervisor();

	void setRole(RoleProxy role);
	RoleProxy getRole();

	//void setSessionId(String sessionId);
	//String getSessionId();

	String getId();
	void setId(String id);

	Integer getVersion();
}
