package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetDiagnoses;

import com.google.gwt.event.shared.GwtEvent;

public class ViewDiagnosesEvent extends GwtEvent<ViewDiagnosesEventHandler> {
	public static Type<ViewDiagnosesEventHandler> TYPE = new Type<ViewDiagnosesEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;
	private GetDiagnoses action;

	public ViewDiagnosesEvent(String caseNumber, EHRView<EHR> view,
			GetDiagnoses action) {
		this.caseNumber = caseNumber;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewDiagnosesEventHandler handler) {
		handler.onViewDiagnoses(this);
	}

	@Override
	public Type<ViewDiagnosesEventHandler> getAssociatedType() {
		return TYPE;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetDiagnoses getAction() {
		return action;
	}

}
