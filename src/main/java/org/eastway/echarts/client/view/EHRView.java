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

import org.eastway.echarts.shared.EHR;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface EHRView<T> {

	public interface Presenter<T> {
		void viewPatientSummary();

		EHR getEhr();

		void viewMessages();

		void viewDemographics();
	}

	Widget asWidget();
	void setTreeItemWidth();
	void setPresenter(Presenter<T> presenter);
	Presenter<T> getPresenter();
	HasWidgets getDisplayArea();
}
