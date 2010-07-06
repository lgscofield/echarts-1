package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetLinks;

import com.google.gwt.event.shared.GwtEvent;

public class ViewLinksEvent extends GwtEvent<ViewLinksEventHandler> {
	public static Type<ViewLinksEventHandler> TYPE = new Type<ViewLinksEventHandler>();

	private EHRView<EHR> view;
	private GetLinks action;

	private String caseNumber;

	public ViewLinksEvent(String caseNumber, EHRView<EHR> view, GetLinks action) {
		this.view = view;
		this.action = action;
		this.caseNumber = caseNumber;
	}

	@Override
	protected void dispatch(ViewLinksEventHandler handler) {
		handler.onViewLinks(this);
	}

	@Override
	public Type<ViewLinksEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetLinks getAction() {
		return action;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
