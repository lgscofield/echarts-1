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

import java.util.LinkedHashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;

public interface PatientListView<T> {
	public interface Presenter<T> {
		void onOpenChartClicked();

		void onItemSelected(String row);
	}

	interface Style extends CssResource {
		String label();
	}

	void setPresenter(Presenter<T> presenter);
	Widget asWidget();
	HasClickHandlers getTable();
	void setData(LinkedHashMap<String, Long> data);
	String getClickedRow(ClickEvent event);
}
