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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.eastway.echarts.shared.Demographics;

public class DemographicsService {
	@PersistenceContext(unitName="DemographicsService")
	protected EntityManager em;

	public DemographicsService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public Demographics create(String gender,
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
			long patientId,
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
		Demographics demographics = new DemographicsImpl();
		demographics.setGender(gender);
		demographics.setRace(race);
		demographics.setMaritalStatus(maritalStatus);
		demographics.setLivingArrangement(livingArrangement);
		demographics.setEmployment(employment);
		demographics.setIncomeSources(incomeSources);
		demographics.setEducationLevel(educationLevel);
		demographics.setEducationType(educationType);
		demographics.setAllergies(allergies);
		demographics.setInsuranceType(insuranceType);
		demographics.setPreferredLanguage(preferredLanguage);
		demographics.setEthnicity(ethnicity);
		demographics.setReligion(religion);
		demographics.setPatientId(patientId);
		demographics.setVeteran(isVeteran);
		demographics.setSmd(isSmd);
		demographics.setAlcoholDrug(isAlcoholDrug);
		demographics.setForensic(isForensic);
		demographics.setDd(isDd);
		demographics.setMimr(isMimr);
		demographics.setDuidwi(isDuidwi);
		demographics.setDeaf(isDeaf);
		demographics.setHearingImpaired(isHearingImpaired);
		demographics.setBlind(isBlind);
		demographics.setVisuallyImpaired(isVisuallyImpaired);
		demographics.setPhyDisabled(isPhyDisabled);
		demographics.setSpeechImpaired(isSpeechImpaired);
		demographics.setPhysicalAbuse(isPhysicalAbuse);
		demographics.setSexualAbuse(isSexualAbuse);
		demographics.setDomesticViolence(isDomesticViolence);
		demographics.setChildAlcDrug(isChildAlcDrug);
		demographics.setHivAids(isHivAids);
		demographics.setSuicidal(isSuicidal);
		demographics.setSchoolDropout(isSchoolDropout);
		demographics.setProbationParole(isProbationParole);
		demographics.setGeneralPopulation(isGeneralPopulation);
		demographics.setDob(dob);
		demographics.setLastEdit(lastEdit);
		demographics.setLastEditBy(lastEditBy);
		getEntityManager().persist(demographics);
		return demographics;
	}

	public void remove(long demographicsId) {
		Demographics demographics = getEntityManager().find(DemographicsImpl.class, demographicsId);
		if (demographics != null)
			getEntityManager().remove(demographics);
	}

	public Demographics find(long demographicsId) {
		return getEntityManager().find(DemographicsImpl.class, demographicsId);
	}

	public List<DemographicsImpl> findAll() {
		TypedQuery<DemographicsImpl> query = getEntityManager().createQuery(
				"SELECT * FROM DemographicsImpl d", DemographicsImpl.class);
		return query.getResultList();
	}
}
