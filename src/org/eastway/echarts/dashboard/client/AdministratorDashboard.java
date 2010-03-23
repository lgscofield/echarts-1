package org.eastway.echarts.dashboard.client;

import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.events.OpenPatientEvent;
import org.eastway.echarts.client.events.OpenPatientEventHandler;
import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;
import org.eastway.echarts.client.view.AlertsView;
import org.eastway.echarts.client.view.PatientListView;
import org.eastway.echarts.client.view.TopPanelView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.CssResource;
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
	private static DashboardViewUiBinder uiBinder = GWT
			.create(DashboardViewUiBinder.class);

	interface DashboardViewUiBinder extends
			UiBinder<Widget, AdministratorDashboard> {
	}

	interface Style extends CssResource {
		String alerts();
	}

	@UiField Style style;
	@UiField DockLayoutPanel dockLayoutPanel;
	@UiField TabLayoutPanel tabLayoutPanel;
	@UiField ScrollPanel patientList;
	@UiField FlowPanel alertsPanel;
	@UiField TopPanelView top;

	private HandlerManager eventBus;

	public AdministratorDashboard(HandlerManager eventBus) {
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));
		PatientListPresenter plp = new PatientListPresenter(
				new PatientListView(), eventBus);
		plp.go(patientList);
		new TopPanelPresenter(top, eventBus);

		AlertsPresenter ap = new AlertsPresenter(new AlertsView(), Rpc.singleton(), eventBus);
		ap.go(alertsPanel);
		bind();
	}

	private void bind() {
		eventBus.addHandler(OpenPatientEvent.TYPE,
				new OpenPatientEventHandler() {
					public void onOpenPatient(
							OpenPatientEvent event) {
						openPatient(event.getId());
					}
				});
	}

	public void openPatient(String patientId) {
		final PatientTab tb = new PatientTab(eventBus, patientId);
		Label closeTab = new Label();
		closeTab.setTitle("Close");
		Label patientIdLabel = new Label(patientId);
		HorizontalPanel tabHeader = new HorizontalPanel();
		tabHeader.add(patientIdLabel);
		tabHeader.add(closeTab);
		closeTab.addStyleName("close-Tab");
		tabLayoutPanel.add(tb, tabHeader);
		closeTab.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer idx = tabLayoutPanel.getWidgetIndex(tb);
				if (tabLayoutPanel.remove(idx))
					tabLayoutPanel.selectTab(idx - 1);
			}
		});
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
}
