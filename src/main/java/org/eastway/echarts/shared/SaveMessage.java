package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class SaveMessage implements Action<SaveMessageResult> {

	private String sessionId;
	private MessageDTO message;

	SaveMessage() { }

	public SaveMessage(String sessionId, MessageDTO message) {
		this.sessionId = sessionId;
		this.message = message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public MessageDTO getMessage() {
		return message;
	}
}
