package org.eastway.echarts.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.client.DemographicsServices;
import org.eastway.echarts.domain.Demographics;
import org.eastway.echarts.domain.DemographicsService;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class DemographicsServicesImpl extends RpcServicesImpl implements DemographicsServices {
	@Override
	public DemographicsDTO getDemographics(long id, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		DemographicsService demographicsService = new DemographicsService(
				em);
		Demographics demographics = demographicsService.find(id);
		DemographicsDTO demographicsDto = new DemographicsDTO();

		demographicsDto.setAlcoholDrug(demographics.isAlcoholDrug());
		demographicsDto.setAllergies(demographics.getAllergies());
		demographicsDto.setBlind(demographics.isBlind());
		demographicsDto.setChildAlcDrug(demographics.isChildAlcDrug());
		demographicsDto.setDd(demographics.isDd());
		demographicsDto.setDeaf(demographics.isDeaf());
		demographicsDto.setDob(demographics.getDob());
		demographicsDto.setDomesticViolence(demographics
				.isDomesticViolence());
		demographicsDto.setDuidwi(demographics.isDuidwi());
		demographicsDto.setEducationLevel(demographics
				.getEducationLevel());
		demographicsDto.setEducationType(demographics
				.getEducationType());
		demographicsDto.setEmployment(demographics.getEmployment());
		demographicsDto.setEthnicity(demographics.getEthnicity());
		demographicsDto.setForensic(demographics.isForensic());
		demographicsDto.setGender(demographics.getGender());
		demographicsDto.setGeneralPopulation(demographics
				.isGeneralPopulation());
		demographicsDto.setHearingImpaired(demographics
				.isHearingImpaired());
		demographicsDto.setHivAids(demographics.isHivAids());
		demographicsDto.setIncomeSources(demographics
				.getIncomeSources());
		demographicsDto.setInsuranceType(demographics
				.getInsuranceType());
		demographicsDto.setLastEdit(demographics.getLastEdit());
		demographicsDto.setLastEditBy(demographics.getLastEditBy());
		demographicsDto.setLivingArrangement(demographics
				.getLivingArrangement());
		demographicsDto.setMaritalStatus(demographics
				.getMaritalStatus());
		demographicsDto.setMimr(demographics.isMimr());
		demographicsDto.setPhyDisabled(demographics.isPhyDisabled());
		demographicsDto
				.setPhysicalAbuse(demographics
						.isPhysicalAbuse());
		demographicsDto.setPreferredLanguage(demographics
				.getPreferredLanguage());
		demographicsDto.setProbationParole(demographics
				.isProbationParole());
		demographicsDto.setRace(demographics.getRace());
		demographicsDto
				.setSchoolDropout(demographics
						.isSchoolDropout());
		demographicsDto.setSexualAbuse(demographics.isSexualAbuse());
		demographicsDto.setSmd(demographics.isSmd());
		demographicsDto.setSpeechImpaired(demographics
				.isSpeechImpaired());
		demographicsDto.setSuicidal(demographics.isSuicidal());
		demographicsDto.setVeteran(demographics.isVeteran());
		demographicsDto.setVisuallyImpaired(demographics
				.isVisuallyImpaired());
		em.close();
		emf.close();
		return demographicsDto;
	}
}
