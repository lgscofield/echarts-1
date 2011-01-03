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
package org.eastway.echartsrequest.server;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.eastway.echarts.domain.SessionIdLog;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.EmptyResultDataAccessException;

@Configurable
public class ServiceUtil {
	@PersistenceContext
	transient EntityManager entityManager;

	public static final boolean isUserLoggedIn(String sessionId) {
		if (sessionId == null)
			return false;
		try {
			SessionIdLog sil = entityManager().createQuery(
					"SELECT s FROM SessionIdLog s WHERE s.sessionId = :sessionId", SessionIdLog.class)
						.setParameter("sessionId", sessionId)
						.getSingleResult();
			Date now = new Date();
			Date expire = new Date(sil.getSessionIdExpire());
			if (now.after(expire))
				return false;
			else
				return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (NoResultException e) {
			return false;
		}
	}

	public static final EntityManager entityManager() {
		EntityManager em = new ServiceUtil().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
}
