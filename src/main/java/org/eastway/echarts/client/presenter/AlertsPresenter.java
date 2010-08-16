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

import java.util.Vector;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.rpc.HandleRpcException;
//import org.eastway.echarts.client.RpcServicesAsync;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AlertsPresenter implements Presenter {

	public interface Display extends EchartsDisplay {
		HasClickHandlers getAlerts();

		void setData(Vector<String> data);
	}

	//private RpcServicesAsync rpcServices;
	private Display display;

//	public AlertsPresenter(Display display, RpcServicesAsync rpcServices, HandlerManager eventBus) {
//		this.rpcServices = rpcServices;
//		this.display = display;
//	}

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
		//rpcServices.getAlerts(EchartsUser.sessionId, callback);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchAlerts();
	}
}
