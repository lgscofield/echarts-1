package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetAddresses;

import com.google.gwt.event.shared.GwtEvent;

public class ViewAddressesEvent extends GwtEvent<ViewAddressesEventHandler> {
	public static Type<ViewAddressesEventHandler> TYPE = new Type<ViewAddressesEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;
	private GetAddresses action;

	public ViewAddressesEvent(String caseNumber, EHRView<EHR> view,
			GetAddresses action) {
		this.caseNumber = caseNumber;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewAddressesEventHandler handler) {
		handler.onViewAddresses(this);
	}

	@Override
	public Type<ViewAddressesEventHandler> getAssociatedType() {
		return TYPE ;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetAddresses getAction() {
		return action;
	}
}
