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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.MessageDTO;

@Entity
public class Message {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	@Column(name = "Message_Id")
	private long id;
	@Column(name = "ehr_id")
	private long ehrId;
	@ManyToOne
	@JoinColumn(name = "MessageType_Id")
	private MessageType messageType;
	private Date creationTimestamp;
	private String message;
	@ManyToOne
	@JoinColumn(name = "Message_Id", insertable = false, updatable = false)
	private Message parent;
	private Date lastEdit;
	private String lastEditBy;

	public Message() { }

	public Message(long id) {
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public MessageType getMessageType() {
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
		return message;
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

	public MessageDTO toDto() {
		MessageDTO dto = new MessageDTO();
		dto.setCreationTimestamp(this.getCreationTimestamp());
		dto.setId(this.getId());
		dto.setLastEdit(this.getLastEdit());
		dto.setLastEditBy(this.getLastEditBy());
		dto.setMessage(this.getMessage());
		dto.setMessageType(this.getMessageType().toDto());
		if (dto.getParent() != null)
			dto.setParent(this.getParent().toDto());
		dto.setEhrId(this.getEhrId());
		return dto;
	}
}
