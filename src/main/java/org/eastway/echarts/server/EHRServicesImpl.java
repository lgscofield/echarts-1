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
package org.eastway.echarts.server;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.client.EHRServices;
import org.eastway.echarts.domain.DemographicsService;
import org.eastway.echarts.domain.EHRImpl;
import org.eastway.echarts.domain.EHRService;
import org.eastway.echarts.domain.PatientService;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class EHRServicesImpl extends RpcServicesImpl implements EHRServices {
	@Override
	public EHR getEhr(long ehrId, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		EHRService ehrService = new EHRService(em);
		EHRImpl ehr = ehrService.find(ehrId);
		EHR ehrDto = new EHRDTO();
		ehrDto = ehr.toDto();
		DemographicsService ds = new DemographicsService(em);
		ehrDto.setDemographics(ds.find(ehrDto.getSubject().getId()).toDto());
		em.close();
		emf.close();
		return ehrDto;
	}

	@Override
	public EHR editEhr(EHR ehrDto, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		EHRService ehrService = new EHRService(em);

		em.getTransaction().begin();
		EHR ehr = ehrService.find(ehrDto.getId());

		Patient patientDto = ehrDto.getSubject();
		PatientService patientService = new PatientService(em);
		Patient patient = patientService.create(patientDto.getAlias(),
				patientDto.getCaseNumber(),
				patientDto.getCaseStatus(),
				ehr.getId(),
				patientDto.getFirstName(),
				new Date(System.currentTimeMillis()),
				patientDto.getLastEditBy(),
				patientDto.getLastName(),
				patientDto.getMiddleInitial(),
				patientDto.getSsn(),
				patientDto.getSuffix());

		DemographicsService demographicsService = new DemographicsService(em);
		Demographics demographicsDto = ehrDto.getDemographics();
		demographicsService.create(demographicsDto.getGender(),
				demographicsDto.getRace(),
				demographicsDto.getMaritalStatus(),
				demographicsDto.getLivingArrangement(),
				demographicsDto.getEmployment(),
				demographicsDto.getIncomeSources(),
				demographicsDto.getEducationLevel(),
				demographicsDto.getEducationType(),
				demographicsDto.getAllergies(),
				demographicsDto.getInsuranceType(),
				demographicsDto.getPreferredLanguage(),
				demographicsDto.getEthnicity(),
				demographicsDto.getReligion(),
				patient.getId(),
				demographicsDto.isVeteran(),
				demographicsDto.isSmd(),
				demographicsDto.isAlcoholDrug(),
				demographicsDto.isForensic(),
				demographicsDto.isDd(),
				demographicsDto.isMimr(),
				demographicsDto.isDuidwi(),
				demographicsDto.isDeaf(),
				demographicsDto.isHearingImpaired(),
				demographicsDto.isBlind(),
				demographicsDto.isVisuallyImpaired(),
				demographicsDto.isPhyDisabled(),
				demographicsDto.isSpeechImpaired(),
				demographicsDto.isPhysicalAbuse(),
				demographicsDto.isSexualAbuse(),
				demographicsDto.isDomesticViolence(),
				demographicsDto.isChildAlcDrug(),
				demographicsDto.isHivAids(),
				demographicsDto.isSuicidal(),
				demographicsDto.isSchoolDropout(),
				demographicsDto.isProbationParole(),
				demographicsDto.isGeneralPopulation(),
				demographicsDto.getDob(),
				new Date(System.currentTimeMillis()),
				demographicsDto.getLastEditBy());

		ehr.setSubject(patient);
		em.persist(ehr);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return ehrDto;
	}

	@Override
	public void addEhr(EHR ehrDto, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		PatientService patientService = new PatientService(em);

		em.getTransaction().begin();
		EHR ehr = new EHRImpl();
		em.persist(ehr);

		Patient patientDto = ehrDto.getSubject();
		Patient patient = patientService.create(patientDto.getAlias(),
				patientDto.getCaseNumber(),
				patientDto.getCaseStatus(),
				ehr.getId(),
				patientDto.getFirstName(),
				patientDto.getLastEdit(),
				patientDto.getLastEditBy(),
				patientDto.getLastName(),
				patientDto.getMiddleInitial(),
				patientDto.getSsn(),
				patientDto.getSuffix());
		ehr.setSubject(patient);
		ehr.setTimeCreated(new Date(System.currentTimeMillis()));
		em.persist(ehr);

		DemographicsService demographicsService = new DemographicsService(em);
		Demographics demographicsDto = ehrDto.getDemographics();
		demographicsService.create(demographicsDto.getGender(),
				demographicsDto.getRace(),
				demographicsDto.getMaritalStatus(),
				demographicsDto.getLivingArrangement(),
				demographicsDto.getEmployment(),
				demographicsDto.getIncomeSources(),
				demographicsDto.getEducationLevel(),
				demographicsDto.getEducationType(),
				demographicsDto.getAllergies(),
				demographicsDto.getInsuranceType(),
				demographicsDto.getPreferredLanguage(),
				demographicsDto.getEthnicity(),
				demographicsDto.getReligion(),
				patient.getId(),
				demographicsDto.isVeteran(),
				demographicsDto.isSmd(),
				demographicsDto.isAlcoholDrug(),
				demographicsDto.isForensic(),
				demographicsDto.isDd(),
				demographicsDto.isMimr(),
				demographicsDto.isDuidwi(),
				demographicsDto.isDeaf(),
				demographicsDto.isHearingImpaired(),
				demographicsDto.isBlind(),
				demographicsDto.isVisuallyImpaired(),
				demographicsDto.isPhyDisabled(),
				demographicsDto.isSpeechImpaired(),
				demographicsDto.isPhysicalAbuse(),
				demographicsDto.isSexualAbuse(),
				demographicsDto.isDomesticViolence(),
				demographicsDto.isChildAlcDrug(),
				demographicsDto.isHivAids(),
				demographicsDto.isSuicidal(),
				demographicsDto.isSchoolDropout(),
				demographicsDto.isProbationParole(),
				demographicsDto.isGeneralPopulation(),
				demographicsDto.getDob(),
				new Date(System.currentTimeMillis()),
				demographicsDto.getLastEditBy());
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
