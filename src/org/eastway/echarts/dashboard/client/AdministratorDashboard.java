package org.eastway.echarts.dashboard.client;


import org.eastway.echarts.client.events.OpenPatientEvent;
import org.eastway.echarts.client.events.OpenPatientEventHandler;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.view.PatientListView;
import org.eastway.echarts.tab.client.PatientTabBody;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
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

public class AdministratorDashboard extends Composite {
	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	interface DashboardViewUiBinder extends UiBinder<Widget, AdministratorDashboard> {}

	@UiField DockLayoutPanel dockLayoutPanel;
	@UiField TabLayoutPanel tabLayoutPanel;
	@UiField FlowPanel alertsPanel;
	@UiField FlowPanel productivityPanel;
	@UiField ScrollPanel patientListPanel;
	@UiField FlowPanel topPanel;

	private HandlerManager eventBus;
	public AdministratorDashboard(HandlerManager eventBus) {
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));
		PatientListPresenter plp = new PatientListPresenter(new PatientListView(), eventBus);
		plp.go(patientListPanel);
		bind();
	}

	private void bind() {
		eventBus.addHandler(OpenPatientEvent.TYPE,
				new OpenPatientEventHandler() {
			public void onOpenPatient(OpenPatientEvent event) {
				openPatient(event.getId());
			}
		});
	}

	public void openPatient(String patientId) {
		PatientTabBody tb = new PatientTabBody(eventBus, patientId);
		Label closeTab = new Label();
		closeTab.setTitle("Close");
		Label patientIdLabel = new Label(patientId);
		HorizontalPanel tabHeader = new HorizontalPanel();
		tabHeader.add(patientIdLabel);
		tabHeader.add(closeTab);
		closeTab.addStyleName("close-Tab");
		tabLayoutPanel.add(tb, tabHeader);
//		tabLayoutPanel.setPatientTab(patientId,
//					patientTab.getDisplay().asWidget())
//						.addClickHandler(
//							new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				Integer idx = display.getIndex(
//					patientTab.getDisplay().asWidget());
//				if (display.removeTab(idx))
//					display.setSelectedTab(idx - 1);
//			}
//		});
	}

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
}
