package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetContacts;

import com.google.gwt.event.shared.GwtEvent;

public class ViewContactsEvent extends GwtEvent<ViewContactsEventHandler> {
	public static Type<ViewContactsEventHandler> TYPE = new Type<ViewContactsEventHandler>();
	private EHRView<EHR> view;
	private GetContacts action;

	public ViewContactsEvent(String caseNumber, EHRView<EHR> view,
			GetContacts action) {
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewContactsEventHandler handler) {
		handler.onViewContacts(this);
	}

	@Override
	public Type<ViewContactsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetContacts getAction() {
		return action;
	}
}
