package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.event.shared.GwtEvent;

public class ViewReferralEvent extends GwtEvent<ViewReferralEventHandler> {
	public static Type<ViewReferralEventHandler> TYPE = new Type<ViewReferralEventHandler>();
	private long id;
	private EHRView<EHR> view;
	private GetReferral action;

	public ViewReferralEvent(long id, EHRView<EHR> view, GetReferral action) {
		this.id = id;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewReferralEventHandler handler) {
		handler.onViewReferral(this);
	}

	@Override
	public Type<ViewReferralEventHandler> getAssociatedType() {
		return TYPE;
	}

	public long getId() {
		return id;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetReferral getAction() {
		return action;
	}
}
