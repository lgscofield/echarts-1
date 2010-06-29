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

import java.util.LinkedHashMap;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PatientListViewImpl<T> extends Composite implements PatientListView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("PatientListView.ui.xml")
	interface PatientListViewUiBinder extends UiBinder<Widget, PatientListViewImpl> { }
	private static PatientListViewUiBinder uiBinder = GWT.create(PatientListViewUiBinder.class);

	@UiField DisclosurePanel display;
	@UiField Style style;
	@UiField FlexTable table;
	Presenter<T> presenter;

	public PatientListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		display.addOpenHandler(new OpenHandler<DisclosurePanel>() {
			@Override
			public void onOpen(OpenEvent<DisclosurePanel> arg0) {
				presenter.onOpenChartClicked();
			}
		});
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashMap<String, Long> data) {
		Set<String> d = data.keySet();
		int i = 0;
		for (String s : d) {
			Label lbl = new Label(s);
			table.setWidget(i, 0, lbl);
			lbl.addStyleName(style.label());
			i++;
		}
	}

	@Override
	public HasClickHandlers getTable() {
		return table;
	}

	@Override
	public String getClickedRow(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() >= 0) {
				selected = cell.getRowIndex();
			}
		}
		return table.getText(selected, 0);
	}

	@UiHandler( "table")
	void handleItemSelected(ClickEvent event) {
		String row = getClickedRow(event);
		if (row != null)
			presenter.onItemSelected(row);
	}

	@Override
	public void setPresenter(
			org.eastway.echarts.client.view.PatientListView.Presenter<T> presenter) {
		this.presenter = presenter;
	}
}
