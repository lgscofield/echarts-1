package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class SavedPatientMessageEvent extends GwtEvent<SavedPatientMessageEventHandler> {
	public static Type<SavedPatientMessageEventHandler> TYPE = new Type<SavedPatientMessageEventHandler>();

	public SavedPatientMessageEvent() { }

	@Override
	protected void dispatch(SavedPatientMessageEventHandler handler) {
		handler.onSavedPatientMessage(this);
	}

	@Override
	public Type<SavedPatientMessageEventHandler> getAssociatedType() {
		return TYPE;
	}
}
