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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DiagnosisViewImpl<T> extends Composite implements DiagnosisView<T> {
	@UiTemplate("DiagnosisView.ui.xml")
	interface DiagnosisViewUiBinder extends UiBinder<Widget, DiagnosisViewImpl> { }
	private static DiagnosisViewUiBinder uiBinder = GWT.create(DiagnosisViewUiBinder.class);

	@UiField StackLayoutPanel diagnoses;
	private Presenter<T> presenter;
	private List<T> rowData;
	private List<ColumnDefinition<T>> columnDefinitions;

	enum Column {
		AXIS1A,
		AXIS1B,
		AXIS1C,
		AXIS1D,
		AXIS1E,
		AXIS2A,
		AXIS2B,
		AXIS2C,
		AXIS3,
		AXIS4,
		CURRENTGAF,
		HIGHESTGAF,
		DATE,
	}

	public DiagnosisViewImpl() {
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
	public void setRowData(List<T> rowData) {
		diagnoses.clear();
		this.rowData = rowData;

		for(int i = 0; i < rowData.size(); i++) {
			final T t = rowData.get(i);
			String header = columnDefinitions.get(0).getHeader(t);
			ScrollPanel scroll = new ScrollPanel();
			HTML vp = new HTML();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < columnDefinitions.size(); j++) {
				columnDefinitions.get(j).render(t, sb);
				vp.setHTML(sb.toString());
			}
			scroll.add(vp);
			diagnoses.add(scroll, header, 30);
		}
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
}
