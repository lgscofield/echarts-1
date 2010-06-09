package org.eastway.echarts.client.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.AppointmentPresenter;
import org.eastway.echarts.shared.AppointmentDTO;

public class AppointmentView extends Composite implements AppointmentPresenter.Display {

	private static AppointmentViewUiBinder uiBinder = GWT
			.create(AppointmentViewUiBinder.class);

	interface AppointmentViewUiBinder extends UiBinder<Widget, AppointmentView> { }

	private int record = 0;
	@UiField FlexTable appointments;

	enum Column {
		ACTIVITY,
		APPOINTMENT_DATE,
		CASE_NUMBER,
		END_TIME,
		LOCATION,
		NOTES,
		PRIORITY,
		STAFF,
		START_TIME,
	}

	public AppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void nextRecord() {
		record++;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public String getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getAppointmentDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActivity(String activity) {
		this.appointments.setText(record, Column.ACTIVITY.ordinal(), activity);
	}

	@Override
	public void setAppointmentDate(Date appointmentDate) {
		this.appointments.setText(record, Column.APPOINTMENT_DATE.ordinal(), appointmentDate.toString());
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.appointments.setText(record, Column.CASE_NUMBER.ordinal(), caseNumber);
	}

	@Override
	public void setEndTime(Date endTime) {
		this.appointments.setText(record, Column.END_TIME.ordinal(), endTime.toString());
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(String location) {
		this.appointments.setText(record, Column.LOCATION.ordinal(), location);
	}

	@Override
	public void setNotes(String notes) {
		this.appointments.setText(record, Column.NOTES.ordinal(), notes);
	}

	@Override
	public void setPriority(String priority) {
		this.appointments.setText(record, Column.PRIORITY.ordinal(), priority);
	}

	@Override
	public void setStaff(String staff) {
		this.appointments.setText(record, Column.STAFF.ordinal(), staff);
	}

	@Override
	public void setStartTime(Date startTime) {
		this.appointments.setText(record, Column.START_TIME.ordinal(), startTime.toString());
	}

	@Override
	public AppointmentDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
