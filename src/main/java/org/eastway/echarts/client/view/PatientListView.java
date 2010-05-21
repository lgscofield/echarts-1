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

import org.eastway.echarts.client.presenter.PatientListPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PatientListView extends Composite implements PatientListPresenter.Display {
	private static PatientListViewUiBinder uiBinder = GWT.create(PatientListViewUiBinder.class);

	interface PatientListViewUiBinder extends UiBinder<Widget, PatientListView> { }

	interface Style extends CssResource {
		String label();
	}

	@UiField FlowPanel list;
	@UiField Style style;

	private FlexTable table = new FlexTable();

	public PatientListView() {
		initWidget(uiBinder.createAndBindUi(this));
		list.add(table);
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
}
