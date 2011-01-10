package org.eastway.echarts.client.request;

import java.util.List;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

public class AppointmentDataProvider extends AsyncDataProvider<AppointmentProxy> {

	private EchartsRequestFactory requestFactory;
	private String caseNumber;

	@Inject
	public AppointmentDataProvider(EchartsRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	@Override
	protected void onRangeChanged(HasData<AppointmentProxy> display) {
		final Range range = display.getVisibleRange();
		final int length = range.getLength();
		final int start = range.getStart();

		requestFactory.appointmentRequest().findAppointmentEntriesByCaseNumber(start, length, caseNumber)
				.fire(new Receiver<List<AppointmentProxy>>() {
			@Override
			public void onSuccess(List<AppointmentProxy> response) {
				updateRowCount(response.size(), false);
				updateRowData(start, response);
			}
		});
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
}
