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

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.SessionIdLog;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SessionExpiredException;
import org.springframework.dao.EmptyResultDataAccessException;

public class ServiceUtil {
	protected void checkSessionExpire(String sessionId) throws SessionExpiredException, DbException {
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		if (sessionId == null)
			throw new IllegalArgumentException("Please login");

		TypedQuery<SessionIdLog> query = em.createQuery(
				"SELECT s FROM SessionIdLog s WHERE s.sessionId = '" + sessionId + "'",
					SessionIdLog.class);
		SessionIdLog sil = null;
		try {
			sil = query.getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			throw new SessionExpiredException("Please login");
		} catch (NoResultException e) {
			throw new SessionExpiredException("Please login");
		}
		Date now = new Date();
		Date expire = new Date(sil.getSessionIdExpire());

		if (now.after(expire))
			throw new SessionExpiredException();
		em.close();
	}
}
