package org.eastway.echarts.client.ui;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.request.PlaceLogRecordProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;

public class PlaceLogRecordListViewImpl extends Composite implements PlaceLogRecordListView {

	private static BINDER uiBinder = GWT
			.create(BINDER.class);

	@UiTemplate("PlaceLogRecordListView.ui.xml")
	interface BINDER extends
			UiBinder<Widget, PlaceLogRecordListViewImpl> {
	}

	@UiField(provided=true) CellTable<PlaceLogRecordProxy> cellTable;

	@UiField SimplePager pager;

	AsyncHandler sortHandler;

	Presenter presenter;

	AsyncDataProvider<PlaceLogRecordProxy> dataProvider = new AsyncDataProvider<PlaceLogRecordProxy>() {
		@Override
		protected void onRangeChanged(HasData<PlaceLogRecordProxy> display) {
			if (presenter != null)
				presenter.requestData();
		}
	};

	public PlaceLogRecordListViewImpl() {
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
		cellTable = new CellTable<PlaceLogRecordProxy>(20);
		sortHandler = new AsyncHandler(cellTable);
		cellTable.addColumnSortHandler(sortHandler);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		final NoSelectionModel<PlaceLogRecordProxy> selectionModel = new NoSelectionModel<PlaceLogRecordProxy>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// when we support editing this entity...
			}
		});
		cellTable.setSelectionModel(selectionModel);

		cellTable.addColumn(new TextColumn<PlaceLogRecordProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(PlaceLogRecordProxy object) {
				return renderer.render(object.getUsername());
			}
		}, "Username");
		cellTable.addColumn(new TextColumn<PlaceLogRecordProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(PlaceLogRecordProxy object) {
				return renderer.render(object.getAppVersion());
			}
		}, "Echarts Version");
		cellTable.addColumn(new TextColumn<PlaceLogRecordProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(PlaceLogRecordProxy object) {
				return renderer.render(object.getUrl());
			}
		}, "URL");
		cellTable.addColumn(new TextColumn<PlaceLogRecordProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(GlobalResources.getDateTimeFormat());
			@Override
			public String getValue(PlaceLogRecordProxy object) {
				if (object.getTimestamp() != null)
					return renderer.render(new Date(object.getTimestamp()));
				else
					return null;
			}
		}, "Timestamp");
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
	public void setRowData(int pageStart, List<PlaceLogRecordProxy> rowData) {
		cellTable.setRowData(pageStart, rowData);
	}

	@Override
	public ColumnSortList getColumnSortList() {
		return cellTable.getColumnSortList();
	}

	@Override
	public String getColumnName(Column<?, ?> column) {
		return null;
	}

	@Override
	public void setVisibleRange(Range range) {
		cellTable.setVisibleRange(range);
	}

}
