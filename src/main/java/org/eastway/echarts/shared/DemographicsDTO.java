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
package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DemographicsDTO implements Serializable, Demographics {
	private long id;
	private EHR ehr;
	private Code gender;
	private Code race;
	private Code maritalStatus;
	private Code livingArrangement;
	private Code employment;
	private String[] incomeSources;
	private Code educationLevel;
	private Code educationType;
	private String[] allergies;
	private String religion;
	private String insuranceType;
	private String preferredLanguage;
	private Code ethnicity;

	private boolean isVeteran;
	private boolean isSmd;
	private boolean isAlcoholDrug;
	private boolean isForensic;
	private boolean isDd;
	private boolean isMimr;
	private boolean isDuidwi;
	private boolean isDeaf;
	private boolean isHearingImpaired;
	private boolean isBlind;
	private boolean isVisuallyImpaired;
	private boolean isPhyDisabled;
	private boolean isSpeechImpaired;
	private boolean isPhysicalAbuse;
	private boolean isSexualAbuse;
	private boolean isDomesticViolence;
	private boolean isChildAlcDrug;
	private boolean isHivAids;
	private boolean isSuicidal;
	private boolean isSchoolDropout;
	private boolean isProbationParole;
	private boolean isGeneralPopulation;
	private Date dob;
	private Date lastEdit;
	private String lastEditBy;
	private long patientId;

	public DemographicsDTO() { }

	public DemographicsDTO(Code gender,
			Code race,
			Code maritalStatus,
			Code livingArrangement,
			Code employment,
			String[] incomeSources,
			Code educationLevel,
			Code educationType,
			String[] allergies,
			String insuranceType,
			String preferredLanguage,
			Code ethnicity,
			String religion,
			boolean isVeteran,
			boolean isSmd,
			boolean isAlcoholDrug,
			boolean isForensic,
			boolean isDd,
			boolean isMimr,
			boolean isDuidwi,
			boolean isDeaf,
			boolean isHearingImpaired,
			boolean isBlind,
			boolean isVisuallyImpaired,
			boolean isPhyDisabled,
			boolean isSpeechImpaired,
			boolean isPhysicalAbuse,
			boolean isSexualAbuse,
			boolean isDomesticViolence,
			boolean isChildAlcDrug,
			boolean isHivAids,
			boolean isSuicidal,
			boolean isSchoolDropout,
			boolean isProbationParole,
			boolean isGeneralPopulation,
			Date dob,
			Date lastEdit,
			String lastEditBy) {
		setGender(gender);
		setRace(race);
		setMaritalStatus(maritalStatus);
		setLivingArrangement(livingArrangement);
		setEmployment(employment);
		setIncomeSources(incomeSources);
		setEducationLevel(educationLevel);
		setEducationType(educationType);
		setAllergies(allergies);
		setInsuranceType(insuranceType);
		setPreferredLanguage(preferredLanguage);
		setEthnicity(ethnicity);
		setReligion(religion);
		setVeteran(isVeteran);
		setSmd(isSmd);
		setAlcoholDrug(isAlcoholDrug);
		setForensic(isForensic);
		setDd(isDd);
		setMimr(isMimr);
		setDuidwi(isDuidwi);
		setDeaf(isDeaf);
		setHearingImpaired(isHearingImpaired);
		setBlind(isBlind);
		setVisuallyImpaired(isVisuallyImpaired);
		setPhyDisabled(isPhyDisabled);
		setSpeechImpaired(isSpeechImpaired);
		setPhysicalAbuse(isPhysicalAbuse);
		setSexualAbuse(isSexualAbuse);
		setDomesticViolence(isDomesticViolence);
		setChildAlcDrug(isChildAlcDrug);
		setHivAids(isHivAids);
		setSuicidal(isSuicidal);
		setSchoolDropout(isSchoolDropout);
		setProbationParole(isProbationParole);
		setGeneralPopulation(isGeneralPopulation);
		setDob(dob);
		setLastEdit(lastEdit);
		setLastEditBy(lastEditBy);
	}

	@Override
	public void setGender(Code gender) {
		this.gender = gender;
	}

	@Override
	public Code getGender() {
		return gender;
	}

	@Override
	public void setRace(Code race) {
		this.race = race;
	}

	@Override
	public Code getRace() {
		return race;
	}

	@Override
	public void setMaritalStatus(Code maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public Code getMaritalStatus() {
		return maritalStatus;
	}

	@Override
	public void setLivingArrangement(Code livingArrangement) {
		this.livingArrangement = livingArrangement;
	}

	@Override
	public Code getLivingArrangement() {
		return livingArrangement;
	}

	@Override
	public void setEmployment(Code employment) {
		this.employment = employment;
	}

	@Override
	public Code getEmployment() {
		return employment;
	}

	@Override
	public void setIncomeSources(String[] incomeSources) {
		this.incomeSources = incomeSources;
	}

	@Override
	public String[] getIncomeSources() {
		return incomeSources;
	}

	@Override
	public void setEducationLevel(Code educationLevel) {
		this.educationLevel = educationLevel;
	}

	@Override
	public Code getEducationLevel() {
		return educationLevel;
	}

	@Override
	public void setEducationType(Code educationType) {
		this.educationType = educationType;
	}

	@Override
	public Code getEducationType() {
		return educationType;
	}

	@Override
	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

	@Override
	public String[] getAllergies() {
		return allergies;
	}

	@Override
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public String getInsuranceType() {
		return insuranceType;
	}

	@Override
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	@Override
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	@Override
	public void setEthnicity(Code ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Override
	public Code getEthnicity() {
		return ethnicity;
	}

	@Override
	public void setVeteran(boolean veteran) {
		this.isVeteran = veteran;
	}

	@Override
	public boolean isVeteran() {
		return isVeteran;
	}

	@Override
	public void setSmd(boolean isSmd) {
		this.isSmd = isSmd;
	}

	@Override
	public boolean isSmd() {
		return isSmd;
	}

	@Override
	public void setAlcoholDrug(boolean isAlcoholDrug) {
		this.isAlcoholDrug = isAlcoholDrug;
	}

	@Override
	public boolean isAlcoholDrug() {
		return isAlcoholDrug;
	}

	@Override
	public void setForensic(boolean isForensic) {
		this.isForensic = isForensic;
	}

	@Override
	public boolean isForensic() {
		return isForensic;
	}

	@Override
	public void setDd(boolean isDd) {
		this.isDd = isDd;
	}

	@Override
	public boolean isDd() {
		return isDd;
	}

	@Override
	public void setMimr(boolean isMimr) {
		this.isMimr = isMimr;
	}

	@Override
	public boolean isMimr() {
		return isMimr;
	}

	@Override
	public void setDuidwi(boolean isDuidwi) {
		this.isDuidwi = isDuidwi;
	}

	@Override
	public boolean isDuidwi() {
		return isDuidwi;
	}

	@Override
	public void setDeaf(boolean isDeaf) {
		this.isDeaf = isDeaf;
	}

	@Override
	public boolean isDeaf() {
		return isDeaf;
	}

	@Override
	public void setHearingImpaired(boolean isHearingImpaired) {
		this.isHearingImpaired = isHearingImpaired;
	}

	@Override
	public boolean isHearingImpaired() {
		return isHearingImpaired;
	}

	@Override
	public void setBlind(boolean isBlind) {
		this.isBlind = isBlind;
	}

	@Override
	public boolean isBlind() {
		return isBlind;
	}

	@Override
	public void setVisuallyImpaired(boolean isVisuallyImpaired) {
		this.isVisuallyImpaired = isVisuallyImpaired;
	}

	@Override
	public boolean isVisuallyImpaired() {
		return isVisuallyImpaired;
	}

	@Override
	public void setPhyDisabled(boolean isPhyDisabled) {
		this.isPhyDisabled = isPhyDisabled;
	}

	@Override
	public boolean isPhyDisabled() {
		return isPhyDisabled;
	}

	@Override
	public void setSpeechImpaired(boolean isSpeechImpaired) {
		this.isSpeechImpaired = isSpeechImpaired;
	}

	@Override
	public boolean isSpeechImpaired() {
		return isSpeechImpaired;
	}

	@Override
	public void setPhysicalAbuse(boolean isPhysicalAbuse) {
		this.isPhysicalAbuse = isPhysicalAbuse;
	}

	@Override
	public boolean isPhysicalAbuse() {
		return isPhysicalAbuse;
	}

	@Override
	public void setSexualAbuse(boolean isSexualAbuse) {
		this.isSexualAbuse = isSexualAbuse;
	}

	@Override
	public boolean isSexualAbuse() {
		return isSexualAbuse;
	}

	@Override
	public void setDomesticViolence(boolean isDomesticViolence) {
		this.isDomesticViolence = isDomesticViolence;
	}

	@Override
	public boolean isDomesticViolence() {
		return isDomesticViolence;
	}

	@Override
	public void setChildAlcDrug(boolean isChildAlcDrug) {
		this.isChildAlcDrug = isChildAlcDrug;
	}

	@Override
	public boolean isChildAlcDrug() {
		return isChildAlcDrug;
	}

	@Override
	public void setHivAids(boolean isHivAids) {
		this.isHivAids = isHivAids;
	}

	@Override
	public boolean isHivAids() {
		return isHivAids;
	}

	@Override
	public void setSuicidal(boolean isSuicidal) {
		this.isSuicidal = isSuicidal;
	}

	@Override
	public boolean isSuicidal() {
		return isSuicidal;
	}

	@Override
	public void setSchoolDropout(boolean isSchoolDropout) {
		this.isSchoolDropout = isSchoolDropout;
	}

	@Override
	public boolean isSchoolDropout() {
		return isSchoolDropout;
	}

	@Override
	public void setProbationParole(boolean isProbationParole) {
		this.isProbationParole = isProbationParole;
	}

	@Override
	public boolean isProbationParole() {
		return isProbationParole;
	}

	@Override
	public void setGeneralPopulation(boolean isGeneralPopulation) {
		this.isGeneralPopulation = isGeneralPopulation;
	}

	@Override
	public boolean isGeneralPopulation() {
		return isGeneralPopulation;
	}

	@Override
	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public Date getDob() {
		return dob;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Override
	public String getReligion() {
		return religion;
	}

	@Override
	public EHR getEhr() {
		return ehr;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	@Override
	public long getPatientId() {
		return patientId;
	}

	@Override
	public DemographicsDTO toDto() {
		return this;
	}
}
