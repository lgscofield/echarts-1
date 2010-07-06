package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetContactsResult implements Result {

	private List<Contact> contacts;

	GetContactsResult() { }

	public GetContactsResult(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Contact> getContacts() {
		return contacts;
	}
}