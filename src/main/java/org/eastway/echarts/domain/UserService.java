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
package org.eastway.echarts.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class UserService {
	@PersistenceContext(unitName="UserService")
	protected EntityManager em;

	public UserService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public User create() {
		User user = new User();
		return user;
	}

	public User find(long userId) {
		return getEntityManager().find(User.class, userId);
	}

	public User find(String username) {
		TypedQuery<User> query = getEntityManager().createQuery(
				"SELECT u FROM UserImpl u WHERE u.username = '" + username + "'", User.class);
		return query.getSingleResult();
	}
}
