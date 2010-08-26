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

public class ReferralViewImpl<T> extends Composite implements ReferralView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("ReferralView.ui.xml")
	interface ReferralViewUiBinder extends UiBinder<Widget, ReferralViewImpl> { }

	private static ReferralViewUiBinder uiBinder = GWT.create(ReferralViewUiBinder.class);

	@UiField FlexTable table;

	private List<ColumnDefinition<T>> columnDefinitions;

	private T rowData;

	public ReferralViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		table.addStyleName(GlobalResources.styles().table());
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
	}

	@Override
	public void setRowData(T rowData) {
		this.rowData = rowData;

		for (int i = 0; i < columnDefinitions.size(); i++) {
			StringBuilder sb = new StringBuilder();
			table.setHTML(i, 0, columnDefinitions.get(i).getHeader(this.rowData));
			columnDefinitions.get(i).render(this.rowData, sb);
			table.setHTML(i, 1, sb.toString());
		}
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
}
