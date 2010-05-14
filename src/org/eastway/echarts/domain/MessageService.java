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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class MessageService {
	@PersistenceContext(unitName="MessageService")
	protected EntityManager em;

	public MessageService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public List<Message> findList(long ehrId) {
		TypedQuery<Message> query = getEntityManager().createQuery(
				"SELECT m FROM Message m WHERE m.ehrId = " + ehrId, Message.class);
		return query.getResultList();
	}

	public Message find(long id) {
		return getEntityManager().find(Message.class, id);
	}

	public Message create(Date creationTimestamp, long ehrId,
			Date lastEdit,
			String lastEditBy,
			String content,
			MessageType type,
			Message parent) {
		Message message = new Message();
		message.setCreationTimestamp(creationTimestamp);
		message.setEhrId(ehrId);
		message.setLastEdit(lastEdit);
		message.setLastEditBy(lastEditBy);
		message.setMessage(content);
		message.setMessageType(type);
		message.setParent(parent);
		getEntityManager().persist(message);
		return message;
	}
}
