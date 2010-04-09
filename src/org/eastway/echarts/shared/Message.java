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

/**
 * A model for patient messages
 * 
 * @author ihilt
 * 
 */
@SuppressWarnings("serial")
public class Message implements Serializable {
	private Integer id;
	private String patId;
	private String messageType;
	private String message;
	private Integer parentId;
	private String lastModified;
	private long creationDate;
	private String lastModifiedBy;

	public Message() {}

	/**
	 * Returns the unique ID of this message in the database
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Returns the patient's id
	 */
	public String getPatId() {
		return patId;
	}

	/**
	 * Sets the patient id
	 */
	public void setPatId(String patId) {
		this.patId = patId;
	}

	/**
	 * Returns the message type
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * Sets the message type
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * Returns the time this message was created
	 */
	public long getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the time the message was created
	 */
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Returns the body of the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the body of the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * If this message is a response, the parentId is the message id this
	 * message responded to
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Returns the date this message was last modified
	 */
	public String getLastModified() {
		return lastModified;
	}

	/**
	 * Sets the timestamp this message was last modified
	 * @param lastModified
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Returns the last user that modified this message
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * Sets the message unique id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the parent id
	 * @param parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * Sets lastModifiedBy to the last user that modified this message
	 * @param lastEditBy
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
