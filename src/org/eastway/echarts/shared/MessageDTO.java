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
package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * A model for patient messages
 * 
 * @author ihilt
 * 
 */
@SuppressWarnings("serial")
public class MessageDTO implements Serializable {

	private Date creationTimestamp;
	private long id;
	private Date lastEdit;
	private String lastEditBy;
	private String message;
	private long ehrId;
	private CodeDTO messageType;
	private MessageDTO parent;

	public MessageDTO() { }

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
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
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
	}

	public void setMessageType(CodeDTO messageType) {
		this.messageType = messageType;
	}

	public CodeDTO getMessageType() {
		return messageType;
	}

	public void setParent(MessageDTO parent) {
		this.parent = parent;
	}

	public MessageDTO getParent() {
		return parent;
	}
}
