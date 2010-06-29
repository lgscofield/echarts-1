/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.presenter;

import java.util.List;

import org.eastway.echarts.client.AddressServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddressPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Address {
		void nextRecord();
	}

	private AddressServicesAsync rpcServices;
	private EHR ehr;
	private Display display;

	public AddressPresenter(Display display, HandlerManager eventBus, AddressServicesAsync rpcServices, EHR ehr) {
		this.rpcServices = rpcServices;
		this.ehr = ehr;
		this.display = display;
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
		rpcServices.findByCaseNumber(ehr.getSubject().getCaseNumber(), Cookies.getCookie("sessionId"), callback);
	}
}
