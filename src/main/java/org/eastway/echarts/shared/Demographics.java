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

	public void setEhr(EHR ehr);

	public EHR getEhr();

	public void setId(long id);

	public long getId();

	public void setPatientId(long patientId);

	public long getPatientId();

	public void setGender(String gender);

	public String getGender();

	public void setRace(String race);

	public String getRace();

	public void setMaritalStatus(String maritalStatus);

	public String getMaritalStatus();

	public void setLivingArrangement(String livingArrangement);

	public String getLivingArrangement();

	public void setEmployment(String employment);

	public String getEmployment();

	public void setIncomeSources(String[] incomeSources);

	public String[] getIncomeSources();

	public void setEducationLevel(String educationLevel);

	public String getEducationLevel();

	public void setEducationType(String educationType);

	public String getEducationType();

	public void setAllergies(String[] allergies);

	public String[] getAllergies();

	public void setInsuranceType(String insuranceType);

	public String getInsuranceType();

	public void setPreferredLanguage(String preferredLanguage);

	public String getPreferredLanguage();

	public void setEthnicity(String ethnicity);

	public String getEthnicity();

	public void setVeteran(boolean veteran);

	public boolean isVeteran();

	public void setSmd(boolean isSmd);

	public boolean isSmd();

	public void setAlcoholDrug(boolean isAlcoholDrug);

	public boolean isAlcoholDrug();

	public void setForensic(boolean isForensic);

	public boolean isForensic();

	public void setDd(boolean isDd);

	public boolean isDd();

	public void setMimr(boolean isMimr);

	public boolean isMimr();

	public void setDuidwi(boolean isDuidwi);

	public boolean isDuidwi();

	public void setDeaf(boolean isDeaf);

	public boolean isDeaf();

	public void setHearingImpaired(boolean isHearingImpaired);

	public boolean isHearingImpaired();

	public void setBlind(boolean isBlind);

	public boolean isBlind();

	public void setVisuallyImpaired(boolean isVisuallyImpaired);

	public boolean isVisuallyImpaired();

	public void setPhyDisabled(boolean isPhyDisabled);

	public boolean isPhyDisabled();

	public void setSpeechImpaired(boolean isSpeechImpaired);

	public boolean isSpeechImpaired();

	public void setPhysicalAbuse(boolean isPhysicalAbuse);

	public boolean isPhysicalAbuse();

	public void setSexualAbuse(boolean isSexualAbuse);

	public boolean isSexualAbuse();

	public void setDomesticViolence(boolean isDomesticViolence);

	public boolean isDomesticViolence();

	public void setChildAlcDrug(boolean isChildAlcDrug);

	public boolean isChildAlcDrug();

	public void setHivAids(boolean isHivAids);

	public boolean isHivAids();

	public void setSuicidal(boolean isSuicidal);

	public boolean isSuicidal();

	public void setSchoolDropout(boolean isSchoolDropout);

	public boolean isSchoolDropout();

	public void setProbationParole(boolean isProbationParole);

	public boolean isProbationParole();

	public void setGeneralPopulation(boolean isGeneralPopulation);

	public boolean isGeneralPopulation();

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