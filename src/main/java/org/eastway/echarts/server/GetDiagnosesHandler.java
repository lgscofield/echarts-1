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

import org.eastway.echarts.domain.DiagnosisImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetDiagnosesResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetDiagnosesHandler implements ActionHandler<GetDiagnoses, GetDiagnosesResult> {

	@Override
	public GetDiagnosesResult execute(GetDiagnoses action, ExecutionContext context)
			throws ActionException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}

		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			List<DiagnosisImpl> diagnoses = em.createQuery(
					"SELECT d FROM DiagnosisImpl d WHERE d.caseNumber = '" + action.getCaseNumber() + "' ORDER BY d.date DESC", DiagnosisImpl.class)
					.getResultList();
			List<Diagnosis> diagnosesDto = new ArrayList<Diagnosis>();
			for (DiagnosisImpl diagnosis : diagnoses)
				diagnosesDto.add(diagnosis.toDto());
			return new GetDiagnosesResult(diagnosesDto);
		} finally {
			em.close();
		}
	}

	@Override
	public Class<GetDiagnoses> getActionType() {
		return GetDiagnoses.class;
	}

	@Override
	public void rollback(GetDiagnoses arg0, GetDiagnosesResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
