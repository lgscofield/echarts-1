package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenIndividualProgressNoteEvent extends GwtEvent<OpenIndividualProgressNoteEventHandler> {

	public static final Type<OpenIndividualProgressNoteEventHandler> TYPE = new Type<OpenIndividualProgressNoteEventHandler>();
	private String caseNumber;

	public OpenIndividualProgressNoteEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
	}

	@Override
	protected void dispatch(OpenIndividualProgressNoteEventHandler handler) {
		handler.onOpenIndividualProgressNote(this);
	}

	@Override
	public Type<OpenIndividualProgressNoteEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
