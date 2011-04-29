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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.UrlBuilder;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.request.UserRequest;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TicklerActivity extends AbstractActivity implements TicklerView.Presenter<Tickler> {
	private TicklerView<Tickler> view;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;
	private TicklerCalc ticklerCalc = new TicklerCalc();
	private String userName;

	public TicklerActivity(TicklerPlace place,
						   EchartsRequestFactory requestFactory,
						   List<ColumnDefinition<Tickler>> columnDefinitions,
						   PlaceController placeController,
						   TicklerView<Tickler> view) {
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.view = view;
		this.view.setColumnDefinitions(columnDefinitions);
		this.view.setPresenter(this);
		this.userName = place.getTicklerName();
	}

	public void fetchData() {
		UserRequest context = requestFactory.userRequest();
		context.findAssignments(EchartsUser.userName, userName)
			.to(new Receiver<List<AssignmentProxy>>() {
			@Override
			public void onSuccess(List<AssignmentProxy> response) {
				if (response != null && response.size() != 0) {
					view.setRowData(ticklerCalc.setDates(response));
					view.setNoteTimeliness(
							ticklerCalc.getNoDataCount().intValue(),
							ticklerCalc.getNoDataCount().divide(ticklerCalc.getTotalCount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue(),
							ticklerCalc.getOverdueCount().intValue(),
							ticklerCalc.getOverdueCount().divide(ticklerCalc.getTotalCount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue(),
							ticklerCalc.getUpToDateCount().intValue(),
							ticklerCalc.getUpToDateCount().divide(ticklerCalc.getTotalCount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue());
					panel.setWidget(view);
				}
			}
		});
		if (!userName.equals(EchartsUser.userName)) {
			context.findUser(userName).to(new Receiver<UserProxy>() {
				@Override
				public void onSuccess(UserProxy response) {
					if (response != null)
						view.setHeader(response.getStaffName());
				}
			});
		}
		context.fire();
	}

	private AcceptsOneWidget panel;

	public TicklerView<Tickler> getDisplay() {
		return view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		fetchData();
	}

	@Override
	public void goTo(Tickler tickler) {
		placeController.goTo(new PatientSummaryPlace(tickler.getCaseNumber()));
	}

	@Override
	public void openEhr(Tickler t) {
		Window.open(Window.Location.createUrlBuilder()
			.setHash("#patientsummary:" + t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openIsp(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/GandO.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openIndividualProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/102IPNEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openCpstNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/101CPSTEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openDoctorProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/104PharmEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openNurseProgressNote(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Forms/103PM-NPNEdit.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber()).buildString(), "_blank", "");
	}

	@Override
	public void openPrintablePatientSummary(Tickler t) {
		Window.open(new UrlBuilder()
				.setProtocol(Window.Location.getProtocol())
				.setHost(EchartsUser.dbServerUrl)
				.setPath("/echarts-asp/Client/demographicsprint.asp")
				.setParameter("staffid", EchartsUser.staffId)
				.setParameter("PATID", t.getCaseNumber())
				.buildString(), "_blank", "");
	}

	@Override
	public void openEditContact(Tickler t) {
		Window.open(new UrlBuilder()
			.setProtocol(Window.Location.getProtocol())
			.setHost(EchartsUser.dbServerUrl)
			.setPath("/echarts-asp/client/contactupdate.asp")
			.setParameter("PATID", t.getCaseNumber())
			.buildString(), "_blank", "");
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}
}
