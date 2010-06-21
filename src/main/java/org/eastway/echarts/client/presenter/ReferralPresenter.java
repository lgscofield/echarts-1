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

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.ReferralServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Referral;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class ReferralPresenter extends Presenter<ReferralPresenter.Display> {

	public interface Display extends EchartsDisplay, Referral { }

	private ReferralServicesAsync rpcServices;
	private EHR ehr;

	public ReferralPresenter(Display display, HandlerManager eventBus, ReferralServicesAsync rpcServices, EHR ehr) {
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
		AsyncCallback<Referral> callback = new AsyncCallback<Referral>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Referral referral) {
				display.setUCI(referral.getUCI());
			}
		};
		rpcServices.findByEhr(ehr.getId(), UserImpl.getSessionId(), callback);
	}
}
