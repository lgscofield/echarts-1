package org.eastway.echarts.client.events;

import org.eastway.echarts.shared.GetTickler;

import com.google.gwt.event.shared.GwtEvent;

public class ViewTicklerEvent extends GwtEvent<ViewTicklerEventHandler> {

	public static final Type<ViewTicklerEventHandler> TYPE = new Type<ViewTicklerEventHandler>();
	private GetTickler action;

	public ViewTicklerEvent(GetTickler action) {
		this.action = action;
	}

	@Override
	protected void dispatch(ViewTicklerEventHandler handler) {
		handler.onOpenTickler(this);
	}

	@Override
	public Type<ViewTicklerEventHandler> getAssociatedType() {
		return TYPE;
	}

	public GetTickler getAction() {
		return action;
	}
}
