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
package org.eastway.echarts.dashboard.client;

import org.eastway.echarts.client.PatientServices;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;
import org.eastway.echarts.client.view.AlertsView;
import org.eastway.echarts.client.view.PatientListView;
import org.eastway.echarts.client.view.TopPanelView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CommunitySupportSpecialistDashboardPresenter extends Presenter<CommunitySupportSpecialistDashboardPresenter.Display> {

	public interface Display extends EchartsDisplay {
		HasSelectionHandlers<Integer> getPanel();

		void setAlerts(AlertsPresenter alerts);

		void setPatientList(PatientListPresenter patientListPresenter);

		void setTopPanel(TopPanelPresenter topPanelPresenter);

		HasClickHandlers setPatientTab(String patientId, Widget patientTab);

		int getIndex(Widget tab);

		void setSelectedTab(Integer i);

		boolean removeTab(Integer i);
	}
	private PatientServicesAsync patientServices = GWT.<PatientServicesAsync>create(PatientServices.class);

	public CommunitySupportSpecialistDashboardPresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		bind();
		fetchWidgets();
	}

	private void bind() {
	}

	private void fetchWidgets() {
		fetchAlerts();
		fetchPatientList();
		fetchTopPanel();
	}

	private void fetchTopPanel() {
		TopPanelPresenter topPanel = new TopPanelPresenter(new TopPanelView(), eventBus, patientServices);
		display.setTopPanel(topPanel);
	}

	private void fetchAlerts() {
		AlertsPresenter alerts = new AlertsPresenter(new AlertsView(), null, eventBus);
		display.setAlerts(alerts);
	}

	public void fetchPatientList() {
		PatientListPresenter patientList = new PatientListPresenter(new PatientListView(), eventBus, patientServices);
		display.setPatientList(patientList);
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
