package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenDoctorProgressNoteEvent extends
		GwtEvent<OpenDoctorProgressNoteEventHandler> {

	public static final Type<OpenDoctorProgressNoteEventHandler> TYPE = new Type<OpenDoctorProgressNoteEventHandler>();
	private String caseNumber;

	public OpenDoctorProgressNoteEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
	}

	@Override
	protected void dispatch(OpenDoctorProgressNoteEventHandler handler) {
		handler.onOpenDoctorProgressNote(this);
	}

	@Override
	public Type<OpenDoctorProgressNoteEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
