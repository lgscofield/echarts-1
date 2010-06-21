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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.client.AddressServices;
import org.eastway.echarts.domain.AddressImpl;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class AddressServicesImpl extends RpcServicesImpl implements AddressServices {
	@Override
	public List<Address> findByCaseNumber(String caseNumber, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		TypedQuery<AddressImpl> query = em.createQuery(
				"SELECT a FROM AddressImpl a where a.caseNumber = '" + caseNumber + "'", AddressImpl.class);
		List<AddressImpl> addresses = query.getResultList();
		List<Address> dtos = new ArrayList<Address>();

		for (AddressImpl address : addresses)
			dtos.add(address.toDto());

		em.close();
		emf.close();
		return dtos;
	}
}
