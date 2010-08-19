package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenCpstNoteEvent extends GwtEvent<OpenCpstNoteEventHandler> {

	public static final Type<OpenCpstNoteEventHandler> TYPE = new Type<OpenCpstNoteEventHandler>();
	private String caseNumber;

	public OpenCpstNoteEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
	}

	@Override
	protected void dispatch(OpenCpstNoteEventHandler handler) {
		handler.onOpenCpstNote(this);
	}

	@Override
	public Type<OpenCpstNoteEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
