package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetMessagesResult implements Result {

	private List<MessageDTO> messages;
	private List<CodeDTO> types;

	GetMessagesResult () { }

	public GetMessagesResult(List<MessageDTO> messages, List<CodeDTO> types) {
		this.messages = messages;
		this.types = types;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public List<CodeDTO> getTypes() {
		return types;
	}
}
