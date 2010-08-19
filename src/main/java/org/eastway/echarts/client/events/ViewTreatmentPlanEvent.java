package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ViewTreatmentPlanEvent extends GwtEvent<ViewTreatmentPlanEventHandler> {
	public static final Type<ViewTreatmentPlanEventHandler> TYPE = new Type<ViewTreatmentPlanEventHandler>();
	private String caseNumber;

	public ViewTreatmentPlanEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
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
}
