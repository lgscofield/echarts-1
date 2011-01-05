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

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class SessionIdLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String sessionId;
	private Long sessionIdExpire;
	@PersistenceContext
	transient EntityManager entityManager;

	@ManyToOne
	@JoinTable(name="user_session_map",
			joinColumns=@JoinColumn(name="session_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private User user;

	public SessionIdLog() { }

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionIdExpire(long sessionIdExpire) {
		this.sessionIdExpire = sessionIdExpire;
	}

	public long getSessionIdExpire() {
		return sessionIdExpire;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new SessionIdLog().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static List<SessionIdLog> findUserBySessionId(String sessionId) {
		if (sessionId == null)
			return null;
		return entityManager().createQuery("SELECT o FROM SessionIdLog o WHERE o.sessionId = :sessionId", SessionIdLog.class)
			.setParameter("sessionId", sessionId)
			.getResultList();
	}
}
