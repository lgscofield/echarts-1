package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SessionIdLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String sessionId;
	private long sessionIdExpire;

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
}
