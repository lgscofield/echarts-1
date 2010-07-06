package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetAppointments;

import com.google.gwt.event.shared.GwtEvent;

public class ViewAppointmentsEvent extends GwtEvent<ViewAppointmentsEventHandler> {
	public static Type<ViewAppointmentsEventHandler> TYPE = new Type<ViewAppointmentsEventHandler>();
	private EHRView<EHR> view;
	private String caseNumber;
	private GetAppointments action;

	public ViewAppointmentsEvent(String caseNumber, EHRView<EHR> view, GetAppointments action) {
		this.view = view;
		this.caseNumber = caseNumber;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewAppointmentsEventHandler handler) {
		handler.onViewAppointments(this);
	}

	@Override
	public Type<ViewAppointmentsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public GetAppointments getAction() {
		return action;
	}
}
