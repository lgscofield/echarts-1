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

import java.math.BigDecimal;

import org.eastway.echarts.domain.Productivity;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Productivity.class)
public interface ProductivityProxy extends EntityProxy {
	void setId(Long id);

	Long getId();

	void setCaseNumber(String caseNumber);

	String getCaseNumber();

	void setStaff(String staff);

	String getStaff();

	void setStaffName(String staffName);

	String getStaffName();

	void setStaffType(String staffType);

	String getStaffType();

	void setProgram(String program);

	String getProgram();

	void setService(String service);

	String getService();

	void setMonth(String month);

	String getMonth();

	void setMonthDigit(BigDecimal monthDigit);

	BigDecimal getMonthDigit();

	void setYearDigit(String yearDigit);

	String getYearDigit();

	void setSumOfMinutes(BigDecimal sumOfMinutes);

	BigDecimal getSumOfMinutes();

	void setGroupService(BigDecimal groupService);

	BigDecimal getGroupService();

	void setIndividualService(BigDecimal individualService);

	BigDecimal getIndividualService();

	void setDoctorService(BigDecimal doctorService);

	BigDecimal getDoctorService();

	Integer getVersion();

	void setVersion(Integer version);

	Double getTotal();

	Double getGreenNumber();

	Double getYellowNumber();
}
