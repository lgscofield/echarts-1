package org.eastway.echarts.client.common;

import java.util.ArrayList;

import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.Tickler;

@SuppressWarnings("serial")
public class TicklerColumnDefinitionsImpl extends
		ArrayList<ColumnDefinition<Tickler>> {

	private static TicklerColumnDefinitionsImpl instance = null;

	public static TicklerColumnDefinitionsImpl getInstance() {
		if (instance == null) {
			instance = new TicklerColumnDefinitionsImpl();
		}
		return instance;
	}
	private String noData = "<b>NO DATA</b>";
	protected TicklerColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Tickler>() {
			public void render(Tickler t, StringBuilder sb) {
				sb.append("<span title=\"HIPAA completed " + t.getHipaaDateCompleted() + "\">" + t.getName() + "</span>");
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
				sb.append(formatColumn(t.getIspDueDate().getStatus(), t.getIspDueDate().getDueDate()));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(formatColumn(t.getIspReviewDueDate().getStatus(), t.getIspReviewDueDate().getDueDate()));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(formatColumn(t.getHealthHistoryDueDate().getStatus(), t.getHealthHistoryDueDate().getDueDate()));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(formatColumn(t.getDiagnosticAssessmentUpdate().getStatus(), t.getDiagnosticAssessmentUpdate().getDueDate()));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(formatColumn(t.getFinancialDueDate().getStatus(), t.getFinancialDueDate().getDueDate()));
			}
		});
		this.add(new ColumnDefinition<Tickler>() {
			@Override
			public void render(Tickler t, StringBuilder sb) {
				sb.append(formatColumn(t.getOoc().getStatus(), t.getOoc().getDueDate()));
			}
		});
	}

	private String formatColumn(int status, String dueDate) {
		if (status == DueDateStatus.NO_DATA) {
			return noData;
		}
		if (status == DueDateStatus.COMPLIANT)
			return "<span style=\"color:green;\">" + dueDate + "</span>";
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			return "<span style=\"color:red;\">" + dueDate + "</span>";
		else
			return "<span style=\"color:red;font-weight:700;\">" + dueDate + "</span>";
	}
}
