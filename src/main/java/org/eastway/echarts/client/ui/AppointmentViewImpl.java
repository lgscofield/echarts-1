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

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.style.GlobalResources;

public class AppointmentViewImpl<T> extends Composite implements AppointmentView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("AppointmentView.ui.xml")
	interface AppointmentViewUiBinder extends UiBinder<Widget, AppointmentViewImpl> {}

	private static AppointmentViewUiBinder uiBinder = GWT.create(AppointmentViewUiBinder.class);

	ListDataProvider<AppointmentProxy> listDataProvider = new ListDataProvider<AppointmentProxy>();

	@UiField SpanElement error;

	@UiField CellTable<AppointmentProxy> cellTable;
	@UiField SimplePager pager;


	public AppointmentViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setColumnDefinitions();
		listDataProvider.addDataDisplay(cellTable);
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public void setColumnDefinitions() {
		cellTable.addColumn(new TextColumn<AppointmentProxy>() {
			@Override
			public String getValue(AppointmentProxy object) {
				return object.getActivity();
			}
		}, "Activity");
		cellTable.addColumn(new TextColumn<AppointmentProxy>() {
			@Override
			public String getValue(AppointmentProxy object) {
				return GlobalResources.getDateFormat().format(object.getAppointmentDate());
			}
		}, "Appt Date");
		cellTable.addColumn(new TextColumn<AppointmentProxy>() {
			@Override
			public String getValue(AppointmentProxy object) {
				return GlobalResources.getTimeFormat().format(object.getStartTime());
			}
		}, "Start Time");
		cellTable.addColumn(new TextColumn<AppointmentProxy>() {
			@Override
			public String getValue(AppointmentProxy object) {
				return GlobalResources.getTimeFormat().format(object.getEndTime());
			}
		}, "End Time");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRowData(List<T> rowData, int startRecord, int maxResults, long rowCount) {

		if (rowData == null || rowData.isEmpty()) {
			cellTable.setVisible(false);
			pager.setVisible(false);
			return;
		} else {
			pager.setVisible(true);
			cellTable.setVisible(true);
			error.setInnerText("");
		}
		listDataProvider.setList((List<AppointmentProxy>) rowData);
		cellTable.setRowData(0, (List<? extends AppointmentProxy>) rowData);
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}
}