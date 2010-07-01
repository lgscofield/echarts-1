package org.eastway.echarts.client.events;

import org.eastway.echarts.client.view.EHRView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewDemographicsEvent extends GwtEvent<ViewDemographicsEventHandler> {
	public static Type<ViewDemographicsEventHandler> TYPE = new Type<ViewDemographicsEventHandler>();
	private long ehrId;
	private EHRView<EHR> view;

	public ViewDemographicsEvent(long ehrId, EHRView<EHR> view) {
		this.ehrId = ehrId;
		this.view = view;
	}

	@Override
	protected void dispatch(ViewDemographicsEventHandler handler) {
		handler.onViewDemographics(this);
	}

	@Override
	public Type<ViewDemographicsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public long getId() {
		return ehrId;
	}

	public EHRView<EHR> getView() {
		return view;
	}
}
