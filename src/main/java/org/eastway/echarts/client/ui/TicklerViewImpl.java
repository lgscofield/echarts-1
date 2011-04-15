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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class TicklerViewImpl<T> extends Composite implements TicklerView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("TicklerView.ui.xml")
	interface TicklerViewUiBinder extends UiBinder<Widget, TicklerViewImpl> { }
	private static TicklerViewUiBinder uiBinder = GWT.create(TicklerViewUiBinder.class);

	@UiField Style style;
	@UiField public FlexTable table;
	Presenter<T> presenter;
	private List<T> rowData = new ArrayList<T>();

	private MenuBar menuBar = new MenuBar(true);
	private PopupPanel menuPopup = new PopupPanel();

	private Command openIsp;
	private Command openCpstNote;
	private Command openIndividualProgressNote;
	private Command openDoctorProgressNote;
	private Command openNurseProgressNote;
	private Command openEhr;
	private Command openPrintablePatientSummary;
	private Command openEditContact;

	private List<ColumnDefinition<T>> columnDefinitions;

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
		menuPopup.setAutoHideEnabled(true);
		menuPopup.add(menuBar);
	}

	private void setHeader(int row) {
		table.setHTML(row, Column.NAME.ordinal(), "<b>Name</b>");
		table.setHTML(row, Column.CASE_NUMBER.ordinal(), "<b>Case #</b>");
		table.setHTML(row, Column.ISP.ordinal(), "<b>ISP</b>");
		table.setHTML(row, Column.ISP_REVIEW.ordinal(), "<b>ISP Review</b>");
		table.setHTML(row, Column.HEALTH_HISTORY.ordinal(), "<b>Health HX</b>");
		table.setHTML(row, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), "<b>DA Update</b>");
		table.setHTML(row, Column.FINANCIAL.ordinal(), "<b>Financial</b>");
		table.setHTML(row, Column.OOC.ordinal(), "<b>OOC</b>");
	}

	public void openMenu(final T t, int x, int y) {
		menuBar.clearItems();
		openEhr = new Command() {
			@Override
			public void execute() {
				presenter.openEhr(t);
				menuPopup.hide();
			}
		};
		MenuItem openEhrMenuItem = new MenuItem("Open Patient Chart in New Window", true, openEhr);
		menuBar.addItem(openEhrMenuItem);
		menuBar.addSeparator();
		openIsp = new Command() {
			@Override
			public void execute() {
				presenter.openIsp(t);
				menuPopup.hide();
			}
		};
		openEditContact = new Command() {
			@Override
			public void execute() {
				presenter.openEditContact(t);
				menuPopup.hide();
			}
		};
		MenuItem openEditContactMenuItem = new MenuItem("Edit Contact Information", true, openEditContact);
		menuBar.addItem(openEditContactMenuItem);
		openPrintablePatientSummary = new Command() {
			@Override
			public void execute() {
				presenter.openPrintablePatientSummary(t);
				menuPopup.hide();
			}
		};
		MenuItem openPrintablePatientSummaryMenuItem = new MenuItem("Printable patient summary", true, openPrintablePatientSummary);
		menuBar.addItem(openPrintablePatientSummaryMenuItem);
		MenuItem openIspMenuItem = new MenuItem("New ISP", true, openIsp);
		menuBar.addItem(openIspMenuItem);
		openIndividualProgressNote = new Command() {
			@Override
			public void execute() {
				presenter.openIndividualProgressNote(t);
				menuPopup.hide();
			}
		};
		MenuItem openIndividualProgressNoteMenuItem = new MenuItem("New Individual Progress Note", true, openIndividualProgressNote);
		menuBar.addItem(openIndividualProgressNoteMenuItem);
		openCpstNote = new Command() {
			@Override
			public void execute() {
				presenter.openCpstNote(t);
				menuPopup.hide();
			}
		};
		MenuItem openCpstNoteMenuItem = new MenuItem("New CPST Note", true, openCpstNote);
		menuBar.addItem(openCpstNoteMenuItem);
		openDoctorProgressNote = new Command() {
			@Override
			public void execute() {
				presenter.openDoctorProgressNote(t);
				menuPopup.hide();
			}
		};
		MenuItem openDoctorProgressNoteMenuItem = new MenuItem("New Doctor Progress Note", true, openDoctorProgressNote);
		menuBar.addItem(openDoctorProgressNoteMenuItem);
		openNurseProgressNote = new Command() {
			@Override
			public void execute() {
				presenter.openNurseProgressNote(t);
				menuPopup.hide();
			}
		};
		MenuItem openNurseProgressNoteMenuItem = new MenuItem("New Nurse Progress Note", true, openNurseProgressNote);
		menuBar.addItem(openNurseProgressNoteMenuItem);
		menuPopup.setPopupPosition(x, y);
		menuPopup.show();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	private int headerRepeat = 10;

	@UiHandler("table")
	void handleItemSelected(ClickEvent event) {
		int selected = -1;
		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() == Column.CASE_NUMBER.ordinal()) {
				selected = cell.getRowIndex();
				if (selected < 1)
					return;
				presenter.goTo(rowData.get(getRow(selected)));
			}
		}
	}

	public int getRow(int selected) {
		for (int i = 0; i < selected; i++)
			if ((i % headerRepeat) == 0)
				selected--;
		return selected;
	}

	TicklerFormatter formatter = new TicklerFormatter();

	@Override
	public void setRowData(List<T> rowData) {
		this.rowData = rowData;
		table.removeAllRows();
		for (int i = 0, row = 0; i < rowData.size(); ++i, ++row) {
			if (i % headerRepeat == 0)
				setHeader(row++);
			final T t = rowData.get(i);

			for (int j = 0; j < columnDefinitions.size(); ++j) {
				final StringBuilder sb = new StringBuilder();
				ColumnDefinition<T> r = columnDefinitions.get(j);
				if (j == 0)
					r.render(t, sb.append((i+1) + ". "));
				else
					r.render(t, sb);
				if (r.isContextMenu()) {
					ContextMenuLabel label = new ContextMenuLabel(sb.toString());
					label.addContextMenuHandler(new ContextMenuHandler() {
						@Override
						public void onContextMenu(ContextMenuEvent event) {
							event.stopPropagation();
							event.preventDefault();
							openMenu(t, event.getNativeEvent().getClientX(),
									event.getNativeEvent().getClientY());
						}
					});
					label.addStyleName(style.pointer());
					table.setWidget(row, j, label);
				} else {
					if (r.isTicklerDueDate())
						table.setHTML(row, j, formatter.formatColumn(r.createTicklerUrl(t), r.getTicklerDueDateStatus(t), sb.toString()));
					else if (r.isTicklerCompletedDate())
						table.setHTML(row, j, formatter.formatColumn(r.getCompletedDate(t), sb.toString()));
					else
						table.setHTML(row, j, sb.toString());
				}
			}
		}
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}

	@UiField SpanElement header;

	@Override
	public void setHeader(String headerText) {
		if (headerText != null && headerText.length() != 0)
			header.setInnerText(headerText);
	}

	@UiField SpanElement noteTimeliness;

	@Override
	public void setNoteTimeliness(int noDataCount, int noDataPercentage,
			int overdueCount, int overduePercentage,
			int upToDateCount, int upToDatePercentage) {
		noteTimeliness.setInnerText("Up to date: " + upToDatePercentage + "% (" + upToDateCount + ") Overdue: " + overduePercentage + "% (" + overdueCount + ") No data: " + noDataPercentage + "% (" + noDataCount + ")");
	}

	@Override
	public String formatTicklerDate(Date date) {
		return GlobalResources.getDateFormat().format(date);
	}

	@Override
	public List<T> getRowData() {
		return rowData;
	}
}
