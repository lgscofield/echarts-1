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
