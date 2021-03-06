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

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;

public interface AppointmentView<T> extends IsWidget {
	interface Presenter<T> {
		void setDateFilter(Date value);
	}

	void setPresenter(Presenter<T> presenter);
	Widget asWidget();
	void setError(String message);
	void reset();
	void setDataProvider(AbstractDataProvider<T> dataProvider);
	void setStartDate(Date date);
	Date getStartDate();
}
