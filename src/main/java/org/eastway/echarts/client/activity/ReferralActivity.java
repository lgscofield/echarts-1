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
package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.ReferralProxy;
import org.eastway.echarts.client.ui.ReferralView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ReferralActivity extends AbstractActivity implements ReferralView.Presenter {

	private ReferralView view;
	private String caseNumber;
	private EchartsRequestFactory requestFactory;
	private AcceptsOneWidget panel;

	public ReferralActivity(ReferralPlace place,
							EchartsRequestFactory requestFactory,
							ReferralView view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.view = view;
	}

	private void fetchData() {
		requestFactory.referralRequest().findReferral(caseNumber).fire(new Receiver<ReferralProxy>() {
			@Override
			public void onSuccess(ReferralProxy response) {
				if (response != null) {
					view.setValue(response);
					view.setError("");
					panel.setWidget(view);
				} else {
					handleFailure("No referral data found for case number: " + caseNumber);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		fetchData();
	}

	private void handleFailure(String message) {
		view.setValue(null);
		view.setError(message);
		panel.setWidget(view);
	}
}
