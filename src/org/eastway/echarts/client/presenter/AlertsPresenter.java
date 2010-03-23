package org.eastway.echarts.client.presenter;

import java.util.Vector;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AlertsPresenter extends Presenter<AlertsPresenter.Display> {

	public interface Display extends EchartsDisplay {
		HasClickHandlers getAlerts();

		void setData(Vector<String> data);
	}

	private RpcServicesAsync rpcServices;

	public AlertsPresenter(Display display, RpcServicesAsync rpcServices, HandlerManager eventBus) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
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
		rpcServices.getAlerts(UserImpl.getSessionId(), callback);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchAlerts();
	}
}
