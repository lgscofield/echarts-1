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

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.DemographicsImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDemographicsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetDemographicsHandler implements
		ActionHandler<GetDemographics, GetDemographicsResult> {
	@Override
	public GetDemographicsResult execute(GetDemographics action,
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
		Demographics demographics = em.createQuery(
				"SELECT d FROM DemographicsImpl d WHERE d.caseNumber = '" + action.getCaseNumber() + "'", DemographicsImpl.class)
				.getSingleResult().toDto();
		em.close();
		return new GetDemographicsResult(demographics);
	}

	@Override
	public Class<GetDemographics> getActionType() {
		return GetDemographics.class;
	}

	@Override
	public void rollback(GetDemographics demographics,
			GetDemographicsResult result, ExecutionContext context)
			throws ActionException {

	}
}
