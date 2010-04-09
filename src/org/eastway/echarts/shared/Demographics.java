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
public class Demographics implements Serializable {
	private String gender;
	private String race;
	private String maritalStatus;
	private String livingArrangement;
	private String employment;
	private String[] incomeSources;
	private String educationLevel;
	private String educationType;
	private String[] allergies;
	private String religion;

	// TODO insuranceType, preferredLanguage, and ethnicity are not present
	// in the datebase
	private String insuranceType;
	private String preferredLanguage;
	private String ethnicity;

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

	public Demographics() { }

	public Demographics(String gender,
			String race,
			String maritalStatus,
			String livingArrangement,
			String employment,
			String[] incomeSources,
			String educationLevel,
			String educationType,
			String[] allergies,
			String insuranceType,
			String preferredLanguage,
			String ethnicity,
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

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
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

	public void setIncomeSources(String[] incomeSources) {
		this.incomeSources = incomeSources;
	}

	public String[] getIncomeSources() {
		return incomeSources;
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

	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

	public String[] getAllergies() {
		return allergies;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setVeteran(boolean veteran) {
		this.isVeteran = veteran;
	}

	public boolean isVeteran() {
		return isVeteran;
	}

	public void setSmd(boolean isSmd) {
		this.isSmd = isSmd;
	}

	public boolean isSmd() {
		return isSmd;
	}

	public void setAlcoholDrug(boolean isAlcoholDrug) {
		this.isAlcoholDrug = isAlcoholDrug;
	}

	public boolean isAlcoholDrug() {
		return isAlcoholDrug;
	}

	public void setForensic(boolean isForensic) {
		this.isForensic = isForensic;
	}

	public boolean isForensic() {
		return isForensic;
	}

	public void setDd(boolean isDd) {
		this.isDd = isDd;
	}

	public boolean isDd() {
		return isDd;
	}

	public void setMimr(boolean isMimr) {
		this.isMimr = isMimr;
	}

	public boolean isMimr() {
		return isMimr;
	}

	public void setDuidwi(boolean isDuidwi) {
		this.isDuidwi = isDuidwi;
	}

	public boolean isDuidwi() {
		return isDuidwi;
	}

	public void setDeaf(boolean isDeaf) {
		this.isDeaf = isDeaf;
	}

	public boolean isDeaf() {
		return isDeaf;
	}

	public void setHearingImpaired(boolean isHearingImpaired) {
		this.isHearingImpaired = isHearingImpaired;
	}

	public boolean isHearingImpaired() {
		return isHearingImpaired;
	}

	public void setBlind(boolean isBlind) {
		this.isBlind = isBlind;
	}

	public boolean isBlind() {
		return isBlind;
	}

	public void setVisuallyImpaired(boolean isVisuallyImpaired) {
		this.isVisuallyImpaired = isVisuallyImpaired;
	}

	public boolean isVisuallyImpaired() {
		return isVisuallyImpaired;
	}

	public void setPhyDisabled(boolean isPhyDisabled) {
		this.isPhyDisabled = isPhyDisabled;
	}

	public boolean isPhyDisabled() {
		return isPhyDisabled;
	}

	public void setSpeechImpaired(boolean isSpeechImpaired) {
		this.isSpeechImpaired = isSpeechImpaired;
	}

	public boolean isSpeechImpaired() {
		return isSpeechImpaired;
	}

	public void setPhysicalAbuse(boolean isPhysicalAbuse) {
		this.isPhysicalAbuse = isPhysicalAbuse;
	}

	public boolean isPhysicalAbuse() {
		return isPhysicalAbuse;
	}

	public void setSexualAbuse(boolean isSexualAbuse) {
		this.isSexualAbuse = isSexualAbuse;
	}

	public boolean isSexualAbuse() {
		return isSexualAbuse;
	}

	public void setDomesticViolence(boolean isDomesticViolence) {
		this.isDomesticViolence = isDomesticViolence;
	}

	public boolean isDomesticViolence() {
		return isDomesticViolence;
	}

	public void setChildAlcDrug(boolean isChildAlcDrug) {
		this.isChildAlcDrug = isChildAlcDrug;
	}

	public boolean isChildAlcDrug() {
		return isChildAlcDrug;
	}

	public void setHivAids(boolean isHivAids) {
		this.isHivAids = isHivAids;
	}

	public boolean isHivAids() {
		return isHivAids;
	}

	public void setSuicidal(boolean isSuicidal) {
		this.isSuicidal = isSuicidal;
	}

	public boolean isSuicidal() {
		return isSuicidal;
	}

	public void setSchoolDropout(boolean isSchoolDropout) {
		this.isSchoolDropout = isSchoolDropout;
	}

	public boolean isSchoolDropout() {
		return isSchoolDropout;
	}

	public void setProbationParole(boolean isProbationParole) {
		this.isProbationParole = isProbationParole;
	}

	public boolean isProbationParole() {
		return isProbationParole;
	}

	public void setGeneralPopulation(boolean isGeneralPopulation) {
		this.isGeneralPopulation = isGeneralPopulation;
	}

	public boolean isGeneralPopulation() {
		return isGeneralPopulation;
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

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	private void setReligion(String religion) {
		this.religion = religion;
	}

	public String getReligion() {
		return religion;
	}
}
