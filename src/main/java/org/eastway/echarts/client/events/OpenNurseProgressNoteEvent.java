package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenNurseProgressNoteEvent extends
		GwtEvent<OpenNurseProgressNoteEventHandler> {

	public static final Type<OpenNurseProgressNoteEventHandler> TYPE = new Type<OpenNurseProgressNoteEventHandler>();
	private String caseNumber;

	public OpenNurseProgressNoteEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
	}

	@Override
	protected void dispatch(OpenNurseProgressNoteEventHandler handler) {
		handler.onOpenNurseProgressNote(this);
	}

	@Override
	public Type<OpenNurseProgressNoteEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
