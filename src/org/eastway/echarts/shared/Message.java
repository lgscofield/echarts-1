package org.eastway.echarts.shared;

import java.io.Serializable;
import java.sql.Timestamp;

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
	private Timestamp messageDate;
	private String message;
	private Integer parentId;
	private Timestamp lastEdit;
	private String lastEditBy;

	public Message() {}

	/**
	 * This add method is used by the client to send a patient message to
	 * the server.
	 */
	public void add(String patId, String messageType, String message) {
		this.patId = patId;
		this.messageType = messageType;
		this.message = message;
	}

	/**
	 * This add method is used by the server to send patient messages
	 * encapsulated in Messages to the client.
	 */
	public void add(Integer id, String patId, String messageType,
			Timestamp messageDate, String message,
			Integer parentId, String lastEditBy) {
		this.id = id;
		this.patId = patId;
		this.messageType = messageType;
		this.messageDate = messageDate;
		this.message = message;
		this.parentId = parentId;
		this.lastEditBy = lastEditBy;
	}

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
	 * Returns the message type
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * Returns the date this message was created
	 */
	public Timestamp getMessageDate() {
		return messageDate;
	}

	/**
	 * Returns the body of the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * If this message is a response, the parentId is the message id this
	 * message responded to
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Returns the date this message was last edited
	 */
	public Timestamp getLastEdit() {
		return lastEdit;
	}

	/**
	 * Returns the last staff id used to edit this message
	 */
	public String getLastEditBy() {
		return lastEditBy;
	}
}
