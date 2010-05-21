package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Referral {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="referral_id")
	private long id;
	@Column(name = "ehr_id")
	private long ehrId;
	private String program;
	private String UCI;
	private String referralSource;
	private String referralType;
	private Date referralDate;
	private String takenByStaff;
	private Date admissionDate;
	private String levelOfCare;
	private String planType;
	private Date lastService;
	private Date dischargeDate;
	private String disposition;
	private Date lastEdit;
	private String lastEditBy;

	public Referral() { }

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return program;
	}

	public void setUCI(String uCI) {
		UCI = uCI;
	}

	public String getUCI() {
		return UCI;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	public String getReferralType() {
		return referralType;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setTakenByStaff(String takenByStaff) {
		this.takenByStaff = takenByStaff;
	}

	public String getTakenByStaff() {
		return takenByStaff;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}

	public String getLevelOfCare() {
		return levelOfCare;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	public Date getLastService() {
		return lastService;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}
}
