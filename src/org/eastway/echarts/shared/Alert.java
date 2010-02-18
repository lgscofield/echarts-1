package org.eastway.echarts.shared;

import java.sql.Date;

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
