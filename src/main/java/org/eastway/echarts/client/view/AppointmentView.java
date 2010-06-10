/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
		START_TIME,
		END_TIME,
		LOCATION,
		NOTES,
		PRIORITY,
		STAFF,
	}

	public AppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
		appointments.setHTML(record, Column.ACTIVITY.ordinal(), "<b>ACTIVITY</b>");
		appointments.setHTML(record, Column.APPOINTMENT_DATE.ordinal(), "<b>APPOINTMENT DATE</b>");
		appointments.setHTML(record, Column.CASE_NUMBER.ordinal(), "<b>CASE NUMBER</b>");
		appointments.setHTML(record, Column.END_TIME.ordinal(), "<b>END TIME</b>");
		appointments.setHTML(record, Column.LOCATION.ordinal(), "<b>LOCATION</b>");
		appointments.setHTML(record, Column.NOTES.ordinal(), "<b>NOTES</b>");
		appointments.setHTML(record, Column.PRIORITY.ordinal(), "<b>PRIORITY</b>");
		appointments.setHTML(record, Column.STAFF.ordinal(), "<b>STAFF</b>");
		appointments.setHTML(record, Column.START_TIME.ordinal(), "<b>START TIME</b>");
		appointments.setBorderWidth(1);
		nextRecord();
		
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
	public float getEndTime() {
		// TODO Auto-generated method stub
		return 0;
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
	public float getPriority() {
		return 0;
	}

	@Override
	public String getStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getStartTime() {
		// TODO Auto-generated method stub
		return 0;
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
	public void setEndTime(float endTime) {
		this.appointments.setText(record, Column.END_TIME.ordinal(), new Float(endTime).toString());
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
	public void setPriority(float priority) {
		this.appointments.setText(record, Column.PRIORITY.ordinal(), new Float(priority).toString());
	}

	@Override
	public void setStaff(String staff) {
		this.appointments.setText(record, Column.STAFF.ordinal(), staff);
	}

	@Override
	public void setStartTime(float startTime) {
		this.appointments.setText(record, Column.START_TIME.ordinal(), new Float(startTime).toString());
	}

	@Override
	public AppointmentDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
