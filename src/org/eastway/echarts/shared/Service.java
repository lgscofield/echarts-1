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

import java.sql.Date;

public class Service {
	private String patientId;
	private Date startTime;
	private Integer duration;
	private Integer service;
	private String modifier1;
	private String modifier2;
	private String staffId;
	private String location1;
	private String location2;

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getService() {
		return service;
	}

	public void setModifier1(String modifier1) {
		this.modifier1 = modifier1;
	}

	public String getModifier1() {
		return modifier1;
	}

	public void setModifier2(String modifier2) {
		this.modifier2 = modifier2;
	}

	public String getModifier2() {
		return modifier2;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getLocation2() {
		return location2;
	}
}
