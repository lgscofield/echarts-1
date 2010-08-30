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
import org.eastway.echarts.shared.SaveProfile;
import org.eastway.echarts.shared.SaveProfileResult;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.User;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class SaveProfileHandler implements ActionHandler<SaveProfile, SaveProfileResult> {

	@Override
	public Class<SaveProfile> getActionType() {
		return SaveProfile.class;
	}

	@Override
	public SaveProfileResult execute(SaveProfile action, ExecutionContext context) throws DispatchException {
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
			User user = action.getUser();
			em.getTransaction().begin();
			UserImpl userImpl = em.find(UserImpl.class, user.getUsername());
			userImpl.setProgram(user.getProgram());
			userImpl.setStaffName(user.getStaffName());
			userImpl.setCred1(user.getCred1());
			userImpl.setCred2(user.getCred2());
			em.persist(userImpl);
			em.getTransaction().commit();
			return userImpl == null ? new SaveProfileResult() : new SaveProfileResult(userImpl.toDto());
		} finally {
			em.close();
		}
	}

	@Override
	public void rollback(SaveProfile action, SaveProfileResult result,
			ExecutionContext context) throws DispatchException {
		// TODO Auto-generated method stub
		
	}

}
