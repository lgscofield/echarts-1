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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.style.client.GlobalResources;

public class AddressViewImpl<T> extends Composite implements AddressView<T> {

	private static AddressViewUiBinder uiBinder = GWT
			.create(AddressViewUiBinder.class);

	@UiTemplate("AddressView.ui.xml")
	interface AddressViewUiBinder extends UiBinder<Widget, AddressViewImpl> { }

	@UiField FlexTable table;
	private Presenter<T> presenter;
	private List<ColumnDefinition<T>> columnDefinitions;
	private List<T> rowData;

	public AddressViewImpl() {
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

	@Override
	public void setRowData(List<T> rowData) {
		this.rowData = rowData;
		for (int i = 0; i < rowData.size(); ++i) {
			final T t = rowData.get(i);
			if (i == 0) {
				for (int k = 0; k < columnDefinitions.size(); k++)
					table.setHTML(0, k, columnDefinitions.get(k).getHeader(t));
				i++;
			}
			for (int j = 0; j < columnDefinitions.size(); ++j) {
				final StringBuilder sb = new StringBuilder();
				columnDefinitions.get(j).render(t, sb);
				table.setHTML(i, j, sb.toString());
			}
		}
	}
}
