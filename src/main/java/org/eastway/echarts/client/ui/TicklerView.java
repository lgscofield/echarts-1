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
import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;

import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
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
		void openPrintablePatientSummary(T t);
		void openEditContact(T t);
		void goTo(Place place);
	}

	public static interface Template extends SafeHtmlTemplates {
		@Template("<span style=\"color: red;\" title=\"HIPAA NOT completed\">{0}</span>")
		SafeHtml hipaaNotCompleted(String name);

		@Template("<span title=\"HIPAA completed {0}\">{1}</span>")
		SafeHtml hipaaCompleted(String dateCompleted, String name);

		@Template("<span style=\"color:green;\">{0}</span>")
		SafeHtml compliant(SafeHtml dueDate);

		@Template("{0}")
		SafeHtml noData(SafeHtml dueDate);

		@Template("<a style=\"color:red;\" target=\"_blank\" href=\"{0}\"><span style=\"color:red;font-weight:700;\"><b>NO&nbsp;DATA</b></span></a>")
		SafeHtml noData(String url);

		@Template("<a style=\"color:red;\" target=\"_blank\" href=\"{0}\"><span style=\"color:red;\">{1}</span></a>")
		SafeHtml dueInThirtyDays(String url, SafeHtml dueDate);

		@Template("<a style=\"color:red;\" target=\"_blank\" href=\"{0}\"><span style=\"color:red;font-weight:700;\">{1}</span></a>")
		SafeHtml overdue(String url, SafeHtml dueDate);
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
	void setHeader(String headerText);
	void setNoteTimeliness(int noDataCount,
			int noDataPercentage,
			int overdueCount,
			int overduePercentage,
			int upToDateCount,
			int upToDatePercentage);
	String formatTicklerDate(Date date);
	List<T> getRowData();
}
