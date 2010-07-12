package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Holiday {

	@Id
	private int id;

	private String day;
	private String month;
	private String year;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDay() {
		return day;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return year;
	}
}
