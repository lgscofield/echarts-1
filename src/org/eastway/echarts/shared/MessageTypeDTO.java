package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MessageTypeDTO implements Serializable {
	private int id;
	private String type;

	public MessageTypeDTO() { }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
