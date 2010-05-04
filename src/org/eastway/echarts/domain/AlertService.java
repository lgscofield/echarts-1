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

public class AlertService {
	@PersistenceContext(unitName="AlertService")
	protected EntityManager em;

	public AlertService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public Alert createAlert(int id, String patientId, String name, String itemName,
			Date date) {
		Alert alert = new Alert(id);
		alert.setPatientId(patientId);
		alert.setName(name);
		alert.setItemName(itemName);
		alert.setDate(date);
		getEntityManager().persist(alert);
		return alert;
	}

	public void removeAlert(int id) {
		Alert alert = findAlert(id);
		if (alert != null)
			getEntityManager().remove(alert);
	}

	public Alert findAlert(int id) {
		return getEntityManager().find(Alert.class, id);
	}

	public List<Alert> findAllAlerts() {
		TypedQuery<Alert> query = getEntityManager().createQuery(
				"SELECT a FROM Alert a", Alert.class);
		return query.getResultList();
	}
}
