package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewMessagesEvent extends GwtEvent<ViewMessagesEventHandler> {
	public static Type<ViewMessagesEventHandler> TYPE = new Type<ViewMessagesEventHandler>();
	private EHRView<EHR> view;
	private long ehrId;

	public ViewMessagesEvent(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		this.view = view;
	}

	@Override
	protected void dispatch(ViewMessagesEventHandler handler) {
		handler.onViewMessages(this);
	}

	@Override
	public Type<ViewMessagesEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public long getId() {
		return ehrId;
	}
}
