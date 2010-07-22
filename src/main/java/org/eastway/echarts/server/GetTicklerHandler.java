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

import org.eastway.echarts.domain.AssignmentImpl;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetTicklerHandler implements ActionHandler<GetTickler, GetTicklerResult> {

	@Override
	public GetTicklerResult execute(GetTickler action, ExecutionContext context)
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
		List<AssignmentImpl> assignments = em.createQuery(
				"SELECT a From AssignmentImpl a Where a.staff = '" + action.getStaffId() + "' And a.disposition = 'Open' And a.service Like 'S%' Order By a.patient.lastName ASC, a.patient.firstName ASC, a.orderDate DESC", AssignmentImpl.class)
				.getResultList();
		List<Assignment> assignmentsDto = new ArrayList<Assignment>();
		for (Assignment assignment : assignments)
			if (assignment != null) {
				assignmentsDto.add(assignment.toDto());
			}
		em.close();
		return new GetTicklerResult(assignmentsDto);
	}

	@Override
	public Class<GetTickler> getActionType() {
		return GetTickler.class;
	}

	@Override
	public synchronized void rollback(GetTickler list, GetTicklerResult result, ExecutionContext context) throws ActionException {
		
	}
}
