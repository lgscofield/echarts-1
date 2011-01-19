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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DiagnosisViewImpl<T> extends Composite implements DiagnosisView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("DiagnosisView.ui.xml")
	interface DiagnosisViewUiBinder extends UiBinder<Widget, DiagnosisViewImpl> { }
	private static DiagnosisViewUiBinder uiBinder = GWT.create(DiagnosisViewUiBinder.class);

	@UiField CellTable<T> cellTable;
	@UiField SpanElement error;

	public DiagnosisViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setRowData(List<T> rowData) {
		if (rowData == null) {
			cellTable.setVisible(false);
			return;
		}
		cellTable.setVisible(true);
		cellTable.setRowData(rowData);
		error.setInnerText("");
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}
}
