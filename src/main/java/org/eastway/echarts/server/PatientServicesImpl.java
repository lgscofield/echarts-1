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

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.client.PatientServices;
import org.eastway.echarts.domain.Assignment;
import org.eastway.echarts.domain.Patient;
import org.eastway.echarts.domain.PatientService;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class PatientServicesImpl extends RpcServicesImpl implements PatientServices {
	@Override
	public PatientDTO getPatient(long patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		PatientService patientService = new PatientService(em);
		Patient patient = patientService.find(patientId);
		em.close();
		emf.close();
		return patient.toDto();
	}

	@Override
	public LinkedHashMap<String, Long> getPatientList(String sessionId, String staffId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		LinkedHashMap<String, Long> pl = new LinkedHashMap<String, Long>();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		List<Assignment> assignments = em.createQuery(
				"SELECT a From Assignment a Where a.staff = '" + staffId + "'", Assignment.class).getResultList();
		for (Assignment assignment : assignments)
			if (assignment != null)
				pl.put(assignment.getEhr().getSubject().getCaseNumber() + " - "
						+ assignment.getEhr().getSubject().getFirstName() + ", "
						+ assignment.getEhr().getSubject().getLastName(),
						assignment.getEhr().getId());
		em.close();
		emf.close();
		return pl;
	}
}
