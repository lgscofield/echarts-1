package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenEhrEvent extends GwtEvent<OpenEhrEventHandler> {
	public static Type<OpenEhrEventHandler> TYPE = new Type<OpenEhrEventHandler>();
	private long ehrId;

	public OpenEhrEvent(long ehrId) {
		this.ehrId = ehrId;
	}

	@Override
	protected void dispatch(OpenEhrEventHandler handler) {
		handler.onOpenPatient(this);
	}

	@Override
	public Type<OpenEhrEventHandler> getAssociatedType() {
		return TYPE;
	}

	public long getId() {
		return ehrId;
	}
}
