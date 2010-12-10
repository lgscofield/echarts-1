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

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Message {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "Message_Id")
	private Long id;
	private String caseNumber;
	@ManyToOne
	@JoinColumn(name = "MessageType_Id")
	private Code messageType;
	private Date creationTimestamp;
	private String message;
	@ManyToOne
	private Message parent;
	private Date lastEdit;
	private String lastEditBy;
	@Version
	@Column(name = "version")
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public Message() { }

	public Message(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setMessageType(Code messageType) {
		this.messageType = (Code) messageType;
	}

	public Code getMessageType() {
		return messageType;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message.toString();
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setParent(Message parent) {
		this.parent = parent;
	}

	public Message getParent() {
		return parent;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Message().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Message findMessage(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Message.class, id);
	}

	public static List<Message> findMessageByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		return entityManager().createQuery(
			"SELECT m FROM Message m WHERE m.caseNumber = :caseNumber ORDER BY m.creationTimestamp DESC", Message.class)
				.setParameter("caseNumber", caseNumber)
				.getResultList();
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	/**
	 * This should probably never be used.
	 */
	@Transactional
	public void remove() {
		if (this.entityManager == null) this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			Message attached = this.entityManager.find(this.getClass(), this.id);
			this.entityManager.remove(attached);
		}
	}
}
