package org.eastway.echarts.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		Demographics demographics = new Demographics();
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
		Demographics demographics = getEntityManager().find(Demographics.class, demographicsId);
		if (demographics != null)
			getEntityManager().remove(demographics);
	}

	public Demographics find(long demographicsId) {
		return getEntityManager().find(Demographics.class, demographicsId);
	}

	public List<Demographics> findAll() {
		TypedQuery<Demographics> query = getEntityManager().createQuery(
				"SELECT * FROM Demographics d", Demographics.class);
		return query.getResultList();
	}
}
