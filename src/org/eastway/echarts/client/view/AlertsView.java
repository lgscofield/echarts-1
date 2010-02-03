package org.eastway.echarts.client.view;

import java.util.Vector;

import org.eastway.echarts.client.presenter.AlertsPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class AlertsView extends Composite implements AlertsPresenter.Display {
	private FlexTable alertsTable = new FlexTable();
	private ScrollPanel sp = new ScrollPanel();

	public AlertsView() {
		initWidget(sp);
		sp.add(alertsTable);
		sp.setSize("100%", "90px");
	}

	@Override
	public HasClickHandlers getAlerts() {
		return alertsTable;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(Vector<String> data) {
		alertsTable.removeAllRows();
		for (int i = 0; i < data.size(); i++) {
			alertsTable.setHTML(i, 0, data.get(i));
		}
	}
}
