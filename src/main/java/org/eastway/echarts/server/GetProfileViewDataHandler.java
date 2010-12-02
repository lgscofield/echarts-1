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
import java.util.Map;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.Code;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProfileViewData;
import org.eastway.echarts.shared.GetProfileViewDataResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetProfileViewDataHandler implements
		ActionHandler<GetProfileViewData, GetProfileViewDataResult> {

	@Override
	public Class<GetProfileViewData> getActionType() {
		return GetProfileViewData.class;
	}

	@Override
	public GetProfileViewDataResult execute(GetProfileViewData action,
			ExecutionContext context) throws DispatchException {
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
			Map<String, String> costCenters = new LinkedHashMap<String, String>();
			List<Code> codes = em.createQuery("SELECT c FROM Code c WHERE c.columnName = 'CostCenter' ORDER BY c.descriptor", Code.class)
								.getResultList();
			for (Code c : codes)
				costCenters.put(c.getValue(), c.getDescriptor());
			return new GetProfileViewDataResult(costCenters);
		} finally {
			em.close();
		}
	}

	@Override
	public void rollback(GetProfileViewData action,
			GetProfileViewDataResult result, ExecutionContext context)
			throws DispatchException {
		
	}

}
