package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetAppointmentsResult implements Result {

	private List<Appointment> appointments;

	GetAppointmentsResult() { }

	public GetAppointmentsResult(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}
}
