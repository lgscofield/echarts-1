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

import org.eastway.echarts.domain.UserImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProfile;
import org.eastway.echarts.shared.GetProfileResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetProfileHandler implements ActionHandler<GetProfile, GetProfileResult> {

	@Override
	public Class<GetProfile> getActionType() {
		return GetProfile.class;
	}

	@Override
	public GetProfileResult execute(GetProfile action, ExecutionContext context)
			throws DispatchException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		UserImpl user = em.find(UserImpl.class, action.getUsername());
		GetProfileResult result;
		try {
			result = new GetProfileResult(user.toDto());
		} catch (NullPointerException e) {
			result = null;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public void rollback(GetProfile action, GetProfileResult result,
			ExecutionContext context) throws DispatchException {

	}

}
