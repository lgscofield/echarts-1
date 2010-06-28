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

import java.util.LinkedHashSet;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class LinkPresenter extends Presenter<LinkPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	private String patientid;
	private LinkedHashSet<String[]> data;
	private RpcServicesAsync rpcServices;

	public LinkPresenter(Display display, HandlerManager eventBus,
			RpcServicesAsync rpcServices, String patientid) {
		super(display, eventBus);
		this.patientid = patientid;
		this.rpcServices = rpcServices;
	}

	private void fetchData() {
		AsyncCallback<LinkedHashSet<String[]>> callback = new AsyncCallback<LinkedHashSet<String[]>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashSet<String[]> data) {
				setData(data);
				display.setData(getData());
			}
		};
		rpcServices.getFormsList(Cookies.getCookie("sessionId"), patientid, callback);
	}

	public LinkedHashSet<String[]> getData() {
		return data;
	}

	public void setData(LinkedHashSet<String[]> d) {
		this.data = d;
		for (String[] s : data) {
			s[1] += "?staffid=" + Cookies.getCookie("staff_id") + "&PATID=" + patientid;
		}
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}
}
