package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenIspEvent extends GwtEvent<OpenIspEventHandler> {
	public static Type<OpenIspEventHandler> TYPE = new Type<OpenIspEventHandler>();

	private String caseNumber;

	public OpenIspEvent(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	protected void dispatch(OpenIspEventHandler handler) {
		handler.onOpenIsp(this);
	}

	@Override
	public Type<OpenIspEventHandler> getAssociatedType() {
		return TYPE;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
