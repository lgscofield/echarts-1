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

import java.util.Date;


public interface Demographics {

	public String getCaseNumber();

	public void setCaseNumber(String caseNumber);

	public void setGender(Code gender);

	public Code getGender();

	public void setRace(Code race);

	public Code getRace();

	public void setMaritalStatus(Code maritalStatus);

	public Code getMaritalStatus();

	public void setLivingArrangement(Code livingArrangement);

	public Code getLivingArrangement();

	public void setEmployment(Code employment);

	public Code getEmployment();

	public void setIncomeSources(String incomeSources);

	public String getIncomeSources();

	public void setEducationLevel(Code educationLevel);

	public Code getEducationLevel();

	public void setEducationType(Code educationType);

	public Code getEducationType();

	public void setAllergies(String allergies);

	public String getAllergies();

	public void setInsuranceType(String insuranceType);

	public String getInsuranceType();

	public void setPreferredLanguage(String preferredLanguage);

	public String getPreferredLanguage();

	public void setEthnicity(Code ethnicity);

	public Code getEthnicity();

	public void setVeteran(Boolean veteran);

	public Boolean isVeteran();

	public void setSmd(Boolean isSmd);

	public Boolean isSmd();

	public void setAlcoholDrug(Boolean isAlcoholDrug);

	public Boolean isAlcoholDrug();

	public void setForensic(Boolean isForensic);

	public Boolean isForensic();

	public void setDd(Boolean isDd);

	public Boolean isDd();

	public void setMimr(Boolean isMimr);

	public Boolean isMimr();

	public void setDuidwi(Boolean isDuidwi);

	public Boolean isDuidwi();

	public void setDeaf(Boolean isDeaf);

	public Boolean isDeaf();

	public void setHearingImpaired(Boolean isHearingImpaired);

	public Boolean isHearingImpaired();

	public void setBlind(Boolean isBlind);

	public Boolean isBlind();

	public void setVisuallyImpaired(Boolean isVisuallyImpaired);

	public Boolean isVisuallyImpaired();

	public void setPhyDisabled(Boolean isPhyDisabled);

	public Boolean isPhyDisabled();

	public void setSpeechImpaired(Boolean isSpeechImpaired);

	public Boolean isSpeechImpaired();

	public void setPhysicalAbuse(Boolean isPhysicalAbuse);

	public Boolean isPhysicalAbuse();

	public void setSexualAbuse(Boolean isSexualAbuse);

	public Boolean isSexualAbuse();

	public void setDomesticViolence(Boolean isDomesticViolence);

	public Boolean isDomesticViolence();

	public void setChildAlcDrug(Boolean isChildAlcDrug);

	public Boolean isChildAlcDrug();

	public void setHivAids(Boolean isHivAids);

	public Boolean isHivAids();

	public void setSuicidal(Boolean isSuicidal);

	public Boolean isSuicidal();

	public void setSchoolDropout(Boolean isSchoolDropout);

	public Boolean isSchoolDropout();

	public void setProbationParole(Boolean isProbationParole);

	public Boolean isProbationParole();

	public void setGeneralPopulation(Boolean isGeneralPopulation);

	public Boolean isGeneralPopulation();

	public void setDob(Date dob);

	public Date getDob();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setReligion(String religion);

	public String getReligion();

	public DemographicsDTO toDto();
}