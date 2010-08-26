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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Productivity - Breakdown\"")
public class Productivity {

	@Id
	private Long id;

	private String caseNumber;

	private String staff;

	@Column(name = "staffname", columnDefinition="nvarchar")
	private String staffName;

	@Column(name = "stafftype", columnDefinition="nvarchar")
	private String staffType;

	@Column(columnDefinition="nvarchar")
	private String program;

	private String service;

	private String month;

	@Column(name = "M", scale=18, precision=0)
	private BigDecimal monthDigit;

	@Column(name = "Y")
	private String yearDigit;

	@Column(name = "sumofmin", scale=5, precision=2)
	private BigDecimal sumOfMinutes;

	@Column(name = "groupservice", scale=5, precision=2)
	private BigDecimal groupService;

	@Column(name = "indivservice", scale=18, precision=0)
	private BigDecimal individualService;

	@Column(name="doctorservice", scale=10, precision=6)
	private BigDecimal doctorService;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getStaff() {
		return staff;
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

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return program;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setMonthDigit(BigDecimal monthDigit) {
		this.monthDigit = monthDigit;
	}

	public BigDecimal getMonthDigit() {
		return monthDigit;
	}

	public void setYearDigit(String yearDigit) {
		this.yearDigit = yearDigit;
	}

	public String getYearDigit() {
		return yearDigit;
	}

	public void setSumOfMinutes(BigDecimal sumOfMinutes) {
		this.sumOfMinutes = sumOfMinutes;
	}

	public BigDecimal getSumOfMinutes() {
		return sumOfMinutes;
	}

	public void setGroupService(BigDecimal groupService) {
		this.groupService = groupService;
	}

	public BigDecimal getGroupService() {
		return groupService;
	}

	public void setIndividualService(BigDecimal individualService) {
		this.individualService = individualService;
	}

	public BigDecimal getIndividualService() {
		return individualService;
	}

	public void setDoctorService(BigDecimal doctorService) {
		this.doctorService = doctorService;
	}

	public BigDecimal getDoctorService() {
		return doctorService;
	}
}
