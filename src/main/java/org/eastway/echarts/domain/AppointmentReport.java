package org.eastway.echarts.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name="rdailyappts")
public class AppointmentReport {

	@Id
	private Long id;
	private String fullName;
	private String caseNumber;
	@Temporal(TemporalType.TIMESTAMP)
	private Date apptDate;
	@Temporal(TemporalType.TIME)
	private Date startTime;
	@Temporal(TemporalType.TIME)
	private Date endTime;
	private String activity;
	private String staffName;
	private String notes;
	@Version
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setApptDate(Date apptDate) {
		this.apptDate = apptDate;
	}

	public Date getApptDate() {
		return apptDate;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Appointment().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static final AppointmentReport findAppointmentReport(Long id) {
		if (id == null)
			return null;
		return entityManager().find(AppointmentReport.class, id);
	}

	public static final Long findAppointmentReportsWithApptDateCount() {
		DateTime startTime = new DateTime()
			.property(DateTimeFieldType.millisOfSecond()).setCopy(0)
			.property(DateTimeFieldType.secondOfMinute()).setCopy(0)
			.property(DateTimeFieldType.minuteOfHour()).setCopy(0)
			.property(DateTimeFieldType.hourOfDay()).setCopy(0);
		DateTime endTime = new DateTime()
			.property(DateTimeFieldType.millisOfSecond()).setCopy(0)
			.property(DateTimeFieldType.secondOfMinute()).setCopy(0)
			.property(DateTimeFieldType.minuteOfHour()).setCopy(0)
			.property(DateTimeFieldType.hourOfDay()).setCopy(23);
		return ((Number) entityManager().createQuery("select count(o) from AppointmentReport o WHERE o.apptDate BETWEEN :startTime AND :endTime", Long.class)
					.setParameter("startTime", startTime.toDate())
					.setParameter("endTime", endTime.toDate())
					.getSingleResult()).longValue();
	}

	public static final List<AppointmentReport> findAppointmentReportsWithApptDate(Integer startPosition, Integer maxResult, Boolean isAscending, String orderBy) {
		if (orderBy == null)
			orderBy = "o.apptDate";
		else
			orderBy = "o." + orderBy;
		String query = "select o from AppointmentReport o WHERE o.apptDate BETWEEN :startTime AND :endTime ORDER BY " + orderBy + " DESC, o.startTime DESC, o.endTime DESC";
		if (isAscending)
			query = "select o from AppointmentReport o WHERE o.apptDate BETWEEN :startTime AND :endTime ORDER BY " + orderBy + " ASC, o.startTime ASC, o.endTime ASC";
		DateTime startTime = new DateTime()
			.property(DateTimeFieldType.millisOfSecond()).setCopy(0)
			.property(DateTimeFieldType.secondOfMinute()).setCopy(0)
			.property(DateTimeFieldType.minuteOfHour()).setCopy(0)
			.property(DateTimeFieldType.hourOfDay()).setCopy(0);
		DateTime endTime = new DateTime()
			.property(DateTimeFieldType.millisOfSecond()).setCopy(0)
			.property(DateTimeFieldType.secondOfMinute()).setCopy(0)
			.property(DateTimeFieldType.minuteOfHour()).setCopy(0)
			.property(DateTimeFieldType.hourOfDay()).setCopy(23);
		return entityManager().createQuery(query, AppointmentReport.class)
			.setParameter("startTime", startTime.toDate())
			.setParameter("endTime", endTime.toDate())
			.setFirstResult(startPosition)
			.setMaxResults(maxResult)
			.getResultList();
	}
}
