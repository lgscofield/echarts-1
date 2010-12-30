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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;

public class AppointmentViewImpl<T> extends Composite implements AppointmentView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("AppointmentView.ui.xml")
	interface AppointmentViewUiBinder extends UiBinder<Widget, AppointmentViewImpl> {}

	private static AppointmentViewUiBinder uiBinder = GWT.create(AppointmentViewUiBinder.class);

	@UiField SpanElement error;

	@UiField CellTable<T> cellTable;
	SimplePager.Resources resources = GWT.create(SimplePager.Resources.class);
	@UiField(provided=true) SimplePager pager = new SimplePager(SimplePager.TextLocation.RIGHT, resources, false, 0, false);

	private AbstractDataProvider<T> dataProvider;

	public AppointmentViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		pager.setDisplay(cellTable);
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}

	@Override
	public void reset() {
		pager.startLoading();
		dataProvider.removeDataDisplay(cellTable);
	}

	@Override
	public void setDataProvider(AbstractDataProvider<T> dataProviderIn) {
		this.dataProvider = dataProviderIn;
		dataProvider.addDataDisplay(cellTable);
	}
}