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
import java.util.Date;

import org.eastway.echarts.domain.ARInfo;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(ARInfo.class)
public interface ARInfoProxy extends EntityProxy {
	public void setId(String id);

	public String getId();

	public void setBillCode(String billCode);

	public String getBillCode();

	public void setArStatus(String arStatus);

	public String getArStatus();

	public void setIncome(Integer income);

	public Integer getIncome();

	public void setDependents(Short dependents);

	public Short getDependents();

	public void setSpendDown(BigDecimal spendDown);

	public BigDecimal getSpendDown();

	public void setAdmissionDate(Date admissionDate);

	public Date getAdmissionDate();

	public void setMedicaidId(String medicaidId);

	public String getMedicaidId();

	public void setTitleTwentyAppDate(Date titleTwentyAppDate);

	public Date getTitleTwentyAppDate();

	public void setTitleTwentyRedetermDate(Date titleTwentyRedetermDate);

	public Date getTitleTwentyRedetermDate();

	public void setTitleTwentyEligibilityCategory(
			String titleTwentyEligibilityCategory);

	public String getTitleTwentyEligibilityCategory();

	public void setUci(String uci);

	public String getUci();

	public void setMacRegDate(Date macRegDate);

	public Date getMacRegDate();

	public void setMacRegName(String macRegName);

	public String getMacRegName();
}
