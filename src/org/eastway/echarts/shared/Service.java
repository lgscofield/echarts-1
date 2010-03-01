package org.eastway.echarts.shared;

import java.sql.Date;

public class Service {
	private String patientId;
	private Date startTime;
	private Integer duration;
	private Integer service;
	private String hipaaModifier1;
	private String hipaaModifier2;
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

	public void setHipaaModifier1(String hipaaModifier1) {
		this.hipaaModifier1 = hipaaModifier1;
	}

	public String getHipaaModifier1() {
		return hipaaModifier1;
	}

	public void setHipaaModifier2(String hipaaModifier2) {
		this.hipaaModifier2 = hipaaModifier2;
	}

	public String getHipaaModifier2() {
		return hipaaModifier2;
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
