package org.eastway.echarts.shared;

import java.math.BigDecimal;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
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
