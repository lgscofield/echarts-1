package org.eastway.echarts.dashboard.client;

import org.eastway.echarts.client.presenter.AlertsPresenter;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.PatientListPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.TopPanelPresenter;
import org.eastway.echarts.client.view.AlertsView;
import org.eastway.echarts.client.view.PatientListView;
import org.eastway.echarts.client.view.TopPanelView;
import org.eastway.echarts.client.events.OpenPatientEvent;
import org.eastway.echarts.client.events.OpenPatientEventHandler;
import org.eastway.echarts.tab.client.presenter.TabPresenter;
import org.eastway.echarts.tab.client.view.TabView;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CommunitySupportSpecialistDashboardPresenter extends Presenter<CommunitySupportSpecialistDashboardPresenter.Display> {

	public interface Display extends EchartsDisplay {
		HasSelectionHandlers<Integer> getPanel();

		void setChartVisibility(boolean v);

		void setAlerts(AlertsPresenter alerts);

		void setPatientList(PatientListPresenter patientListPresenter);

		void setTopPanel(TopPanelPresenter topPanelPresenter);

		HasClickHandlers setPatientTab(String patientId, Widget patientTab);

		int getIndex(Widget tab);

		void setSelectedTab(Integer i);

		boolean removeTab(Integer i);
	}

	public CommunitySupportSpecialistDashboardPresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		bind();
		fetchWidgets();
	}

	private void bind() {
		display.getPanel().addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				if (event.getSelectedItem() == 0) {
					display.setChartVisibility(true);
				} else {
					display.setChartVisibility(false);
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
		TopPanelPresenter topPanel = new TopPanelPresenter(new TopPanelView(), eventBus);
		display.setTopPanel(topPanel);
	}

	private void fetchAlerts() {
		AlertsPresenter alerts = new AlertsPresenter(new AlertsView(), eventBus);
		display.setAlerts(alerts);
	}

	public void fetchPatientList() {
		PatientListPresenter patientList = new PatientListPresenter(new PatientListView(), eventBus);
		display.setPatientList(patientList);
	}

	public void openPatient(String patientId) {
		final TabPresenter patientTab = new TabPresenter(
			new TabView(), eventBus, patientId);
		display.setPatientTab(patientId,
					patientTab.getDisplay().asWidget())
						.addClickHandler(
							new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer idx = display.getIndex(
					patientTab.getDisplay().asWidget());
				if (display.removeTab(idx))
					display.setSelectedTab(idx - 1);
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
