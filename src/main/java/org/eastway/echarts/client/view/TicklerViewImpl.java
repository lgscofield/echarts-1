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

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.ui.ContextMenuLabel;

import com.google.gwt.core.client.GWT;
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
	@SuppressWarnings("rawtypes")
	@UiTemplate("TicklerView.ui.xml")
	interface TicklerViewUiBinder extends UiBinder<Widget, TicklerViewImpl> { }
	private static TicklerViewUiBinder uiBinder = GWT.create(TicklerViewUiBinder.class);

	@UiField Style style;
	@UiField FlexTable table;
	Presenter<T> presenter;
	private List<T> rowData = new ArrayList<T>();

	private MenuBar menuBar = new MenuBar(true);
	private PopupPanel menuPopup = new PopupPanel();

	private Command openIsp;
	private Command openEhr;

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
		table.setHTML(0, Column.NAME.ordinal(), "<b>Name</b>");
		table.setHTML(0, Column.CASE_NUMBER.ordinal(), "<b>Case Number</b>");
		table.setHTML(0, Column.ISP.ordinal(), "<b>ISP</b>");
		table.setHTML(0, Column.ISP_REVIEW.ordinal(), "<b>ISP Review</b>");
		table.setHTML(0, Column.HEALTH_HISTORY.ordinal(), "<b>Health History</b>");
		table.setHTML(0, Column.DIAGNOSTIC_ASSESSMENT_UPDATE.ordinal(), "<b>DA Update</b>");
		table.setHTML(0, Column.FINANCIAL.ordinal(), "<b>Financial</b>");
		table.setHTML(0, Column.OOC.ordinal(), "<b>OOC</b>");
		table.setBorderWidth(1);
		menuPopup.setAutoHideEnabled(true);
		menuPopup.add(menuBar);
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
		MenuItem openEhrMenuItem = new MenuItem("View Patient Tab", true, openEhr);
		menuBar.addItem(openEhrMenuItem);
		menuBar.addSeparator();
		openIsp = new Command() {
			@Override
			public void execute() {
				// TODO: appending caseNumber doesn't work
				com.google.gwt.user.client.Window.open("http://ewsql.eastway.local/echarts-asp/Forms/GandO.asp?staffid=" + EchartsUser.staffId + "&PATID=" + t, "ISP", "");
				menuPopup.hide();
			}
		};
		MenuItem openIspMenuItem = new MenuItem("New ISP", true, openIsp);
		menuBar.addItem(openIspMenuItem);

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

	@UiHandler("table")
	void handleItemSelected(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() == Column.CASE_NUMBER.ordinal()) {
				selected = cell.getRowIndex();
				if (selected < 1)
					return;
				presenter.openEhr(rowData.get(selected - 1));
			}
		}
	}

	@Override
	public void setRowData(List<T> rowData) {
		this.rowData = rowData;

		for (int i = 0; i < rowData.size(); ++i) {
			final T t = rowData.get(i);

			for (int j = 0; j < columnDefinitions.size(); ++j) {
				final StringBuilder sb = new StringBuilder();
				if (j == 0)
					columnDefinitions.get(j).render(t, sb.append((i+1) + ". "));
				else
					columnDefinitions.get(j).render(t, sb);
				if (columnDefinitions.get(j).isContextMenu()) {
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
					table.setWidget(i + 1, j, label);
				} else {
					table.setHTML(i + 1, j, sb.toString());
				}
			}
		}
	}

	@Override
	public void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
}
