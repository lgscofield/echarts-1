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

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class EHRService {
	@PersistenceContext(unitName="EHRService")
	protected EntityManager em;

	public EHRService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public EHR create(Patient subject, Date timeCreated) {
		EHR ehr = new EHR();
		ehr.setSubject(subject);
		ehr.setTimeCreated(timeCreated);
		getEntityManager().persist(ehr);
		return ehr;
	}

	public EHR find(long ehrId) {
		return getEntityManager().find(EHR.class, ehrId);
	}

	public List<EHR> findAll() {
		TypedQuery<EHR> query = getEntityManager().createQuery(
				"SELECT e FROM EHR e", EHR.class);
		return query.getResultList();
	}
}
