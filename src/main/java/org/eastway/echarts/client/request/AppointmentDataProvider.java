package org.eastway.echarts.client.request;

import java.util.Date;
import java.util.List;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

public class AppointmentDataProvider extends AsyncDataProvider<AppointmentProxy> {

	private EchartsRequestFactory requestFactory;
	private String caseNumber;
	private Date startDate;

	@Inject
	public AppointmentDataProvider(EchartsRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	@Override
	protected void onRangeChanged(HasData<AppointmentProxy> display) {
		final Range range = display.getVisibleRange();
		final int length = range.getLength();
		final int start = range.getStart();

		if (startDate == null) {
			requestFactory.appointmentRequest().findAppointmentEntriesByCaseNumber(start, length, caseNumber)
					.fire(new Receiver<List<AppointmentProxy>>() {
				@Override
				public void onSuccess(List<AppointmentProxy> response) {
					setData(start, length, response);
				}
			});
		} else {
			requestFactory.appointmentRequest().findAppointmentEntriesByCaseNumberAndDate(start, length, caseNumber, startDate)
					.fire(new Receiver<List<AppointmentProxy>>() {
				@Override
				public void onSuccess(List<AppointmentProxy> response) {
					setData(start, length, response);
				}
			});
		}
	}

	private void setData(int start, int length, List<AppointmentProxy> data) {
		if (data != null && data.size() != 0) {
			updateRowCount(start, false);
			updateRowData(start, data);
		} else {
			updateRowCount(start, true);
			updateRowData(start, data);
		}
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setStartDate(Date value) {
		this.startDate = value;
	}
}
