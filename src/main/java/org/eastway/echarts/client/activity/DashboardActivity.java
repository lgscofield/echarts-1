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

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.EhrRequest;
import org.eastway.echarts.client.request.ProductivityProxy;
import org.eastway.echarts.client.ui.DashboardView;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DashboardActivity extends AbstractActivity implements DashboardView.Presenter<LinkedHashMap<String, Long>> {

	class EHRFetcher {
		EHRProxy fetchedEHR;
		List<AssignmentProxy> fetchedAssignments;

		void Run(final EhrRequest ehrRequest, final AssignmentRequest assignmentRequest, final String caseNumber, final Receiver<EHRFetcher> callback) {
			ehrRequest.findEHRByCaseNumber(caseNumber)
				.with("patient")
				.with("patient.caseStatus")
				.with("demographics")
				.fire(
					new Receiver<EHRProxy>() {
						@Override
						public void onSuccess(EHRProxy response) {
							if (response != null)
								fetchedEHR = response;
								assignmentRequest.findAssignmentsByCaseNumber(caseNumber).fire(
										new Receiver<List<AssignmentProxy>>() {
											@Override
											public void onSuccess(List<AssignmentProxy> response) {
												fetchedAssignments = response;
												if (fetchedAssignments != null)
													callback.onSuccess(EHRFetcher.this);
											}
										});
						}

						@Override
						public void onFailure(ServerFailure error) {
							com.google.gwt.user.client.Window.alert(error.getMessage());
						}
					});
			
		}
	}

	private DashboardView<LinkedHashMap<String, Long>> view;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;

	public DashboardActivity(DashboardPlace place,
							 PlaceController placeController,
							 DashboardView<LinkedHashMap<String, Long>> view,
							 EchartsRequestFactory requestFactory) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.view.setPresenter(this);
	}

	public DashboardView<LinkedHashMap<String, Long>> getDisplay() {
		return view;
	}

	private void fetchData() {
		getProductivityData();
	}

	private void getProductivityData() {
		requestFactory.productivityRequest().findProductivityByStaffId(EchartsUser.staffId).fire(new Receiver<ProductivityProxy>() {
			@Override
			public void onSuccess(ProductivityProxy response) {
				if (response == null)
					return;
				String color = null;
				if (response.getTotal() < response.getYellowNumber())
					color = "red";
				else if (response.getTotal() < response.getGreenNumber())
					color = "yellow";
				else
					color = "green";
				view.setProductivity(response.getTotal().toString(), color);
				view.setBonusProjection(response.getGreenNumber().toString());
			}
		});
	}

	@Override
	public void openTickler() {
		placeController.goTo(new TicklerPlace());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		fetchData();
		if (Cookies.getCookie("first_login") != null)
			view.isFirstLogin();
	}
}
