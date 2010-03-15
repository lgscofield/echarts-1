package org.eastway.echarts.shared;

import java.io.Serializable;
import java.sql.Date;

import org.eastway.echarts.client.UserImpl;

@SuppressWarnings("serial")
public class UserData implements User, Serializable {
	private String staffId, userName, staffName, program, status, office,
			officePhone, officeExt, staffDescription, staffNpi;
	private Integer jobClassId, extendedPermissions;
	private Date hireDate, termDate;

	public void initUser() {
		new UserImpl(this);
	}

	public UserData() { };

	public UserData(String staffId, String userName, String staffName,
			String program, String status, String office,
			String officePhone, String officeExt,
			String staffDescription, String staffNpi,
			Integer jobClassId, Integer extendedPermissions,
			Date hireDate, Date termDate) {
		this.setStaffId(staffId);
		this.setUserName(userName);
		this.setStaffName(staffName);
		this.setProgram(program);
		this.setStatus(status);
		this.setOffice(office);
		this.setOfficePhone(officePhone);
		this.setOfficeExt(officeExt);
		this.setStaffDescription(staffDescription);
		this.setStaffNpi(staffNpi);
		this.setJobClassId(jobClassId);
		this.setExtendedPermissions(extendedPermissions);
		this.setHireDate(hireDate);
		this.setTermDate(termDate);
	}

	@Override
	public Integer getExtendedPermissions() {
		return extendedPermissions;
	}

	@Override
	public Date getHireDate() {
		return hireDate;
	}

	@Override
	public Integer getJobClassId() {
		return jobClassId;
	}

	@Override
	public String getOffice() {
		return office;
	}

	@Override
	public String getOfficeExt() {
		return officeExt;
	}

	@Override
	public String getOfficePhone() {
		return officePhone;
	}

	@Override
	public String getProgram() {
		return program;
	}

	@Override
	public String getStaffDescription() {
		return staffDescription;
	}

	@Override
	public String getStaffId() {
		return staffId;
	}

	@Override
	public String getStaffName() {
		return staffName;
	}

	@Override
	public String getStaffNpi() {
		return staffNpi;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public Date getTermDate() {
		return termDate;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setExtendedPermissions(Integer extendedPermissions) {
		this.extendedPermissions = extendedPermissions;
	}

	@Override
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public void setJobClassId(Integer jobClassId) {
		this.jobClassId = jobClassId;
	}

	@Override
	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public void setOfficeExt(String officeExt) {
		this.officeExt = officeExt;
	}

	@Override
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Override
	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public void setStaffDescription(String staffDescription) {
		this.staffDescription = staffDescription;
	}

	@Override
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Override
	public void setStaffNpi(String staffNpi) {
		this.staffNpi = staffNpi;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
