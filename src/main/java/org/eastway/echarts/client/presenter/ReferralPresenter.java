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

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.ReferralProxy;
import org.eastway.echarts.client.view.ReferralView;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class ReferralPresenter implements Presenter, ReferralView.Presenter<ReferralProxy> {

	private ReferralView<ReferralProxy> view;
	private EchartsRequestFactory requestFactory;
	private String caseNumber;

	public ReferralPresenter(ReferralView<ReferralProxy> view, List<ColumnDefinition<ReferralProxy>> columnDefinitions, EchartsRequestFactory requestFactory, String caseNumber) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.caseNumber = caseNumber;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		requestFactory.referralRequest().findReferral(caseNumber).fire(new Receiver<ReferralProxy>() {
			@Override
			public void onSuccess(ReferralProxy response) {
				if (response != null)
					view.setRowData(response);
			}
		});
	}
}
