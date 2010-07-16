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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;

public interface TicklerView<T> {
	public interface Presenter<T> {
		void openEhr(String text);
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
	Widget asWidget();
	HasClickHandlers getTable();
	String getClickedRow(ClickEvent event);
	void reset();
	void addData(String str);
	void nextRecord();
	void setName(String name);
	void setCaseNumber(String caseNumber);
	void setIspDueDate(String dueDate, DueDateStatus status);
	void setIspReviewDueDate(String dueDate, DueDateStatus status);
	void setHealthHistoryDueDate(String dueDate, DueDateStatus status);
	void setDiagnosticAssessmentUpdate(String dueDate, DueDateStatus status);
	void setFinancialDueDate(String dueDate, DueDateStatus status);
	void setOoc(String dueDate, DueDateStatus status);
	void setHipaaDateCompleted(String hipaaDateCompleted);
}
