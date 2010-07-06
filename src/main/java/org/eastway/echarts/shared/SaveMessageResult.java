package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class SaveMessageResult implements Result {

	private MessageDTO message;

	SaveMessageResult() { }

	public SaveMessageResult(MessageDTO message) {
		this.message = message;
	}

	public MessageDTO getMessage() {
		return message;
	}

}
