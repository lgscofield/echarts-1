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

import java.math.BigDecimal;

import net.customware.gwt.dispatch.shared.Result;

public class GetProductivityResult implements Result {

	private String staffId;
	private String staffName;
	private String staffType;
	private String month;
	private BigDecimal group;
	private BigDecimal individual;
	private BigDecimal total;
	private double greenNumber;
	private double yellowNumber;
	
	public GetProductivityResult() { }

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setGroup(BigDecimal group) {
		this.group = group;
	}

	public BigDecimal getGroup() {
		return group;
	}

	public void setIndividual(BigDecimal individual) {
		this.individual = individual;
	}

	public BigDecimal getIndividual() {
		return individual;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setGreenNumber(double d) {
		this.greenNumber = d;
	}

	public double getGreenNumber() {
		return greenNumber;
	}

	public void setYellowNumber(double d) {
		this.yellowNumber = d;
	}

	public double getYellowNumber() {
		return yellowNumber;
	}
}
