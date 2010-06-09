package org.eastway.echarts.client.presenter;

import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppointmentPresenter extends Presenter<AppointmentPresenter.Display> {

	public interface Display extends EchartsDisplay, Appointment {
		public void nextRecord();
	}

	private EHR ehr;

	public AppointmentPresenter(Display display, HandlerManager eventBus, EHR ehr) {
		super(display, eventBus);
		this.ehr = ehr;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setData();
	}

	public void setData() {
		for (Appointment a : ehr.getAppointments()) {
			display.setActivity(a.getActivity());
			display.setAppointmentDate(a.getAppointmentDate());
			display.setCaseNumber(a.getCaseNumber());
			display.setEndTime(a.getEndTime());
			display.setId(a.getId());
			display.setLocation(a.getLocation());
			display.setNotes(a.getNotes());
			display.setPriority(a.getPriority());
			display.setStaff(a.getStaff());
			display.setStartTime(a.getStartTime());
			display.nextRecord();
		}
	}
}
