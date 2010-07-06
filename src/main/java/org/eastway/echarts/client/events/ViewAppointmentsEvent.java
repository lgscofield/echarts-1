package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewAppointmentsEvent extends GwtEvent<ViewAppointmentsEventHandler> {
	public static Type<ViewAppointmentsEventHandler> TYPE = new Type<ViewAppointmentsEventHandler>();
	private EHRView<EHR> view;
	private String caseNumber;

	public ViewAppointmentsEvent(String caseNumber, EHRView<EHR> view) {
		this.view = view;
		this.caseNumber = caseNumber;
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
}
