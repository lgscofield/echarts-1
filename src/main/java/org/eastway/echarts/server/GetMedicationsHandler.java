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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.MedicationImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMedicationsResult;
import org.eastway.echarts.shared.Medication;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetMedicationsHandler implements ActionHandler<GetMedications, GetMedicationsResult> {

	@Override
	public GetMedicationsResult execute(GetMedications action,
			ExecutionContext context) throws ActionException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}

		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		List<MedicationImpl> medications = em.createQuery(
				"SELECT m FROM MedicationImpl m WHERE m.caseNumber = '" + action.getCaseNumber() + "'", MedicationImpl.class)
				.getResultList();
		List<Medication> medicationsDto = new ArrayList<Medication>();
		for (MedicationImpl medication : medications)
			medicationsDto.add(medication.toDto());
		em.close();
		return new GetMedicationsResult(medicationsDto);
	}

	@Override
	public Class<GetMedications> getActionType() {
		return GetMedications.class;
	}

	@Override
	public void rollback(GetMedications arg0, GetMedicationsResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
