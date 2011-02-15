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
package org.eastway.echarts.client.common;

import java.util.ArrayList;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;

@SuppressWarnings("serial")
public class TicklerColumnDefinitionsImpl extends
		ArrayList<ColumnDefinition<Tickler>> {

	private String noData = "<b>NO DATA</b>";
	public TicklerColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Tickler>() {
			public void render(Tickler t, StringBuilder sb) {
				if (t.getHipaaDateCompleted() == null) {
					sb.append("<span style=\"color: red;\" title=\"HIPAA NOT completed\">" + t.getName() + "</span>");
				} else {
					sb.append("<span title=\"HIPAA completed " + t.getHipaaDateCompleted() + "\">" + t.getName() + "</span>");
				}
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(t.getCaseNumber());
			}

			@Override
			public boolean isClickable() {
				return true;
			}

			@Override
			public boolean isContextMenu() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getIspDueDate().getDueDate();
				int status = t.getIspDueDate().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/GandO.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/GandO.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getIspReviewDueDate().getDueDate();
				int status = t.getIspReviewDueDate().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/GandOReview.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/GandOReview.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getHealthHistoryDueDate().getDueDate();
				int status = t.getHealthHistoryDueDate().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/107HealthHxEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/107HealthHxEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getDiagnosticAssessmentUpdate().getDueDate();
				int status = t.getDiagnosticAssessmentUpdate().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/110ADAUpdateEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/110ADAUpdateEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getFinancialDueDate().getDueDate();
				int status = t.getFinancialDueDate().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/109FinancialAuthEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"" + new UrlBuilder()
																		.setProtocol(Window.Location.getProtocol())
																		.setHost(EchartsUser.dbServerUrl)
																		.setPath("/echarts-asp/forms/109FinancialAuthEdit.asp")
																		.setParameter("staffid", EchartsUser.staffId)
																		.setParameter("PATID", t.getCaseNumber())
																		.buildString() + "\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				String dueDate = t.getOoc().getDueDate();
				int status = t.getOoc().getStatus();
				if (status == DueDateStatus.NO_DATA)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"https://home.eastway.local/outcomes/\">" + noData + "</a>";
				else if (status != DueDateStatus.COMPLIANT)
					dueDate = "<a style=\"color:red;\" target=\"_blank\" href=\"https://home.eastway.local/outcomes/\">" + dueDate + "</a>";
				sb.append(formatColumn(status, dueDate));
			}
		});
	}

	private String formatColumn(int status, String dueDate) {
		if (status == DueDateStatus.NO_DATA)
			return dueDate;
		if (status == DueDateStatus.COMPLIANT)
			return "<span style=\"color:green;\">" + dueDate + "</span>";
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			return "<span style=\"color:red;\">" + dueDate + "</span>";
		else
			return "<span style=\"color:red;font-weight:700;\">" + dueDate + "</span>";
	}
}
