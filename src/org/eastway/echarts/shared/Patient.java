package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Patient implements Serializable {
	private long patientId;
	private String caseNumber;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String suffix;
	private String alias;
	private String caseStatus;
	private String ssn;
	private String lastEditBy;
	private Date lastEdit;
	private Demographics demographics;

	public Patient() { }

	public Patient(String alias,
			String caseNumber,
			String caseStatus,
			String firstName,
			Date lastEdit,
			String lastEditBy,
			String lastName,
			String middleInitial,
			long patientId,
			String ssn,
			String suffix,
			Demographics demographics) {
		setAlias(alias);
		setCaseNumber(caseNumber);
		setCaseStatus(caseStatus);
		setFirstName(firstName);
		setLastEdit(lastEdit);
		setLastEditBy(lastEditBy);
		setLastName(lastName);
		setMiddleInitial(middleInitial);
		setPatientId(patientId);
		setSsn(ssn);
		setSuffix(suffix);
		setDemographics(demographics);
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getPatientId() {
		return patientId;
	}

	public String getName() {
		return getLastName() + ", " + getFirstName();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	public Demographics getDemographics() {
		return demographics;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}
}
