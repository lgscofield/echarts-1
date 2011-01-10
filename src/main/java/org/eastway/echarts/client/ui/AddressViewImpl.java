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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.common.AddressCellTable;
import org.eastway.echarts.client.request.AddressProxy;

public class AddressViewImpl<T> extends Composite implements AddressView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("AddressView.ui.xml")
	interface AddressViewUiBinder extends UiBinder<Widget, AddressViewImpl> { }

	private static AddressViewUiBinder uiBinder = GWT
			.create(AddressViewUiBinder.class);

	@UiField AddressCellTable panel;
	@UiField SpanElement error;

	public AddressViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRowData(List<T> rowData) {
		if (rowData == null || rowData.isEmpty()) {
			panel.setVisible(false);
			return;
		} else {
			panel.setVisible(true);
			error.setInnerText("");
		}
		panel.setRowData(0, (List<? extends AddressProxy>) rowData);
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}
}
