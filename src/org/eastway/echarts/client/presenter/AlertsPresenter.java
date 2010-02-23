package org.eastway.echarts.client.presenter;

import java.util.Vector;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.UserImpl;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AlertsPresenter extends EchartsPresenter<AlertsPresenter.Display> {

	public interface Display extends EchartsDisplay {
		HasClickHandlers getAlerts();

		void setData(Vector<String> data);
	}

	private final PatientServicesAsync patientSvc;

	public AlertsPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc) {
		super(display, eventBus);
		this.patientSvc = patientSvc;
		fetchAlerts();
	}

	public void fetchAlerts() {
		AsyncCallback<Vector<String>> callback = new AsyncCallback<Vector<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Vector<String> data) {
				display.setData(data);
			}
		};
		patientSvc.getAlerts(UserImpl.getSessionId(), callback);
	}
}
