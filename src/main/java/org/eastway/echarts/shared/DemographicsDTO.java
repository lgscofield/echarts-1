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
	private Code gender;
	private Code race;
	private Code maritalStatus;
	private Code livingArrangement;
	private Code employment;
	private String incomeSources;
	private Code educationLevel;
	private Code educationType;
	private String allergies;
	private String religion;
	private String insuranceType;
	private String preferredLanguage;
	private Code ethnicity;

	private Boolean isVeteran;
	private Boolean isSmd;
	private Boolean isAlcoholDrug;
	private Boolean isForensic;
	private Boolean isDd;
	private Boolean isMimr;
	private Boolean isDuidwi;
	private Boolean isDeaf;
	private Boolean isHearingImpaired;
	private Boolean isBlind;
	private Boolean isVisuallyImpaired;
	private Boolean isPhyDisabled;
	private Boolean isSpeechImpaired;
	private Boolean isPhysicalAbuse;
	private Boolean isSexualAbuse;
	private Boolean isDomesticViolence;
	private Boolean isChildAlcDrug;
	private Boolean isHivAids;
	private Boolean isSuicidal;
	private Boolean isSchoolDropout;
	private Boolean isProbationParole;
	private Boolean isGeneralPopulation;
	private Date dob;
	private Date lastEdit;
	private String lastEditBy;
	private String caseNumber;

	public DemographicsDTO() { }

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
	public void setIncomeSources(String incomeSources) {
		this.incomeSources = incomeSources;
	}

	@Override
	public String getIncomeSources() {
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
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	@Override
	public String getAllergies() {
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
	public void setVeteran(Boolean veteran) {
		this.isVeteran = veteran;
	}

	@Override
	public Boolean isVeteran() {
		return isVeteran;
	}

	@Override
	public void setSmd(Boolean isSmd) {
		this.isSmd = isSmd;
	}

	@Override
	public Boolean isSmd() {
		return isSmd;
	}

	@Override
	public void setAlcoholDrug(Boolean isAlcoholDrug) {
		this.isAlcoholDrug = isAlcoholDrug;
	}

	@Override
	public Boolean isAlcoholDrug() {
		return isAlcoholDrug;
	}

	@Override
	public void setForensic(Boolean isForensic) {
		this.isForensic = isForensic;
	}

	@Override
	public Boolean isForensic() {
		return isForensic;
	}

	@Override
	public void setDd(Boolean isDd) {
		this.isDd = isDd;
	}

	@Override
	public Boolean isDd() {
		return isDd;
	}

	@Override
	public void setMimr(Boolean isMimr) {
		this.isMimr = isMimr;
	}

	@Override
	public Boolean isMimr() {
		return isMimr;
	}

	@Override
	public void setDuidwi(Boolean isDuidwi) {
		this.isDuidwi = isDuidwi;
	}

	@Override
	public Boolean isDuidwi() {
		return isDuidwi;
	}

	@Override
	public void setDeaf(Boolean isDeaf) {
		this.isDeaf = isDeaf;
	}

	@Override
	public Boolean isDeaf() {
		return isDeaf;
	}

	@Override
	public void setHearingImpaired(Boolean isHearingImpaired) {
		this.isHearingImpaired = isHearingImpaired;
	}

	@Override
	public Boolean isHearingImpaired() {
		return isHearingImpaired;
	}

	@Override
	public void setBlind(Boolean isBlind) {
		this.isBlind = isBlind;
	}

	@Override
	public Boolean isBlind() {
		return isBlind;
	}

	@Override
	public void setVisuallyImpaired(Boolean isVisuallyImpaired) {
		this.isVisuallyImpaired = isVisuallyImpaired;
	}

	@Override
	public Boolean isVisuallyImpaired() {
		return isVisuallyImpaired;
	}

	@Override
	public void setPhyDisabled(Boolean isPhyDisabled) {
		this.isPhyDisabled = isPhyDisabled;
	}

	@Override
	public Boolean isPhyDisabled() {
		return isPhyDisabled;
	}

	@Override
	public void setSpeechImpaired(Boolean isSpeechImpaired) {
		this.isSpeechImpaired = isSpeechImpaired;
	}

	@Override
	public Boolean isSpeechImpaired() {
		return isSpeechImpaired;
	}

	@Override
	public void setPhysicalAbuse(Boolean isPhysicalAbuse) {
		this.isPhysicalAbuse = isPhysicalAbuse;
	}

	@Override
	public Boolean isPhysicalAbuse() {
		return isPhysicalAbuse;
	}

	@Override
	public void setSexualAbuse(Boolean isSexualAbuse) {
		this.isSexualAbuse = isSexualAbuse;
	}

	@Override
	public Boolean isSexualAbuse() {
		return isSexualAbuse;
	}

	@Override
	public void setDomesticViolence(Boolean isDomesticViolence) {
		this.isDomesticViolence = isDomesticViolence;
	}

	@Override
	public Boolean isDomesticViolence() {
		return isDomesticViolence;
	}

	@Override
	public void setChildAlcDrug(Boolean isChildAlcDrug) {
		this.isChildAlcDrug = isChildAlcDrug;
	}

	@Override
	public Boolean isChildAlcDrug() {
		return isChildAlcDrug;
	}

	@Override
	public void setHivAids(Boolean isHivAids) {
		this.isHivAids = isHivAids;
	}

	@Override
	public Boolean isHivAids() {
		return isHivAids;
	}

	@Override
	public void setSuicidal(Boolean isSuicidal) {
		this.isSuicidal = isSuicidal;
	}

	@Override
	public Boolean isSuicidal() {
		return isSuicidal;
	}

	@Override
	public void setSchoolDropout(Boolean isSchoolDropout) {
		this.isSchoolDropout = isSchoolDropout;
	}

	@Override
	public Boolean isSchoolDropout() {
		return isSchoolDropout;
	}

	@Override
	public void setProbationParole(Boolean isProbationParole) {
		this.isProbationParole = isProbationParole;
	}

	@Override
	public Boolean isProbationParole() {
		return isProbationParole;
	}

	@Override
	public void setGeneralPopulation(Boolean isGeneralPopulation) {
		this.isGeneralPopulation = isGeneralPopulation;
	}

	@Override
	public Boolean isGeneralPopulation() {
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
	public DemographicsDTO toDto() {
		return this;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
}
