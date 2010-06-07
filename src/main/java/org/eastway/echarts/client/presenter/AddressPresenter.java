package org.eastway.echarts.client.presenter;

import java.util.List;

import org.eastway.echarts.client.AddressServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddressPresenter extends Presenter<AddressPresenter.Display> {

	public interface Display extends EchartsDisplay, Address {
		void nextRecord();
	}

	private AddressServicesAsync rpcServices;
	private EHR ehr;

	public AddressPresenter(Display display, HandlerManager eventBus, AddressServicesAsync rpcServices, EHR ehr) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
		this.ehr = ehr;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}

	private void fetchData() {
		AsyncCallback<List<Address>> callback = new AsyncCallback<List<Address>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(List<Address> addresses) {
				for (Address address : addresses) {
					display.setCaseNumber(address.getCaseNumber());
					display.nextRecord();
				}
			}
		};
		rpcServices.findByEhr(ehr, UserImpl.getSessionId(), callback);
	}
}
