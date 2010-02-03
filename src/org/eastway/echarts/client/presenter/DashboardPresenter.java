package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.ProductivityChart;
import org.eastway.echarts.client.view.AlertsView;
import org.eastway.echarts.client.view.PatientListView;
import org.eastway.echarts.client.view.PatientTabView;
import org.eastway.echarts.client.view.TopPanelView;
import org.eastway.echarts.client.events.OpenPatientEvent;
import org.eastway.echarts.client.events.OpenPatientEventHandler;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class DashboardPresenter extends EchartsPresenter<DashboardPresenter.Display> {
	private PatientServicesAsync patientSvc;

	public interface Display extends EchartsDisplay {
		TabLayoutPanel getTabPanel();

		ProductivityChart getChart();

		void setAlerts(AlertsPresenter alerts);

		void setPatientList(PatientListPresenter patientListPresenter);

		void setTopPanel(TopPanelPresenter topPanelPresenter);

		void setPatientTab(String patientId, PatientTabPresenter patientTab);
	}

	public DashboardPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc) {
		super(display, eventBus);
		this.patientSvc = patientSvc;
		bind();
		fetchWidgets();
	}

	private void bind() {
		display.getTabPanel().addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				if (event.getSelectedItem() == 0) {
					display.getChart().setVisible(true);
				} else {
					display.getChart().setVisible(false);
				}
			}
		});

		eventBus.addHandler(OpenPatientEvent.TYPE,
				new OpenPatientEventHandler() {
			public void onOpenPatient(OpenPatientEvent event) {
				openPatient(event.getId());
			}
		});
	}

	private void fetchWidgets() {
		fetchAlerts();
		fetchPatientList();
		fetchTopPanel();
	}

	private void fetchTopPanel() {
		TopPanelPresenter topPanel = new TopPanelPresenter(new TopPanelView(), eventBus, patientSvc);
		display.setTopPanel(topPanel);
	}

	private void fetchAlerts() {
		AlertsPresenter alerts = new AlertsPresenter(new AlertsView(), eventBus, patientSvc);
		display.setAlerts(alerts);
	}

	public void fetchPatientList() {
		PatientListPresenter patientList = new PatientListPresenter(new PatientListView(), eventBus, patientSvc);
		display.setPatientList(patientList);
	}

	public void openPatient(String patientId) {
		// History.newItem("t" + display.getTabPanel().getWidgetCount()
		// + patientId, false);
		PatientTabPresenter patientTab = new PatientTabPresenter(new PatientTabView(), eventBus, patientSvc, patientId);
		display.setPatientTab(patientId, patientTab);
	}
}
