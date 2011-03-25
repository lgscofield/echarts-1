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
import java.util.Date;

import org.eastway.echarts.client.style.GlobalResources;
import org.eastway.echarts.client.ui.TicklerUrlBuilder;
import org.eastway.echarts.shared.Tickler;

@SuppressWarnings("serial")
public class TicklerColumnDefinitionsImpl extends
		ArrayList<ColumnDefinition<Tickler>> {

	public TicklerColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
					sb.append(t.getName());
			}

			@Override
			public boolean isTicklerCompletedDate() {
				return true;
			}

			@Override
			public Date getCompletedDate(Tickler t) {
				return t.getHipaaDateCompleted();
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
				sb.append(GlobalResources.getDateFormat().format(t.getIspDueDate().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createIspUrl(t.getCaseNumber());
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getIspDueDate().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(GlobalResources.getDateFormat().format(t.getIspReviewDueDate().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createIspReviewUrl(t.getCaseNumber());
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getIspReviewDueDate().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(GlobalResources.getDateFormat().format(t.getHealthHistoryDueDate().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createHealthHistoryUrl(t.getCaseNumber());
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getHealthHistoryDueDate().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(GlobalResources.getDateFormat().format(t.getDiagnosticAssessmentUpdate().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createDAUpdateUrl(t.getCaseNumber());
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getDiagnosticAssessmentUpdate().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(GlobalResources.getDateFormat().format(t.getFinancialDueDate().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createFinancialUpdateUrl(t.getCaseNumber());
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getFinancialDueDate().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(GlobalResources.getDateFormat().format(t.getOoc().getDueDate()));
			}

			@Override
			public String createTicklerUrl(Tickler t) {
				return TicklerUrlBuilder.createOocUrl();
			}

			@Override
			public int getTicklerDueDateStatus(Tickler t) {
				return t.getOoc().getStatus();
			}

			@Override
			public boolean isTicklerDueDate() {
				return true;
			}
		});
	}
}
