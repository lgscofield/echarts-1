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
package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.eastway.echarts.server.EchartsEntityManagerFactory;

import com.google.gwt.requestfactory.shared.Version;

@Entity
public class Demographics {
	@Id
	private String caseNumber;
	@ManyToOne
	private Code gender;
	@ManyToOne
	private Code race;
	@ManyToOne
	private Code maritalStatus;
	@ManyToOne
	private Code livingArrangement;
	@ManyToOne
	private Code employment;
	private String incomeSources;
	@ManyToOne
	private Code educationLevel;
	@ManyToOne
	private Code educationType;
	private String allergies;
	private String religion;
	private String insuranceType;
	private String preferredLanguage;
	@ManyToOne
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
	@NotNull
	private Date lastEdit;
	private String lastEditBy;
	@Version
	private Integer version;

	public Demographics() {}

	public String getId() {
		return caseNumber;
	}

	public void setGender(Code gender) {
		this.gender = (Code) gender;
	}

	public Code getGender() {
		return gender;
	}

	public void setRace(Code race) {
		this.race = (Code) race;
	}

	public Code getRace() {
		return race;
	}

	public void setMaritalStatus(Code maritalStatus) {
		this.maritalStatus = (Code) maritalStatus;
	}

	public Code getMaritalStatus() {
		return maritalStatus;
	}

	public void setLivingArrangement(Code livingArrangement) {
		this.livingArrangement = (Code) livingArrangement;
	}

	public Code getLivingArrangement() {
		return livingArrangement;
	}

	public void setEmployment(Code employment) {
		this.employment = (Code) employment;
	}

	public Code getEmployment() {
		return employment;
	}

	public void setIncomeSources(String incomeSources) {
		this.incomeSources = incomeSources;
	}

	public String getIncomeSources() {
		return incomeSources;
	}

	public void setEducationLevel(Code educationLevel) {
		this.educationLevel = (Code) educationLevel;
	}

	public Code getEducationLevel() {
		return educationLevel;
	}

	public void setEducationType(Code educationType) {
		this.educationType = (Code) educationType;
	}

	public Code getEducationType() {
		return educationType;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getAllergies() {
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

	public void setEthnicity(Code ethnicity) {
		this.ethnicity = (Code) ethnicity;
	}

	public Code getEthnicity() {
		return ethnicity;
	}

	
	public void setVeteran(Boolean veteran) {
		this.isVeteran = veteran;
	}

	
	public Boolean getVeteran() {
		return isVeteran;
	}

	
	public void setSmd(Boolean isSmd) {
		this.isSmd = isSmd;
	}

	
	public Boolean getSmd() {
		return isSmd;
	}

	
	public void setAlcoholDrug(Boolean isAlcoholDrug) {
		this.isAlcoholDrug = isAlcoholDrug;
	}

	
	public Boolean getAlcoholDrug() {
		return isAlcoholDrug;
	}

	
	public void setForensic(Boolean isForensic) {
		this.isForensic = isForensic;
	}

	
	public Boolean getForensic() {
		return isForensic;
	}

	
	public void setDd(Boolean isDd) {
		this.isDd = isDd;
	}

	
	public Boolean getDd() {
		return isDd;
	}

	
	public void setMimr(Boolean isMimr) {
		this.isMimr = isMimr;
	}

	
	public Boolean getMimr() {
		return isMimr;
	}

	
	public void setDuidwi(Boolean isDuidwi) {
		this.isDuidwi = isDuidwi;
	}

	
	public Boolean getDuidwi() {
		return isDuidwi;
	}

	
	public void setDeaf(Boolean isDeaf) {
		this.isDeaf = isDeaf;
	}

	
	public Boolean getDeaf() {
		return isDeaf;
	}

	
	public void setHearingImpaired(Boolean isHearingImpaired) {
		this.isHearingImpaired = isHearingImpaired;
	}

	
	public Boolean getHearingImpaired() {
		return isHearingImpaired;
	}

	
	public void setBlind(Boolean isBlind) {
		this.isBlind = isBlind;
	}

	
	public Boolean getBlind() {
		return isBlind;
	}

	
	public void setVisuallyImpaired(Boolean isVisuallyImpaired) {
		this.isVisuallyImpaired = isVisuallyImpaired;
	}

	
	public Boolean getVisuallyImpaired() {
		return isVisuallyImpaired;
	}

	
	public void setPhyDisabled(Boolean isPhyDisabled) {
		this.isPhyDisabled = isPhyDisabled;
	}

	
	public Boolean getPhyDisabled() {
		return isPhyDisabled;
	}

	
	public void setSpeechImpaired(Boolean isSpeechImpaired) {
		this.isSpeechImpaired = isSpeechImpaired;
	}

	
	public Boolean getSpeechImpaired() {
		return isSpeechImpaired;
	}

	
	public void setPhysicalAbuse(Boolean isPhysicalAbuse) {
		this.isPhysicalAbuse = isPhysicalAbuse;
	}

	
	public Boolean getPhysicalAbuse() {
		return isPhysicalAbuse;
	}

	
	public void setSexualAbuse(Boolean isSexualAbuse) {
		this.isSexualAbuse = isSexualAbuse;
	}

	
	public Boolean getSexualAbuse() {
		return isSexualAbuse;
	}

	
	public void setDomesticViolence(Boolean isDomesticViolence) {
		this.isDomesticViolence = isDomesticViolence;
	}

	
	public Boolean getDomesticViolence() {
		return isDomesticViolence;
	}

	
	public void setChildAlcDrug(Boolean isChildAlcDrug) {
		this.isChildAlcDrug = isChildAlcDrug;
	}

	
	public Boolean getChildAlcDrug() {
		return isChildAlcDrug;
	}

	
	public void setHivAids(Boolean isHivAids) {
		this.isHivAids = isHivAids;
	}

	
	public Boolean getHivAids() {
		return isHivAids;
	}

	
	public void setSuicidal(Boolean isSuicidal) {
		this.isSuicidal = isSuicidal;
	}

	
	public Boolean getSuicidal() {
		return isSuicidal;
	}

	
	public void setSchoolDropout(Boolean isSchoolDropout) {
		this.isSchoolDropout = isSchoolDropout;
	}

	
	public Boolean getSchoolDropout() {
		return isSchoolDropout;
	}

	
	public void setProbationParole(Boolean isProbationParole) {
		this.isProbationParole = isProbationParole;
	}

	
	public Boolean getProbationParole() {
		return isProbationParole;
	}

	
	public void setGeneralPopulation(Boolean isGeneralPopulation) {
		this.isGeneralPopulation = isGeneralPopulation;
	}

	
	public Boolean getGeneralPopulation() {
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

	
	public void setReligion(String religion) {
		this.religion = religion;
	}

	
	public String getReligion() {
		return religion;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Integer getVersion() {
		return version;
	}

	public static Demographics findDemographics(String id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			Demographics demographics = em.find(Demographics.class, id);
			return demographics;
		} finally {
			em.close();
		}
	}
}
