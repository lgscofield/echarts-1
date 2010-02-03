package org.eastway.echarts.client.view;

import org.eastway.echarts.client.ProductivityChart;
import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.PatientTabPresenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DashboardView extends Composite implements DashboardPresenter.Display {
	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	interface DashboardViewUiBinder extends UiBinder<Widget, DashboardView> {}

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

	public DashboardView() {
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
	public TabLayoutPanel getTabPanel() {
		return tabLayoutPanel;
	}

	@Override
	public ProductivityChart getChart() {
		return gchart;
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
	public void setPatientTab(String patientId, PatientTabPresenter patientTabPresenter) {
		tabLayoutPanel.add(patientTabPresenter.getDisplay().asWidget(), patientId);
	}
}
