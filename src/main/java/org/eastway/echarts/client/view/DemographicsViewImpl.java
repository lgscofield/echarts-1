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

import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class DemographicsViewImpl<T> extends Composite implements DemographicsView<T> {

	private static DemographicsViewUiBinder uiBinder = GWT
			.create(DemographicsViewUiBinder.class);

	@UiTemplate("DemographicsView.ui.xml")
	interface DemographicsViewUiBinder extends UiBinder<Widget, DemographicsViewImpl> { }

	private Presenter<T> presenter;
	private T rowData;
	private List<ColumnDefinition<T>> columnDefinitions;
	@UiField FlowPanel panel;

	public DemographicsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setRowData(T rowData) {
		this.rowData = rowData;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columnDefinitions.size(); i++)
			columnDefinitions.get(i).render(rowData, sb);
		panel.add(new HTML(sb.toString()));
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
}
