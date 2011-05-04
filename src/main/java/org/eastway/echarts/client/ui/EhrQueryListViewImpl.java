package org.eastway.echarts.client.ui;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.EHRProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;

public class EhrQueryListViewImpl extends Composite implements EhrQueryListView {

	private static PatientQueryListViewImplUiBinder uiBinder = GWT
			.create(PatientQueryListViewImplUiBinder.class);

	@UiTemplate("EhrQueryListView.ui.xml")
	interface PatientQueryListViewImplUiBinder extends
			UiBinder<Widget, EhrQueryListViewImpl> {
	}

	@UiField(provided=true) CellTable<EHRProxy> table;

	@UiField SimplePager pager;

	private Presenter presenter;

	AsyncDataProvider<EHRProxy> dataProvider = new AsyncDataProvider<EHRProxy>() {
		@Override
		protected void onRangeChanged(HasData<EHRProxy> display) {
			if (presenter != null)
				presenter.fetchData();
		}
	};

	private AsyncHandler sortHandler;

	private TextColumn<EHRProxy> nameColumn;

	private TextColumn<EHRProxy> caseNumberColumn;

	private NoSelectionModel<EHRProxy> selectionModel = new NoSelectionModel<EHRProxy>();

	private TextColumn<EHRProxy> genderColumn;

	private TextColumn<EHRProxy> ageColumn;

	private TextColumn<EHRProxy> staffNameColumn;;

	public EhrQueryListViewImpl() {
		createTable();
		initWidget(uiBinder.createAndBindUi(this));
		dataProvider.addDataDisplay(table);
	}

	@UiFactory
	SimplePager createPager() {
		SimplePager p = new SimplePager(TextLocation.RIGHT);
		p.setRangeLimited(true);
		p.setDisplay(table);
		return p;
	}

	private void createTable() {
		table = new CellTable<EHRProxy>(20);
		sortHandler = new AsyncHandler(table);
		table.addColumnSortHandler(sortHandler);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				EHRProxy selected = selectionModel.getLastSelectedObject();
				if (selected != null && selected.getPatient() != null)
					presenter.goTo(new PatientSummaryPlace(selected.getPatient().getCaseNumber()));
			}
		});
		table.setSelectionModel(selectionModel);
		nameColumn = new TextColumn<EHRProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(EHRProxy object) {
				if (object.getPatient() != null)
					return renderer.render(object.getPatient().getName());
				return null;
			}
		};
		nameColumn.setSortable(true);
		table.addColumn(nameColumn, "Name");
		caseNumberColumn = new TextColumn<EHRProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(EHRProxy object) {
				if (object.getPatient() != null)
					return renderer.render(object.getPatient().getCaseNumber());
				return null;
			}
		};
		caseNumberColumn.setSortable(true);
		table.addColumn(caseNumberColumn, "Case #");
		genderColumn = new TextColumn<EHRProxy>() {
			@Override
			public String getValue(EHRProxy object) {
				if (object.getDemographics() != null)
					return CodeProxyRenderer.instance().render(object.getDemographics().getGender());
				return null;
			}
		};
		table.addColumn(genderColumn, "Gender");
		ageColumn = new TextColumn<EHRProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};

			@Override
			public String getValue(EHRProxy object) {
				if (object.getDemographics() != null && object.getDemographics().getDob() != null)
					return renderer.render(new Long((new Date().getTime() - object.getDemographics().getDob().getTime()) / (3600*24*365) / 1000).toString());
				return null;
			}
		};
		ageColumn.setSortable(true);
		table.addColumn(ageColumn, "Age");
		staffNameColumn = new TextColumn<EHRProxy>() {
			@Override
			public String getValue(EHRProxy object) {
				if (object.getAssignments() != null && object.getAssignments().size() != 0) {
					StringBuilder sb = new StringBuilder();
					int size = object.getAssignments().size();
					for (AssignmentProxy a : object.getAssignments()) {
						if (size > 1) {
							sb.append("\"");
							AssignmentProxyRenderer.instance().render(a, sb);
							sb.append("\", ");
							size--;
						} else {
							sb.append("\"");
							AssignmentProxyRenderer.instance().render(a, sb);
							sb.append("\"");
						}
					}
					return sb.toString();
				}
				return null;
			}
		};
		table.addColumn(staffNameColumn, "Assigned To");
		table.getColumnSortList().push(nameColumn);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Range getVisibleRange() {
		return table.getVisibleRange();
	}

	@Override
	public void setRowCount(int count, boolean b) {
		table.setRowCount(count, b);
	}

	@Override
	public int getPageSize() {
		return table.getPageSize();
	}

	@Override
	public int getPageStart() {
		return table.getPageStart();
	}

	@Override
	public void setRowData(int pageStart, List<EHRProxy> response) {
		table.setRowData(pageStart, response);
	}

	@Override
	public ColumnSortList getColumnSortList() {
		return table.getColumnSortList();
	}

	@Override
	public String getColumnName(Column<?, ?> column) {
		if (column.equals(nameColumn)) {
			return "patient.lastName";
		} else if (column.equals(caseNumberColumn)) {
			return "patient.caseNumber";
		} else if (column.equals(ageColumn)) {
			return "demographics.dob";
		} else {
			return null;
		}
	}
}
