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
package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.Demographics;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Demographics.class)
public interface DemographicsProxy extends EntityProxy {

	public void setGender(CodeProxy gender);

	public CodeProxy getGender();

	public void setRace(CodeProxy race);

	public CodeProxy getRace();

	public void setMaritalStatus(CodeProxy maritalStatus);

	public CodeProxy getMaritalStatus();

	public void setLivingArrangement(CodeProxy livingArrangement);

	public CodeProxy getLivingArrangement();

	public void setEmployment(CodeProxy employment);

	public CodeProxy getEmployment();

	public void setIncomeSources(String incomeSources);

	public String getIncomeSources();

	public void setEducationLevel(CodeProxy educationLevel);

	public CodeProxy getEducationLevel();

	public void setEducationType(CodeProxy educationType);

	public CodeProxy getEducationType();

	public void setAllergies(String allergies);

	public String getAllergies();

	public void setInsuranceType(String insuranceType);

	public String getInsuranceType();

	public void setPreferredLanguage(String preferredLanguage);

	public String getPreferredLanguage();

	public void setEthnicity(CodeProxy ethnicity);

	public CodeProxy getEthnicity();

	public void setVeteran(Boolean veteran);

	public Boolean getVeteran();

	public void setSmd(Boolean isSmd);

	public Boolean getSmd();

	public void setAlcoholDrug(Boolean isAlcoholDrug);

	public Boolean getAlcoholDrug();

	public void setForensic(Boolean isForensic);

	public Boolean getForensic();

	public void setDd(Boolean isDd);

	public Boolean getDd();

	public void setMimr(Boolean isMimr);

	public Boolean getMimr();

	public void setDuidwi(Boolean isDuidwi);

	public Boolean getDuidwi();

	public void setDeaf(Boolean isDeaf);

	public Boolean getDeaf();

	public void setHearingImpaired(Boolean isHearingImpaired);

	public Boolean getHearingImpaired();

	public void setBlind(Boolean isBlind);

	public Boolean getBlind();

	public void setVisuallyImpaired(Boolean isVisuallyImpaired);

	public Boolean getVisuallyImpaired();

	public void setPhyDisabled(Boolean isPhyDisabled);

	public Boolean getPhyDisabled();

	public void setSpeechImpaired(Boolean isSpeechImpaired);

	public Boolean getSpeechImpaired();

	public void setPhysicalAbuse(Boolean isPhysicalAbuse);

	public Boolean getPhysicalAbuse();

	public void setSexualAbuse(Boolean isSexualAbuse);

	public Boolean getSexualAbuse();

	public void setDomesticViolence(Boolean isDomesticViolence);

	public Boolean getDomesticViolence();

	public void setChildAlcDrug(Boolean isChildAlcDrug);

	public Boolean getChildAlcDrug();

	public void setHivAids(Boolean isHivAids);

	public Boolean getHivAids();

	public void setSuicidal(Boolean isSuicidal);

	public Boolean getSuicidal();

	public void setSchoolDropout(Boolean isSchoolDropout);

	public Boolean getSchoolDropout();

	public void setProbationParole(Boolean isProbationParole);

	public Boolean getProbationParole();

	public void setGeneralPopulation(Boolean isGeneralPopulation);

	public Boolean getGeneralPopulation();

	public void setDob(Date dob);

	public Date getDob();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public void setReligion(String religion);

	public String getReligion();

	public String getCaseNumber();

	public void setCaseNumber(String caseNumber);

	public EntityProxyId<DemographicsProxy> stableId();
}