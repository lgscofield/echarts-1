package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenPatientEvent extends GwtEvent<OpenPatientEventHandler> {
	public static Type<OpenPatientEventHandler> TYPE = new Type<OpenPatientEventHandler>();
	private String patientId;

	public OpenPatientEvent(String patientId) {
		this.patientId = patientId;
	}

	@Override
	protected void dispatch(OpenPatientEventHandler handler) {
		handler.onOpenPatient(this);
	}

	@Override
	public Type<OpenPatientEventHandler> getAssociatedType() {
		return TYPE;
	}

	public String getId() {
		return patientId;
	}
}
