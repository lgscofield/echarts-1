package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetMedications;

import com.google.gwt.event.shared.GwtEvent;

public class ViewMedicationsEvent extends GwtEvent<ViewMedicationsEventHandler> {

	public static Type<ViewMedicationsEventHandler> TYPE = new Type<ViewMedicationsEventHandler>();
	private EHRView<EHR> view;
	private GetMedications action;

	public ViewMedicationsEvent(String caseNumber, EHRView<EHR> view,
			GetMedications action) {
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewMedicationsEventHandler handler) {
		handler.onViewMedications(this);
	}

	@Override
	public Type<ViewMedicationsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetMedications getAction() {
		return action;
	}
}
