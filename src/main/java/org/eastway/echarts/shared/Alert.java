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

import java.util.Date;

public class Alert {
	private String patientId;
	private String name;
	private String itemName;
	private Date date;

	public Alert(String patientId, String name, String itemName,
			Date date) {
		this.setPatientId(patientId);
		this.setName(name);
		this.setItemName(itemName);
		this.setDate(date);
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
}
