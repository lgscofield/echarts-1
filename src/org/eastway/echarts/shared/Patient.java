package org.eastway.echarts.shared;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Patient implements Serializable {

	private String patientId, name, firstName, lastName, suffix, alias,
			caseStatus, gender, ssn, race, maritalStatus,
			livingArrangement, employment, incomeSource1,
			incomeSource2, incomeSource3, allergies, lastEditBy,
			educationLevel, educationType;

	private boolean veteran, sp_smd, sp_alcoholdrug, sp_forensic, sp_dd,
			sp_mimr, sp_duidwi, sp_deaf, sp_hearingimpaired,
			sp_blind, sp_visuallyimpaired, sp_phydisabled,
			sp_speechimpaired, sp_physicalabuse, sp_sexualabuse,
			sp_domesticviolence, sp_childalcdrug, sp_hivaids,
			sp_suicidal, sp_schooldropout, sp_probationparole,
			sp_generalpopulation;

	private Date dob, lastEdit;

	// TODO insuranceType, preferredLanguage, and ethnicity are not present
	// in the datebase
	private String insuranceType;

	private String preferredLanguage;

	private String ethnicity;

	public Patient() { }

	public Patient(String alias, String allergies, String caseStatus,
			Date dob, String educationLevel, String educationType,
			String employment, String firstName, String gender,
			String incomeSource1, String incomeSource2,
			String incomeSource3, Date lastEdit, String lastEditBy,
			String lastName, String livingArrangement,
			String maritalStatus, String name, String patientId,
			String race, boolean sp_alcoholdrug, boolean sp_blind,
			boolean sp_childalcdrug, boolean sp_dd, boolean sp_deaf,
			boolean sp_domesticviolence, boolean sp_duidwi,
			boolean sp_forensic, boolean sp_generalpopulation,
			boolean sp_hearingimpaired, boolean sp_hivaids,
			boolean sp_mimr, boolean sp_phydisabled,
			boolean sp_physicalabuse, boolean sp_probationparole,
			boolean sp_schooldropout, boolean sp_sexualabuse,
			boolean sp_smd, boolean sp_speechimpaired,
			boolean sp_suicidal, boolean sp_visuallyimpaired,
			String ssn, String suffix, boolean veteran) {
		this.alias = alias;
		this.allergies = allergies;
		this.caseStatus = caseStatus;
		this.dob = dob;
		this.educationLevel = educationLevel;
		this.educationType = educationType;
		this.employment = employment;
		this.firstName = firstName;
		this.gender = gender;
		this.incomeSource1 = incomeSource1;
		this.incomeSource2 = incomeSource2;
		this.incomeSource3 = incomeSource3;
		this.lastEdit = lastEdit;
		this.lastEditBy = lastEditBy;
		this.lastName = lastName;
		this.livingArrangement = livingArrangement;
		this.maritalStatus = maritalStatus;
		this.name = name;
		this.patientId = patientId;
		this.race = race;
		this.sp_alcoholdrug = sp_alcoholdrug;
		this.sp_blind = sp_blind;
		this.sp_childalcdrug = sp_childalcdrug;
		this.sp_dd = sp_dd;
		this.sp_deaf = sp_deaf;
		this.sp_domesticviolence = sp_domesticviolence;
		this.sp_duidwi = sp_duidwi;
		this.sp_forensic = sp_forensic;
		this.sp_generalpopulation = sp_generalpopulation;
		this.sp_hearingimpaired = sp_hearingimpaired;
		this.sp_hivaids = sp_hivaids;
		this.sp_mimr = sp_mimr;
		this.sp_phydisabled = sp_phydisabled;
		this.sp_physicalabuse = sp_physicalabuse;
		this.sp_probationparole = sp_probationparole;
		this.sp_schooldropout = sp_schooldropout;
		this.sp_sexualabuse = sp_sexualabuse;
		this.sp_smd = sp_smd;
		this.sp_speechimpaired = sp_speechimpaired;
		this.sp_suicidal = sp_suicidal;
		this.sp_visuallyimpaired = sp_visuallyimpaired;
		this.ssn = ssn;
		this.suffix = suffix;
		this.veteran = veteran;
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

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRace() {
		return race;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setLivingArrangement(String livingArrangement) {
		this.livingArrangement = livingArrangement;
	}

	public String getLivingArrangement() {
		return livingArrangement;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getEmployment() {
		return employment;
	}

	public void setIncomeSource1(String incomeSource1) {
		this.incomeSource1 = incomeSource1;
	}

	public String getIncomeSource1() {
		return incomeSource1;
	}

	public void setIncomeSource2(String incomeSource2) {
		this.incomeSource2 = incomeSource2;
	}

	public String getIncomeSource2() {
		return incomeSource2;
	}

	public void setIncomeSource3(String incomeSource3) {
		this.incomeSource3 = incomeSource3;
	}

	public String getIncomeSource3() {
		return incomeSource3;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public void setSp_smd(boolean sp_smd) {
		this.sp_smd = sp_smd;
	}

	public boolean isSp_smd() {
		return sp_smd;
	}

	public void setSp_alcoholdrug(boolean sp_alcoholdrug) {
		this.sp_alcoholdrug = sp_alcoholdrug;
	}

	public boolean isSp_alcoholdrug() {
		return sp_alcoholdrug;
	}

	public void setSp_forensic(boolean sp_forensic) {
		this.sp_forensic = sp_forensic;
	}

	public boolean isSp_forensic() {
		return sp_forensic;
	}

	public void setSp_dd(boolean sp_dd) {
		this.sp_dd = sp_dd;
	}

	public boolean isSp_dd() {
		return sp_dd;
	}

	public void setSp_mimr(boolean sp_mimr) {
		this.sp_mimr = sp_mimr;
	}

	public boolean isSp_mimr() {
		return sp_mimr;
	}

	public void setSp_duidwi(boolean sp_duidwi) {
		this.sp_duidwi = sp_duidwi;
	}

	public boolean isSp_duidwi() {
		return sp_duidwi;
	}

	public void setSp_deaf(boolean sp_deaf) {
		this.sp_deaf = sp_deaf;
	}

	public boolean isSp_deaf() {
		return sp_deaf;
	}

	public void setSp_hearingimpaired(boolean sp_hearingimpaired) {
		this.sp_hearingimpaired = sp_hearingimpaired;
	}

	public boolean isSp_hearingimpaired() {
		return sp_hearingimpaired;
	}

	public void setSp_blind(boolean sp_blind) {
		this.sp_blind = sp_blind;
	}

	public boolean isSp_blind() {
		return sp_blind;
	}

	public void setSp_visuallyimpaired(boolean sp_visuallyimpaired) {
		this.sp_visuallyimpaired = sp_visuallyimpaired;
	}

	public boolean isSp_visuallyimpaired() {
		return sp_visuallyimpaired;
	}

	public void setSp_phydisabled(boolean sp_phydisabled) {
		this.sp_phydisabled = sp_phydisabled;
	}

	public boolean isSp_phydisabled() {
		return sp_phydisabled;
	}

	public void setSp_speechimpaired(boolean sp_speechimpaired) {
		this.sp_speechimpaired = sp_speechimpaired;
	}

	public boolean isSp_speechimpaired() {
		return sp_speechimpaired;
	}

	public void setSp_physicalabuse(boolean sp_physicalabuse) {
		this.sp_physicalabuse = sp_physicalabuse;
	}

	public boolean isSp_physicalabuse() {
		return sp_physicalabuse;
	}

	public void setSp_sexualabuse(boolean sp_sexualabuse) {
		this.sp_sexualabuse = sp_sexualabuse;
	}

	public boolean isSp_sexualabuse() {
		return sp_sexualabuse;
	}

	public void setSp_domesticviolence(boolean sp_domesticviolence) {
		this.sp_domesticviolence = sp_domesticviolence;
	}

	public boolean isSp_domesticviolence() {
		return sp_domesticviolence;
	}

	public void setSp_childalcdrug(boolean sp_childalcdrug) {
		this.sp_childalcdrug = sp_childalcdrug;
	}

	public boolean isSp_childalcdrug() {
		return sp_childalcdrug;
	}

	public void setSp_hivaids(boolean sp_hivaids) {
		this.sp_hivaids = sp_hivaids;
	}

	public boolean isSp_hivaids() {
		return sp_hivaids;
	}

	public void setSp_suicidal(boolean sp_suicidal) {
		this.sp_suicidal = sp_suicidal;
	}

	public boolean isSp_suicidal() {
		return sp_suicidal;
	}

	public void setSp_schooldropout(boolean sp_schooldropout) {
		this.sp_schooldropout = sp_schooldropout;
	}

	public boolean isSp_schooldropout() {
		return sp_schooldropout;
	}

	public void setSp_probationparole(boolean sp_probationparole) {
		this.sp_probationparole = sp_probationparole;
	}

	public boolean isSp_probationparole() {
		return sp_probationparole;
	}

	public void setSp_generalpopulation(boolean sp_generalpopulation) {
		this.sp_generalpopulation = sp_generalpopulation;
	}

	public boolean isSp_generalpopulation() {
		return sp_generalpopulation;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEthncity() {
		return ethnicity;
	}
}
