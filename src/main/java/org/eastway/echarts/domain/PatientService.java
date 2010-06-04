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

import org.eastway.echarts.shared.Patient;

public class PatientService {
	@PersistenceContext(unitName="PatientService")
	protected EntityManager em;

	public PatientService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public Patient create(String alias,
			String caseNumber,
			String caseStatus,
			long ehrId,
			String firstName,
			Date lastEdit,
			String lastEditBy,
			String lastName,
			String middleInitial,
			String ssn,
			String suffix) {
		Patient patient = new PatientImpl();
		patient.setAlias(alias);
		patient.setCaseNumber(caseNumber);
		patient.setCaseStatus(caseStatus);
		patient.setEhrId(ehrId);
		patient.setFirstName(firstName);
		patient.setLastEdit(lastEdit);
		patient.setLastEditBy(lastEditBy);
		patient.setLastName(lastName);
		patient.setMiddleInitial(middleInitial);
		patient.setSsn(ssn);
		patient.setSuffix(suffix);
		getEntityManager().persist(patient);
		return patient;
	}

	public void remove(long id) {
		Patient patient = getEntityManager().find(PatientImpl.class, id);
		if (patient != null)
			getEntityManager().remove(patient);
	}

	public PatientImpl find(long id) {
		return getEntityManager().find(PatientImpl.class, id);
	}

	public List<PatientImpl> findAll() {
		TypedQuery<PatientImpl> query = getEntityManager().createQuery(
				"SELECT p FROM PatientImpl p", PatientImpl.class);
		return query.getResultList();
	}
}
