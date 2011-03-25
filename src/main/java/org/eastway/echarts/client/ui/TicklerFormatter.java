package org.eastway.echarts.client.ui;

import java.util.Date;

import org.eastway.echarts.client.style.GlobalResources;
import org.eastway.echarts.shared.DueDateStatus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class TicklerFormatter {
	TicklerView.Template TEMPLATE = GWT.create(TicklerView.Template.class);

	public String formatColumn(String url, int status, String dueDate) {
		SafeHtml safeDueDate = SafeHtmlUtils.fromString(dueDate);
		if (status == DueDateStatus.NO_DATA)
			return TEMPLATE.noData(url).asString();
		if (status == DueDateStatus.COMPLIANT)
			return TEMPLATE.compliant(safeDueDate).asString();
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			return TEMPLATE.dueInThirtyDays(url, safeDueDate).asString();
		else
			return TEMPLATE.overdue(url, safeDueDate).asString();
	}

	public String formatColumn(Date completedDate, String name) {
		if (completedDate == null)
			return TEMPLATE.hipaaNotCompleted(name).asString();
		else
			return TEMPLATE.hipaaCompleted(GlobalResources.getDateFormat().format(completedDate), name).asString();
	}
}
