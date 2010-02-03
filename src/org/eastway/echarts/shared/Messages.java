package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Messages implements Serializable {
	private Vector<Message> msgs = new Vector<Message>();

	public Messages() {}

	public void add(Message m) {
		this.msgs.add(m);
	}

	public Message get(int idx) {
		if (idx >= this.msgs.size())
			return null;
		else
			return this.msgs.get(idx);
	}

	public int count() {
		return this.msgs.size();
	}
}
