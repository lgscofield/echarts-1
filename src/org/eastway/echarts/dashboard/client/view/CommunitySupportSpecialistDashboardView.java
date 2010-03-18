package org.eastway.echarts.dashboard.client.view;

import org.eastway.echarts.client.ProductivityChart;
import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;
import org.eastway.echarts.dashboard.client.presenter.CommunitySupportSpecialistDashboardPresenter;

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

	private ProductivityChart gchart = new ProductivityChart();

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
		productivityPanel.add(gchart);
		gchart.update();
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

	@Override
	public void setChartVisibility(boolean v) {
		gchart.setVisible(v);
	}
}
