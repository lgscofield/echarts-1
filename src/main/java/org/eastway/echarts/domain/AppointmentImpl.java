package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.AppointmentDTO;

@Entity
@Table(name = "Appointment")
public class AppointmentImpl implements Appointment {

	private String activity;
	private Date appointmentDate;
	private String caseNumber;
	private Date endTime;
	@Id
	private long id;
	private String location;
	private String notes;
	private String priority;
	private String staff;
	private Date startTime;

	@Override
	public String getActivity() {
		return activity;
	}

	@Override
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public String getPriority() {
		return priority;
	}

	@Override
	public String getStaff() {
		return staff;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public void setStaff(String staff) {
		this.staff = staff;
	}

	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public AppointmentDTO toDto() {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setActivity(activity);
		dto.setAppointmentDate(appointmentDate);
		dto.setCaseNumber(caseNumber);
		dto.setEndTime(endTime);
		dto.setId(id);
		dto.setLocation(location);
		dto.setNotes(notes);
		dto.setPriority(priority);
		dto.setStaff(staff);
		dto.setStartTime(startTime);
		return dto;
	}

}
