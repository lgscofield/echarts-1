package org.eastway.echarts.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.request.AppointmentReportProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.requestfactory.shared.EntityProxyChange;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
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
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.inject.Inject;

public class AppointmentReportList extends Composite implements EntityProxyChange.Handler<AppointmentReportProxy>, Activity {
	@UiTemplate("AppointmentReportList.ui.xml")
	interface AppointmentReportViewUiBinder extends UiBinder<HTMLPanel, AppointmentReportList> {}

	private static AppointmentReportViewUiBinder uiBinder = GWT.create(AppointmentReportViewUiBinder.class);

	@UiField(provided=true) CellTable<AppointmentReportProxy> cellTable;

	@UiField SimplePager pager;

	AsyncHandler sortHandler;

	private EchartsRequestFactory requestFactory;

	private Receiver<Long> lastDataSizeReceiver;

	private Receiver<List<AppointmentReportProxy>> lastDataReceiver;

	AsyncDataProvider<AppointmentReportProxy> dataProvider = new AsyncDataProvider<AppointmentReportProxy>() {
		@Override
		protected void onRangeChanged(HasData<AppointmentReportProxy> display) {
			requestReports();
		}
	};

	@Inject
	public AppointmentReportList(EchartsRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
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

	private Listener listener;

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public interface Listener {
		void onReportSelected(AppointmentReportProxy proxy);
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
				Object selected = selectionModel.getLastSelectedObject();
				// when we support editing this entity...
				//if (selected != null && listener != null)
				//	listener.onReportSelected((AppointmentReportProxy) selected);
			}
		});
		cellTable.setSelectionModel(selectionModel);

		TextColumn<AppointmentReportProxy> nameColumn = new TextColumn<AppointmentReportProxy>() {
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
		//nameColumn.setSortable(true);
		cellTable.addColumn(nameColumn, "Name");
		Column<AppointmentReportProxy, Date> apptDateColumn = new Column<AppointmentReportProxy, Date>(new DateCell(GlobalResources.getDateFormat())) {
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
		cellTable.addColumn(new TextColumn<AppointmentReportProxy>() {
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
		}, "Staff");
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

	private void requestReports() {
		Range range = cellTable.getVisibleRange();

		lastDataSizeReceiver = new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				if (this == lastDataSizeReceiver) {
					int count = response.intValue();
					cellTable.setRowCount(count, count != 1000);
				}
			}
		};

		requestFactory.appointmentReportRequest().findAppointmentReportsWithApptDateCount().fire(lastDataSizeReceiver);

		lastDataReceiver = new Receiver<List<AppointmentReportProxy>>() {
			@Override
			public void onSuccess(List<AppointmentReportProxy> response) {
				if (this == lastDataReceiver) {
					int size = response.size();
					if (size < cellTable.getPageSize()) {
						cellTable.setRowCount(cellTable.getPageStart() + size, true);
					}
					if (size > 0) {
						cellTable.setRowData(cellTable.getPageStart(), response);
					}
				}
			}
		};

		boolean isAscending = cellTable.getColumnSortList().get(0).isAscending();
		requestFactory.appointmentReportRequest().findAppointmentReportsWithApptDate(range.getStart(), range.getLength(), isAscending).fire(lastDataReceiver);
	}

	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {
		onStop();
	}

	@Override
	public void onStop() {
		//running = false;
		//refreshTimer.cancel();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		EntityProxyChange.registerForProxyType(eventBus, AppointmentReportProxy.class, 	this);
		requestReports();
		panel.setWidget(this);
	}

	@Override
	public void onProxyChange(EntityProxyChange<AppointmentReportProxy> event) {
		EntityProxyId<AppointmentReportProxy> changedId = event.getProxyId();
		List<AppointmentReportProxy> records = cellTable.getVisibleItems();
		int i = 0;
		for (AppointmentReportProxy record : records) {
			if (record != null && changedId.equals(record.stableId())) {
				List<AppointmentReportProxy> changedList = new ArrayList<AppointmentReportProxy>();
				changedList.add(record);
				cellTable.setRowData(i + cellTable.getPageStart(), changedList);
			}
			i++;
		}
	}
}
