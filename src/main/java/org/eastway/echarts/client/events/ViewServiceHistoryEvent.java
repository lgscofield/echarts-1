package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewServiceHistoryEvent extends GwtEvent<ViewServiceHistoryEventHandler> {

	public static final Type<ViewServiceHistoryEventHandler> TYPE = new Type<ViewServiceHistoryEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;

	public ViewServiceHistoryEvent(String caseNumber, EHRView<EHR> view) {
		this.setCaseNumber(caseNumber);
		this.setView(view);
	}

	@Override
	protected void dispatch(ViewServiceHistoryEventHandler handler) {
		handler.onViewServiceHistory(this);
	}

	@Override
	public Type<ViewServiceHistoryEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setView(EHRView<EHR> view) {
		this.view = view;
	}

	public EHRView<EHR> getView() {
		return view;
	}

}
