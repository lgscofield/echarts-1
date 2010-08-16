package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ViewProfileEvent extends GwtEvent<ViewProfileEventHandler> {

	public final static Type<ViewProfileEventHandler> TYPE = new Type<ViewProfileEventHandler>();

	@Override
	protected void dispatch(ViewProfileEventHandler handler) {
		handler.onViewProfile(this);
	}

	@Override
	public Type<ViewProfileEventHandler> getAssociatedType() {
		return TYPE;
	}
}
