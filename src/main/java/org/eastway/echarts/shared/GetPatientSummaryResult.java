package org.eastway.echarts.shared;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetPatientSummaryResult implements Result {

	private String caseNumber;
	private String name;
	private String gender;
	private Date dob;
	private String ethnicity;
	private String preferredLanguage;
	private String race;
	private String insuranceType;
	private String ssn;

	public GetPatientSummaryResult() { }

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRace() {
		return race;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}
}
