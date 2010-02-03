package org.eastway.echarts.shared;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A structure for passing patient messages between client and server.
 * 
 * @author ihilt
 * 
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	public Integer ID;
	public String PATID;
	public String MessageType;
	public Timestamp MessageDate;
	public String Message;
	public Integer ParentId;
	public Timestamp LastEdit;
	public String LastEditBy;

	public Message() {}

	/**
	 * This add method is used by the client to send a patient message to
	 * the server.
	 * 
	 * @param PATID
	 *                The patient's ID
	 * @param MessageType
	 *                The message type
	 * @param Message
	 *                The body of the message
	 * @param LastEditBy
	 *                The ID of the last user to edit the message
	 */
	public void add(String PATID, String MessageType, String Message,
			String LastEditBy) {
		this.PATID = PATID;
		this.MessageType = MessageType;
		this.Message = Message;
		this.LastEditBy = LastEditBy;
	}

	/**
	 * This add method is used by the server to send patient messages
	 * encapsulated in Messages to the client.
	 * 
	 * @param ID
	 *                The unique ID of this message in the database
	 * @param PATID
	 *                PATID The patient's ID
	 * @param MessageType
	 *                MessageType The message type
	 * @param MessageDate
	 *                The date this message was created
	 * @param Message
	 *                The body of the message
	 * @param ParentId
	 *                If this message is a response, the ParentId is the
	 *                message ID this message responded to
	 * @param LastEdit
	 *                The date this message was last edited
	 * @param LastEditBy
	 *                The ID of the last user to edit the message
	 */
	public void add(Integer ID, String PATID, String MessageType,
			Timestamp MessageDate, String Message, int ParentId, String LastEditBy) {
		this.ID = ID;
		this.PATID = PATID;
		this.MessageType = MessageType;
		this.MessageDate = MessageDate;
		this.Message = Message;
		this.ParentId = ParentId;
		this.LastEditBy = LastEditBy;
	}
}
