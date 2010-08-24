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

import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.AssignmentImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProvider;
import org.eastway.echarts.shared.GetProviderResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetProviderHandler implements ActionHandler<GetProvider, GetProviderResult> {

	@Override
	public Class<GetProvider> getActionType() {
		return GetProvider.class;
	}

	@Override
	public GetProviderResult execute(GetProvider action,
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
		List<AssignmentImpl> assignments = em.createQuery(
				"SELECT a From AssignmentImpl a Where a.disposition = 'Open' And a.service Like 'S%' And a.caseNumber = '" + action.getCaseNumber() + "' Order By a.patient.lastName ASC, a.patient.firstName ASC, a.orderDate DESC", AssignmentImpl.class)
					.getResultList();
		for (AssignmentImpl a : assignments) {
			if (a.getStaff().matches(action.getStaffId()))
				return new GetProviderResult(a.getStaffName());
		}
		if (assignments.size() > 0)
			return new GetProviderResult(assignments.get(0).getStaffName());
		else
			return new GetProviderResult();
	}

	@Override
	public void rollback(GetProvider action, GetProviderResult result,
			ExecutionContext context) throws DispatchException {
		
	}

}
