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
package org.eastway.echarts.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.style.GlobalResources;

public class AppointmentViewImpl<T> extends Composite implements AppointmentView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("AppointmentView.ui.xml")
	interface AppointmentViewUiBinder extends UiBinder<Widget, AppointmentViewImpl> {}

	private static AppointmentViewUiBinder uiBinder = GWT.create(AppointmentViewUiBinder.class);

	@UiField FlexTable table;
	@UiField SpanElement counter;
	@UiField SpanElement error;

	private List<ColumnDefinition<T>> columnDefinitions;

	private List<T> rowData;

	private Presenter<T> presenter;

	public AppointmentViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		table.addStyleName(GlobalResources.styles().table());
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}

	private int headerRepeat = 10, row = 0;

	@Override
	public void setRowData(List<T> rowData, int startRecord, int maxResults, long rowCount) {
		if (rowData == null || rowData.isEmpty()) {
			table.setVisible(false);
			return;
		} else {
			table.setVisible(true);
			error.setInnerText("");
		}

		table.removeAllRows();
		this.rowData = rowData;
		row = 0;
		for (int i = 0; i < this.rowData.size(); ++i, ++row, startRecord++) {
			final T t = this.rowData.get(i);

			if (i % headerRepeat == 0)
				setHeader(t);

			for (int j = 0; j < columnDefinitions.size(); ++j) {
				final StringBuilder sb = new StringBuilder();
				if (j == 0)
					columnDefinitions.get(j).render(t,
							sb.append((startRecord + 1) + ". "));
				else
					columnDefinitions.get(j).render(t, sb);
				table.setHTML(row, j, sb.toString());
			}
		}
	}

	private void setHeader(T t) {
		for (int j = 0; j < columnDefinitions.size(); j++)
			table.setHTML(row, j, columnDefinitions.get(j).getHeader(t));
		row++;
	}

	@UiHandler("newest")
	public void handleNewestClicked(ClickEvent event) {
		presenter.getNewest();
	}

	@UiHandler("newer")
	public void handleNewerClicked(ClickEvent event) {
		presenter.getNewer();
	}

	@UiHandler("older")
	public void handleOlderClicked(ClickEvent event) {
		presenter.getOlder();
	}

	@UiHandler("oldest")
	public void handleOldestClicked(ClickEvent event) {
		presenter.getOldest();
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}
}