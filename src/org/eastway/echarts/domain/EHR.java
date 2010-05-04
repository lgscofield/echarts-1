package org.eastway.echarts.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EHR {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ehr_id")
	private long id;

	@OneToOne
	@JoinColumn(name="subject_id")
	private Patient subject;

	@Column(name="time_created")
	private Date timeCreated;

	public EHR() { }

	public EHR(long id) {
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setSubject(Patient subject) {
		this.subject = subject;
	}

	public Patient getSubject() {
		return subject;
	}

}
