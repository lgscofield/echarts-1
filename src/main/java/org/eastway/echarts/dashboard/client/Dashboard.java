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

import java.util.Date;
import java.util.LinkedHashMap;

import org.eastway.echarts.client.EHRServices;
import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServices;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.RpcServices;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenEhrEventHandler;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;
import org.eastway.echarts.client.view.PatientListViewImpl;
import org.eastway.echarts.client.view.TopPanelView;
import org.eastway.echarts.shared.EHR;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class Dashboard extends Composite {
	private static DashboardViewUiBinder uiBinder = GWT
			.create(DashboardViewUiBinder.class);

	interface DashboardViewUiBinder extends
			UiBinder<Widget, Dashboard> {
	}

	interface Style extends CssResource {
		String alerts();
		String green();
		String yellow();
		String red();
	}

	//@UiField Style style;
	@UiField DockLayoutPanel dockLayoutPanel;
	@UiField TabLayoutPanel tabLayoutPanel;
	@UiField ScrollPanel patientListPanel;
	//@UiField FlowPanel alertsPanel;
	@UiField TopPanelView top;
	@UiField FlowPanel scheduler;
	//@UiField SpanElement productivity;
	//@UiField Anchor gmhIntake;

	private RpcServicesAsync rpcServices = GWT.<RpcServicesAsync>create(RpcServices.class);
	private EHRServicesAsync ehrServices = GWT.<EHRServicesAsync>create(EHRServices.class);
	private PatientServicesAsync patientServices = GWT.<PatientServicesAsync>create(PatientServices.class);
	private HandlerManager eventBus;

	public Dashboard(HandlerManager eventBus) {
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));
		new TopPanelPresenter(top, eventBus, patientServices);
		//AlertsPresenter ap = new AlertsPresenter(new AlertsView(), Rpc.singleton(), eventBus);
		//ap.go(alertsPanel);
		//setProductivity("92");
		setScheduler(scheduler);
		bind();
		PatientListPresenter plp = new PatientListPresenter(
				new PatientListViewImpl<LinkedHashMap<String, Long>>(), eventBus, patientServices);
		plp.go(patientListPanel);
	}

//	private void setProductivity(String credit) {
//		productivity.setInnerText(credit);
//		double cred = new Double(credit);
//
//		if (cred >= 92)
//			productivity.getParentElement().addClassName(style.green());
//		else if (cred >= 87)
//			productivity.getParentElement().addClassName(style.yellow());
//		else
//			productivity.getParentElement().addClassName(style.red());
//	}

	private void setScheduler(HasWidgets container) {
		Calendar calendar = new Calendar();
		calendar.setDate(new Date()); //calendar date, not required
		calendar.setDays(1); //number of days displayed at a time, not required
		calendar.setWidth("100%");
		calendar.setHeight("400px");
		container.clear();
		container.add(calendar);
	}

	private void bind() {
		eventBus.addHandler(OpenEhrEvent.TYPE,
				new OpenEhrEventHandler() {
					public void onOpenEhr(
							OpenEhrEvent event) {
						openEhr(event.getId());
					}
				});

//		gmhIntake.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				openEditPatient();
//			}
//		});
		tabLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				Widget w = tabLayoutPanel.getWidget(event.getSelectedItem());
				if (w instanceof EHRTab)
					eventBus.fireEvent(new ChangeCurrentEhrEvent(((EHRTab) w).getEhr()));
				else
					eventBus.fireEvent(new ChangeCurrentEhrEvent(null));
			}
		});
	}

	private void addTab(final Widget child, String tab) {
		Label closeTab = new Label();
		HorizontalPanel tabHeader = new HorizontalPanel();
		closeTab.setTitle("Close");
		closeTab.addStyleName("close-Tab");
		closeTab.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer idx = tabLayoutPanel.getWidgetIndex(child);
				if (tabLayoutPanel.remove(idx))
					tabLayoutPanel.selectTab(idx - 1);
			}
		});
		tabHeader.add(new Label(tab));
		tabHeader.add(closeTab);
		tabLayoutPanel.add(child, tabHeader);
		setSelectedTab(getIndex(child));
	}

//	private void openEditPatient() {
//		AddEhrPresenter epp = new AddEhrPresenter(new AddEhrView(), eventBus, ehrServices);
//		epp.go();
//		addTab(epp.getDisplay().asWidget(), "New Chart");
//	}

	public void openEhr(long ehrId) {
		fetchEhr(ehrId);
	}

	public HasClickHandlers setPatientTab(String patientId,
			Widget patientTab) {
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

	public int getIndex(Widget tab) {
		return tabLayoutPanel.getWidgetIndex(tab);
	}

	public void setSelectedTab(Integer i) {
		tabLayoutPanel.selectTab(i);
	}

	public boolean removeTab(Integer i) {
		if (tabLayoutPanel.remove(i))
			return true;
		else
			return false;
	}

	private void fetchEhr(long ehrId) {
		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR ehr) {
				final EHRTab tb = new EHRTab(eventBus, rpcServices, ehr);
				addTab(tb, ehr.getSubject().getName());
			}
		};
		ehrServices.getEhr(ehrId, Cookies.getCookie("sessionId"),
				callback);
	}
}
