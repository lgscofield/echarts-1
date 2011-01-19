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

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.IsWidget;

public interface TicklerView<T> extends IsWidget {
	public interface Presenter<T> {
		void openEhr(T t);
		void goTo(T t);
		void openIsp(T t);
		void openIndividualProgressNote(T t);
		void openCpstNote(T t);
		void openDoctorProgressNote(T t);
		void openNurseProgressNote(T t);
	}

	enum DueDateStatus {
		COMPLIANT,
		DUE_IN_THIRTY_DAYS,
		OVERDUE,
		NO_DATA
	}

	interface Style extends CssResource {
		String pointer();
		String compliant();
		String dueInThirtyDays();
		String overDue();
	}

	void setPresenter(Presenter<T> presenter);
	void setRowData(List<T> ticklers);
	void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
}