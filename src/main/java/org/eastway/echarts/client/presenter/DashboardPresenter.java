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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ChangeCurrentEhrEventHandler;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.ViewProfileEvent;
import org.eastway.echarts.client.events.ViewTicklerEvent;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.GetProductivity;
import org.eastway.echarts.shared.GetProductivityResult;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerResult;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class DashboardPresenter implements Presenter, DashboardView.Presenter<LinkedHashMap<String, Long>> {

	private DashboardView<LinkedHashMap<String, Long>> view;
	private EventBus eventBus;
	private GetTicklerResult data;
	private CachingDispatchAsync dispatch;
	private GetTickler action;
	private String caseNumber;

	@Inject
	public DashboardPresenter(DashboardView<LinkedHashMap<String, Long>> view,
			EventBus eventBus, final CachingDispatchAsync dispatch, GetTickler action) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
	}

	private void bind() {
		eventBus.addHandler(ChangeCurrentEhrEvent.TYPE, new ChangeCurrentEhrEventHandler() {
			@Override
			public void onChangeCurrentEhr(ChangeCurrentEhrEvent event) {
				setCurrentEhrData(event.getEhr());
			}
		});
	}

	public DashboardView<LinkedHashMap<String, Long>> getDisplay() {
		return view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchData();
	}

	private void setCurrentEhrData(EHR ehr) {
		if (ehr == null) {
			view.showEhrStub(false);
			return;
		}
		Patient patient = ehr.getSubject();
		Demographics demographics = ehr.getDemographics();
		view.setName(patient.getName());
		view.setCaseStatus(patient.getCaseStatus().getDescriptor());

		Long age = (new Date().getTime() - demographics.getDob().getTime()) / (3600*24*365) / 1000;

		//view.setAge(age.toString());
		view.setDob(DateTimeFormat.getFormat("M/d/y").format(new Date(new Long(demographics.getDob().getTime()))).toString() + " (" + age.toString() + ")");
		view.setProvider(getProvider());
		view.setSsn(patient.getSsn());
		view.showEhrStub(true);
	}

	private String getProvider() {
		List<Tickler> assignments = data.getTicklers();
		for (Tickler assignment : assignments) {
			if (!assignment.getService().matches("S CS"))
				continue;
			else
				return assignment.getStaffName();
		}

		for (Tickler assignment : assignments)
			return assignment.getStaffName();
		return null;
	}

	private void fetchData() {
		getPatientList();
		getProductivityData();
	}

	private void setData(GetTicklerResult data) {
		this.data = data;
	}

	@Override
	public void onItemSelected(String row) {
		if (row != null) {
			eventBus.fireEvent(new OpenEhrEvent(caseNumber));
		}
	}

	@Override
	public void changeCurrentEhr(EHR ehr) {
			eventBus.fireEvent(new ChangeCurrentEhrEvent(ehr));
	}

	@Override
	public void openEhr(String text) {
		eventBus.fireEvent(new OpenEhrEvent(text.replaceAll("(.*) - .*", "$1")));
	}

	private void getProductivityData() {
		dispatch.execute(new GetProductivity(EchartsUser.sessionId, EchartsUser.staffId), new EchartsCallback<GetProductivityResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetProductivityResult result) {
				String color = null;
				if (result.getTotal().doubleValue() < result.getYellowNumber())
					color = "red";
				else if (result.getTotal().doubleValue() < result.getGreenNumber())
					color = "yellow";
				else
					color = "green";
				view.setProductivity(result.getTotal().toPlainString(), color);
				view.setBonusProjection(new Double(result.getGreenNumber()).toString());
			}
		});
	}

	public void getPatientList() {
		action.setStaffId(EchartsUser.staffId);
		action.setSessionId(EchartsUser.sessionId);
		dispatch.executeWithCache(action, new EchartsCallback<GetTicklerResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetTicklerResult result) {
				for (Tickler assignment : result.getTicklers())
					if (assignment != null)
						view.addPatientSearchData(new StringBuilder()
									.append(assignment.getCaseNumber())
									.append(" - ")
									.append(assignment.getName()).toString());
				setData(result);
			}
		});
	}

	@Override
	public void openTickler() {
		eventBus.fireEvent(new ViewTicklerEvent());
	}

	@Override
	public void logout() {
		eventBus.fireEvent(new LogoutEvent());
	}

	@Override
	public void openProfile() {
		eventBus.fireEvent(new ViewProfileEvent());
	}
}
