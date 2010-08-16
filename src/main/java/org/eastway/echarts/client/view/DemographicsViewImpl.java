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
import org.eastway.echarts.style.client.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class DemographicsViewImpl<T> extends Composite implements DemographicsView<T> {

	private static DemographicsViewUiBinder uiBinder = GWT
			.create(DemographicsViewUiBinder.class);

	@UiTemplate("DemographicsView.ui.xml")
	interface DemographicsViewUiBinder extends UiBinder<Widget, DemographicsViewImpl> { }

	private Presenter<T> presenter;
	private T rowData;
	private List<ColumnDefinition<T>> columnDefinitions;
	@UiField FlexTable table;

	public DemographicsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		table.addStyleName(GlobalResources.styles().table());
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
		for (int i = 0; i < columnDefinitions.size(); i++) {
			StringBuilder sb = new StringBuilder();
			table.setHTML(i, 0, columnDefinitions.get(i).getHeader(rowData));
			columnDefinitions.get(i).render(rowData, sb);
			table.setHTML(i, 1, sb.toString());
		}
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
}