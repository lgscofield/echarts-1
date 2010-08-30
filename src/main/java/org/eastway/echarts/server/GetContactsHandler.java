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

import org.eastway.echarts.domain.ContactImpl;
import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetContactsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetContactsHandler implements
		ActionHandler<GetContacts, GetContactsResult> {

	@Override
	public GetContactsResult execute(GetContacts action, ExecutionContext context)
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
			List<ContactImpl> contacts = em.createQuery(
					"SELECT c FROM ContactImpl c WHERE c.caseNumber = '" + action.getCaseNumber() + "'", ContactImpl.class)
					.getResultList();
			List<Contact> dto = new ArrayList<Contact>();
			for (ContactImpl contact : contacts)
				dto.add(contact.toDto());
			return new GetContactsResult(dto);
		} finally {
			em.close();
		}
	}

	@Override
	public Class<GetContacts> getActionType() {
		return GetContacts.class;
	}

	@Override
	public void rollback(GetContacts arg0, GetContactsResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
