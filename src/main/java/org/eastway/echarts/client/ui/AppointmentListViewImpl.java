package org.eastway.echarts.client.ui;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.request.AppointmentReportProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;

public class AppointmentListViewImpl extends Composite implements AppointmentListView {
	@UiTemplate("AppointmentListView.ui.xml")
	interface AppointmentReportViewUiBinder extends UiBinder<HTMLPanel, AppointmentListViewImpl> {}

	private static AppointmentReportViewUiBinder uiBinder = GWT.create(AppointmentReportViewUiBinder.class);

	@UiField(provided=true) CellTable<AppointmentReportProxy> cellTable;

	@UiField SimplePager pager;

	AsyncHandler sortHandler;

	Presenter presenter;

	AsyncDataProvider<AppointmentReportProxy> dataProvider = new AsyncDataProvider<AppointmentReportProxy>() {
		@Override
		protected void onRangeChanged(HasData<AppointmentReportProxy> display) {
			if (presenter != null)
				presenter.requestReports();
		}
	};

	private Column<AppointmentReportProxy, Date> apptDateColumn;
	private Column<AppointmentReportProxy, String> staffNameColumn;
	private Column<AppointmentReportProxy, String> nameColumn;

	public AppointmentListViewImpl() {
		createTable();
		initWidget(uiBinder.createAndBindUi(this));
		dataProvider.addDataDisplay(cellTable);
	}

	@UiFactory
	SimplePager createPager() {
		SimplePager p = new SimplePager(TextLocation.RIGHT);
		p.setRangeLimited(true);
		p.setDisplay(cellTable);
		return p;
	}

	private void createTable() {
		cellTable = new CellTable<AppointmentReportProxy>(20);
		sortHandler = new AsyncHandler(cellTable);
		cellTable.addColumnSortHandler(sortHandler);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		final NoSelectionModel<AppointmentReportProxy> selectionModel = new NoSelectionModel<AppointmentReportProxy>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// when we support editing this entity...
			}
		});
		cellTable.setSelectionModel(selectionModel);

		nameColumn = new TextColumn<AppointmentReportProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getFullName());
			}
		};
		nameColumn.setSortable(true);
		cellTable.addColumn(nameColumn, "Name");
		apptDateColumn = new Column<AppointmentReportProxy, Date>(new DateCell(GlobalResources.getDateFormat())) {
			@Override
			public Date getValue(AppointmentReportProxy object) {
				return object.getApptDate();
			}
		};
		apptDateColumn.setSortable(true);
		cellTable.addColumn(apptDateColumn, "Date");
		cellTable.addColumn(new TextColumn<AppointmentReportProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getActivity());
			}
		}, "Activity");
		cellTable.addColumn(new TextColumn<AppointmentReportProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(
					DateTimeFormat
							.getFormat(GlobalResources.DateTimeConstants.SHORT_TIME));

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getStartTime());
			}
		}, "Start");
		cellTable.addColumn(new TextColumn<AppointmentReportProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(
					DateTimeFormat
							.getFormat(GlobalResources.DateTimeConstants.SHORT_TIME));

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getEndTime());
			}
		}, "End");
		staffNameColumn = new TextColumn<AppointmentReportProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getStaffName());
			}
		};
		staffNameColumn.setSortable(true);
		cellTable.addColumn(staffNameColumn, "Staff");
		cellTable.addColumn(new TextColumn<AppointmentReportProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(AppointmentReportProxy object) {
				return renderer.render(object.getNotes());
			}
		}, "Notes");
		cellTable.getColumnSortList().push(apptDateColumn);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Range getVisibleRange() {
		return cellTable.getVisibleRange();
	}

	@Override
	public void setRowCount(int count, boolean b) {
		cellTable.setRowCount(count, b);
	}

	@Override
	public int getPageSize() {
		return cellTable.getPageSize();
	}

	@Override
	public int getPageStart() {
		return cellTable.getPageStart();
	}

	@Override
	public void setRowData(int pageStart, List<AppointmentReportProxy> response) {
		cellTable.setRowData(pageStart, response);
	}

	@Override
	public ColumnSortList getColumnSortList() {
		return cellTable.getColumnSortList();
	}

	@Override
	public List<AppointmentReportProxy> getVisibleItems() {
		return cellTable.getVisibleItems();
	}

	@Override
	public String getColumnName(Column<?, ?> column) {
		if (column.equals(apptDateColumn)) {
			return "apptDate";
		} else if (column.equals(staffNameColumn)) {
			return "staffName";
		} else if (column.equals(nameColumn)) {
			return "fullName";
		} else {
			return null;
		}
	}
}
