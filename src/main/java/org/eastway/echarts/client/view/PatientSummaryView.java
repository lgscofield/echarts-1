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

import java.util.LinkedHashSet;

import org.eastway.echarts.client.presenter.PatientSummaryPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class PatientSummaryView extends Composite implements PatientSummaryPresenter.Display {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, PatientSummaryView> {}

	@UiField FlexTable ft1;
	@UiField FlexTable ft2;

	interface Style extends CssResource {
	}

	@UiField Style style;

	public PatientSummaryView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashSet<String[]> data) {
		int i = 0;
		for (String[] s : data) {
			for (int j = 0; j < s.length; j++) {
				ft1.setHTML(i, j, s[j]);
			}
			i++;
		}
	}
}
