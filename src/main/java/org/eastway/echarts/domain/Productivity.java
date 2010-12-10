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
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name = "\"Productivity - Breakdown\"")
public class Productivity {
	@PersistenceContext
	transient EntityManager entityManager;

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

	@Version
	@Column(name = "version")
	private Integer version;

	transient Double total;

	transient Double greenNumber;

	transient Double yellowNumber;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Double getTotal() {
		return total;
	}

	public Double getYellowNumber() {
		return yellowNumber;
	}

	public Double getGreenNumber() {
		return greenNumber;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setYellowNumber(Double yellowNumber) {
		this.yellowNumber = yellowNumber;
	}

	public void setGreenNumber(Double greenNumber) {
		this.greenNumber = greenNumber;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Productivity().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Productivity findProductivity(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Productivity.class, id);
	}

	public static Productivity findProductivityByStaffId(String staffId) {
		if (staffId.equals("5597") || staffId.equals("5274"))
			staffId = "5262";

		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		String monthYear = month + "-" + year;

		List<Productivity> productivityList = entityManager().createQuery(
			"SELECT p FROM Productivity p WHERE p.staff = :staffId AND p.month = :month", Productivity.class)
				.setParameter("staffId", staffId)
				.setParameter("month", monthYear)
				.getResultList();
		BigDecimal individual = new BigDecimal(0.00);
		BigDecimal group = new BigDecimal(0.00);
		for (Productivity p : productivityList) {
			individual = individual.add(p.getIndividualService()
					.divide(new BigDecimal(4.00))
					.add(p.getDoctorService()));
			group = group.add(p.getGroupService()
					.divide(new BigDecimal(4.00)));
		}
		BigDecimal total = new BigDecimal(0.00);
		total = group.add(individual);
		Productivity productivity = new Productivity();
		productivity.setTotal(total.setScale(1, RoundingMode.HALF_UP).doubleValue());
		double monthlyWorkDays = getMonthlyWorkDays(calendar);
		double currentWorkDays = getCurrentWorkDays(calendar);
		productivity.setGreenNumber(calculateGreenNumber(monthlyWorkDays, currentWorkDays));
		productivity.setYellowNumber(calculateYellowNumber(monthlyWorkDays, currentWorkDays));
		return productivity;
	}

	private static Double calculateYellowNumber(Double m, Double c) {
		return new BigDecimal((92.0/m) * c).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private static double calculateGreenNumber(double m, double c) {
		return new BigDecimal((100.0/m) * c).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private static long getMonthlyWorkDays(Calendar calendar) {
		long workDays = 0;
		int month = calendar.get(Calendar.MONTH);
		Calendar monthlyWorkDaysCalendar = Calendar.getInstance();

		monthlyWorkDaysCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		monthlyWorkDaysCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		monthlyWorkDaysCalendar.set(Calendar.DAY_OF_MONTH, 1);

		while (monthlyWorkDaysCalendar.get(Calendar.MONTH) == month) {
			if (monthlyWorkDaysCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
					monthlyWorkDaysCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				workDays++;
			monthlyWorkDaysCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		monthlyWorkDaysCalendar.add(Calendar.MONTH, -1);
		long holidays = getHolidays(monthlyWorkDaysCalendar);
		return workDays - holidays;
	}

	private static Long getHolidays(Calendar calendar) {
		return entityManager().createQuery(
			"SELECT COUNT(h) FROM Holiday h WHERE h.month = '" + (calendar.get(Calendar.MONTH)+1) + "' "
			+ "AND h.year = '" + calendar.get(Calendar.YEAR) + "' "
			+ "AND h.day > '"	+ calendar.get(Calendar.DAY_OF_MONTH) + "'" , Long.class)
				.getSingleResult();
	}

	private static Long getCurrentWorkDays(Calendar calendar) {
		long holidays = getHolidays(calendar);
		long workDays = 0;
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		Calendar currentWorkDays = Calendar.getInstance();
		currentWorkDays.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		currentWorkDays.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		currentWorkDays.set(Calendar.DAY_OF_MONTH, 1);
		while (currentWorkDays.get(Calendar.DAY_OF_MONTH) != today) {
			if (currentWorkDays.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
					currentWorkDays.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				workDays++;
			currentWorkDays.add(Calendar.DAY_OF_MONTH, 1);
		}
		return workDays - holidays;
	}
}
