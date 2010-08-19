package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewTreatmentPlanEvent extends GwtEvent<ViewTreatmentPlanEventHandler> {
	public static final Type<ViewTreatmentPlanEventHandler> TYPE = new Type<ViewTreatmentPlanEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;

	public ViewTreatmentPlanEvent(String caseNumber, EHRView<EHR> view) {
		this.setCaseNumber(caseNumber);
		this.setView(view);
	}

	@Override
	protected void dispatch(ViewTreatmentPlanEventHandler handler) {
		handler.onViewTreatmentPlan(this);
	}

	@Override
	public Type<ViewTreatmentPlanEventHandler> getAssociatedType() {
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
