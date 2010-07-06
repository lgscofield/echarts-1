package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetMessages;

import com.google.gwt.event.shared.GwtEvent;

public class ViewMessagesEvent extends GwtEvent<ViewMessagesEventHandler> {
	public static Type<ViewMessagesEventHandler> TYPE = new Type<ViewMessagesEventHandler>();
	private EHRView<EHR> view;
	private String caseNumber;
	private GetMessages action;

	public ViewMessagesEvent(String caseNumber, EHRView<EHR> view, GetMessages action) {
		this.view = view;
		this.caseNumber = caseNumber;
		this.action = action;
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

	public GetMessages getAction() {
		return action;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
