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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TicklerViewImpl<T> extends Composite implements TicklerView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("TicklerView.ui.xml")
	interface TicklerViewUiBinder extends UiBinder<Widget, TicklerViewImpl> { }
	private static TicklerViewUiBinder uiBinder = GWT.create(TicklerViewUiBinder.class);

	@UiField Style style;
	@UiField FlexTable table;
	Presenter<T> presenter;
	private int record = 0;
	private String noData = "<b>NO DATA</b>";

	enum Column {
		NAME,
		CASE_NUMBER,
		ISP,
		ISP_REVIEW,
		HEALTH_HISTORY,
		DIAGNOSTIC_ASSESSMENT_UPDATE,
		FINANCIAL,
		OOC
	}

	public TicklerViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		table.setHTML(record, Column.NAME.ordinal(), "<b>Name</b>");
		table.setHTML(record, Column.CASE_NUMBER.ordinal(), "<b>Case Number</b>");
		table.setHTML(record, Column.ISP.ordinal(), "<b>ISP</b>");
		table.setHTML(record, Column.ISP_REVIEW.ordinal(), "<b>ISP Review</b>");
		table.setHTML(record, Column.HEALTH_HISTORY.ordinal(), "<b>Health History</b>");
		table.setHTML(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), "<b>DA Update</b>");
		table.setHTML(record, Column.FINANCIAL.ordinal(), "<b>Financial</b>");
		table.setHTML(record, Column.OOC.ordinal(), "<b>OOC</b>");
		record++;
		table.setBorderWidth(1);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getTable() {
		return table;
	}

	@Override
	public String getClickedRow(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() >= 0) {
				selected = cell.getRowIndex();
			}
		}
		return table.getText(selected, 0);
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@Override
	public void reset() {
		
	}

	@UiHandler("table")
	void handleItemSelected(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() == Column.CASE_NUMBER.ordinal()) {
				selected = cell.getRowIndex();
				String row = table.getText(selected, Column.CASE_NUMBER.ordinal());
				if (row != null)
					presenter.openEhr(row);
			}
		}
	}

	@Override
	public void addData(String str) {
		table.setText(record++, 0, str);
	}

	@Override
	public void nextRecord() {
		record++;
	}

	@Override
	public void setName(String name) {
		table.setHTML(record, Column.NAME.ordinal(), record + ". " + name);
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		Label caseNumberLabel = new Label(caseNumber);
		caseNumberLabel.addStyleName(style.pointer());
		table.setWidget(record, Column.CASE_NUMBER.ordinal(), caseNumberLabel);
	}

	@Override
	public void setIspDueDate(String ispDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.ISP.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.ISP.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.ISP.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.ISP.ordinal(), style.overDue());
		table.setHTML(record, Column.ISP.ordinal(),
					(ispDateCompleted == null) ? noData : ispDateCompleted);
	}

	@Override
	public void setIspReviewDueDate(String ispReviewDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.ISP_REVIEW.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.ISP_REVIEW.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.ISP_REVIEW.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.ISP_REVIEW.ordinal(), style.overDue());
		table.setHTML(record, Column.ISP_REVIEW.ordinal(),
				(ispReviewDateCompleted == null) ? noData : ispReviewDateCompleted.toString());
	}

	@Override
	public void setHealthHistoryDueDate(String healthHistoryDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.HEALTH_HISTORY.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.HEALTH_HISTORY.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.HEALTH_HISTORY.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.HEALTH_HISTORY.ordinal(), style.overDue());
		table.setHTML(record, Column.HEALTH_HISTORY.ordinal(),
				(healthHistoryDateCompleted == null) ? noData : healthHistoryDateCompleted.toString());
	}

	@Override
	public void setDiagnosticAssessmentUpdate(String diagnosticAssessmentDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), style.overDue());
		table.setHTML(record, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(),
				(diagnosticAssessmentDateCompleted == null) ? noData : diagnosticAssessmentDateCompleted.toString());
	}

	@Override
	public void setFinancialDueDate(String financialDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.FINANCIAL.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.FINANCIAL.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.FINANCIAL.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.FINANCIAL.ordinal(), style.overDue());
		table.setHTML(record, Column.FINANCIAL.ordinal(),
				(financialDateCompleted == null) ? noData : financialDateCompleted.toString());
	}

	@Override
	public void setOoc(String outcomesConsumerDateCompleted, DueDateStatus status) {
		if (status == DueDateStatus.NO_DATA) {
			table.setHTML(record, Column.OOC.ordinal(), noData);
			return;
		}
		if (status == DueDateStatus.COMPLIANT)
			table.getCellFormatter().addStyleName(record, Column.OOC.ordinal(), style.compliant());
		else if (status == DueDateStatus.DUE_IN_THIRTY_DAYS)
			table.getCellFormatter().addStyleName(record, Column.OOC.ordinal(), style.dueInThirtyDays());
		else
			table.getCellFormatter().addStyleName(record, Column.OOC.ordinal(), style.overDue());
		table.setHTML(record, Column.OOC.ordinal(),
				(outcomesConsumerDateCompleted == null) ? noData : outcomesConsumerDateCompleted.toString());
	}

	@Override
	public void setHipaaDateCompleted(String hipaaDateCompleted) {
		table.getCellFormatter()
			.getElement(record, Column.CASE_NUMBER.ordinal())
			.setAttribute("title", "HIPAA completed " + hipaaDateCompleted);
	}
}
