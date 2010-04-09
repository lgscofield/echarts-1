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

import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommunitySupportSpecialistDashboardView extends Composite implements CommunitySupportSpecialistDashboardPresenter.Display {
	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	interface DashboardViewUiBinder extends UiBinder<Widget, CommunitySupportSpecialistDashboardView> {}

	@UiField
	DockLayoutPanel dockLayoutPanel;
	@UiField
	TabLayoutPanel tabLayoutPanel;
	@UiField
	FlowPanel alertsPanel;
	@UiField
	FlowPanel productivityPanel;
	@UiField
	ScrollPanel patientListPanel;
	@UiField
	FlowPanel topPanel;

	public CommunitySupportSpecialistDashboardView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setAlerts(AlertsPresenter alerts) {
		alertsPanel.add(alerts.getDisplay().asWidget());
	}

	@Override
	public HasSelectionHandlers<Integer> getPanel() {
		return tabLayoutPanel;
	}

	@Override
	public void setPatientList(PatientListPresenter patientListPresenter) {
		patientListPanel.add(patientListPresenter.getDisplay().asWidget());
	}

	@Override
	public void setTopPanel(TopPanelPresenter topPanelPresenter) {
		topPanel.add(topPanelPresenter.getDisplay().asWidget());
	}

	@Override
	public HasClickHandlers setPatientTab(String patientId, Widget patientTab) {
		Label closeTab = new Label();
		closeTab.setTitle("Close");
		Label patientIdLabel = new Label(patientId);
		HorizontalPanel tabHeader = new HorizontalPanel();
		tabHeader.add(patientIdLabel);
		tabHeader.add(closeTab);
		closeTab.addStyleName("close-Tab");
		tabLayoutPanel.add(patientTab, tabHeader);
		return closeTab;
	}

	@Override
	public int getIndex(Widget tab) {
		return tabLayoutPanel.getWidgetIndex(tab);
	}

	@Override
	public void setSelectedTab(Integer i) {
		tabLayoutPanel.selectTab(i);
	}

	@Override
	public boolean removeTab(Integer i) {
		if (tabLayoutPanel.remove(i))
			return true;
		else
			return false;
	}
}
