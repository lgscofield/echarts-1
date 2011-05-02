package org.eastway.echarts.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.request.AppointmentReportProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.ui.AppointmentListView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.EntityProxyChange;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.Range;

public class AppointmentListActivity implements Activity, AppointmentListView.Presenter, EntityProxyChange.Handler<AppointmentReportProxy> {

	private EchartsRequestFactory requestFactory;
	private AppointmentListView view;
	private Receiver<Long> lastDataSizeReceiver;
	private Receiver<List<AppointmentReportProxy>> lastDataReceiver;

	public AppointmentListActivity(EchartsRequestFactory requestFactory, AppointmentListView view) {
		this.requestFactory = requestFactory;
		this.view = view;
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
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		EntityProxyChange.registerForProxyType(eventBus, AppointmentReportProxy.class, this);
		requestReports();
		view.setPresenter(this);
		panel.setWidget(view);
	}

	public void requestReports() {
		Range range = view.getVisibleRange();

		lastDataSizeReceiver = new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				if (this == lastDataSizeReceiver) {
					int count = response.intValue();
					view.setRowCount(count, count != 1000);
				}
			}
		};

		requestFactory.appointmentReportRequest().findAppointmentReportsWithApptDateCount().fire(lastDataSizeReceiver);

		lastDataReceiver = new Receiver<List<AppointmentReportProxy>>() {
			@Override
			public void onSuccess(List<AppointmentReportProxy> response) {
				if (this == lastDataReceiver) {
					int size = response.size();
					if (size < view.getPageSize()) {
						view.setRowCount(view.getPageStart() + size, true);
					}
					if (size > 0) {
						view.setRowData(view.getPageStart(), response);
					}
				}
			}
		};
		ColumnSortInfo sortInfo = view.getColumnSortList().get(0);
		String columnName = view.getColumnName(sortInfo.getColumn());
		boolean isAscending = view.getColumnSortList().get(0).isAscending();
		requestFactory.appointmentReportRequest().findAppointmentReportsWithApptDate(range.getStart(), range.getLength(), isAscending, columnName).fire(lastDataReceiver);
	}

	@Override
	public void onProxyChange(EntityProxyChange<AppointmentReportProxy> event) {
		EntityProxyId<AppointmentReportProxy> changedId = event.getProxyId();
		List<AppointmentReportProxy> records = view.getVisibleItems();
		int i = 0;
		for (AppointmentReportProxy record : records) {
			if (record != null && changedId.equals(record.stableId())) {
				List<AppointmentReportProxy> changedList = new ArrayList<AppointmentReportProxy>();
				changedList.add(record);
				view.setRowData(i + view.getPageStart(), changedList);
			}
			i++;
		}
	}

}
