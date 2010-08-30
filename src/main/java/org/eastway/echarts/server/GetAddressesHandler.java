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
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.AddressImpl;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAddressesResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetAddressesHandler implements ActionHandler<GetAddresses, GetAddressesResult> {

	@Override
	public GetAddressesResult execute(GetAddresses action, ExecutionContext context)
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
			TypedQuery<AddressImpl> query = em.createQuery(
					"SELECT a FROM AddressImpl a where a.caseNumber = '" + action.getCaseNumber() + "'", AddressImpl.class);
			List<AddressImpl> addresses = query.getResultList();
			List<Address> dtos = new ArrayList<Address>();
			for (AddressImpl address : addresses)
				dtos.add(address.toDto());
			return new GetAddressesResult(dtos);
		} finally {
			em.close();
		}
	}

	@Override
	public Class<GetAddresses> getActionType() {
		return GetAddresses.class;
	}

	@Override
	public void rollback(GetAddresses arg0, GetAddressesResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
