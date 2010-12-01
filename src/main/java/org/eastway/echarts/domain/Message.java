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
import javax.persistence.TableGenerator;

import org.eastway.echarts.server.EchartsEntityManagerFactory;
import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.MessageDTO;

import com.google.gwt.requestfactory.shared.Version;

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
	private CodeImpl messageType;
	private Date creationTimestamp;
	private String message;
	@ManyToOne
	private Message parent;
	private Date lastEdit;
	private String lastEditBy;
	@Version
	@Column(name = "version")
	private Integer version;

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
		this.messageType = (CodeImpl) messageType;
	}

	public CodeImpl getMessageType() {
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

	public MessageDTO toDto() {
		MessageDTO dto = new MessageDTO();
		dto.setCreationTimestamp(this.getCreationTimestamp());
		dto.setId(this.getId());
		dto.setCaseNumber(caseNumber);
		dto.setLastEdit(this.getLastEdit());
		dto.setLastEditBy(this.getLastEditBy());
		dto.setMessage(this.getMessage().toString());
		dto.setMessageType(this.getMessageType().toDto());
		if (dto.getParent() != null)
			dto.setParent(this.getParent().toDto());
		return dto;
	}

	public static Message findMessage(Long id) {
		if (id == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			Message message = em.find(Message.class, id);
			return message;
		} finally {
			em.close();
		}
	}

	public static List<Message> findMessageByCaseNumber(String caseNumber) {
		if (caseNumber == null)
			return null;
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			List<Message> messages = em.createQuery(
					"SELECT m FROM Message m WHERE m.caseNumber = '" + caseNumber + "' ORDER BY m.creationTimestamp DESC", Message.class)
					.getResultList();
			return messages;
		} finally {
			em.close();
		}
	}

	public void persist() {
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			em.persist(this);
		} finally {
			em.close();
		}
	}

	public void remove() {
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			Message attached = em.find(Message.class, this.id);
			em.remove(attached);
		} finally {
			em.close();
		}
	}
}
