package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditPatientSummaryPresenter extends Presenter<EditPatientSummaryPresenter.Display> {

	public interface Display extends EchartsDisplay { }

	public EditPatientSummaryPresenter(
			Display display,
			HandlerManager eventBus, RpcServicesAsync singleton,
			Patient patient) {
		super(display, eventBus);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
