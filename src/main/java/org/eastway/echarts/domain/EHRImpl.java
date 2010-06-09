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
package org.eastway.echarts.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.Medication;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.PatientDTO;

@Entity
@Table(name = "EHR")
public class EHRImpl implements EHR {
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	@Column(name="ehr_id")
	private long id;

	@OneToMany(targetEntity = AssignmentImpl.class)
	@JoinColumn
	@OrderBy(value="orderDate DESC")
	private List<AssignmentImpl> assignments;

	@OneToOne(targetEntity = PatientImpl.class)
	@JoinColumn(name="subject_id")
	private PatientImpl subject;

	@Column(name="time_created")
	private Date timeCreated;

	@OneToOne(targetEntity = DemographicsImpl.class)
	@JoinColumn
	private DemographicsImpl demographics;

	@OneToMany(targetEntity = ContactImpl.class)
	@JoinColumn
	private List<ContactImpl> contacts;

	@OneToMany(targetEntity = DiagnosisImpl.class)
	@JoinColumn
	private List<DiagnosisImpl> diagnoses;

	@OneToMany(targetEntity = AppointmentImpl.class)
	@JoinColumn
	private List<AppointmentImpl> appointments;

	@OneToMany(targetEntity = MedicationImpl.class)
	@JoinColumn
	private List<MedicationImpl> medications;

	public EHRImpl() { }

	public EHRImpl(long id) {
		this.id = id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	@Override
	public Date getTimeCreated() {
		return timeCreated;
	}

	@Override
	public void setSubject(Patient subject) {
		this.subject = (PatientImpl) subject;
	}

	@Override
	public Patient getSubject() {
		return subject;
	}

	@Override
	public void setAssignments(List<Assignment> assignments) {
		this.assignments.clear();
		for (Assignment assignment : assignments)
			this.assignments.add((AssignmentImpl) assignment);
	}

	@Override
	public List<Assignment> getAssignments() {
		List<Assignment> assignments = new ArrayList<Assignment>();
		for (AssignmentImpl assignment : this.assignments)
			assignments.add(assignment);
		return assignments;
	}

	@Override
	public Demographics getDemographics() {
		return demographics;
	}

	@Override
	public void setDemographics(Demographics demographics) {
		this.demographics = (DemographicsImpl) demographics;
	}

	@Override
	public void setContacts(List<Contact> contacts) {
		this.contacts.clear();
		for (Contact contact : contacts)
			this.contacts.add((ContactImpl) contact);
	}

	@Override
	public List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		for (ContactImpl contact : this.contacts)
			contacts.add(contact);
		return contacts;
	}

	@Override
	public List<Diagnosis> getDiagnoses() {
		List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
		for (DiagnosisImpl diagnosis : this.diagnoses)
			diagnoses.add(diagnosis);
		return diagnoses;
	}

	@Override
	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses.clear();
		for (Diagnosis diagnosis : diagnoses)
			this.diagnoses.add((DiagnosisImpl) diagnosis);
	}

	@Override
	public List<Appointment> getAppointments() {
		List<Appointment> appointments = new ArrayList<Appointment>();
		for (AppointmentImpl appointment : this.appointments)
			appointments.add(appointment);
		return appointments;
	}

	@Override
	public void setAppointments(List<Appointment> appointments) {
		this.appointments.clear();
		for (Appointment appointment : appointments)
			this.appointments.add((AppointmentImpl) appointment);
	}

	@Override
	public List<Medication> getMedications() {
		List<Medication> medications = new ArrayList<Medication>();
		for (MedicationImpl medication : this.medications)
			medications.add(medication);
		return medications;
	}

	@Override
	public void setMedications(List<Medication> medications) {
		this.medications.clear();
		for (Medication medication : medications)
			this.medications.add((MedicationImpl) medication);
	}

	@Override
	public EHRDTO toDto() {
		EHRDTO ehrDto = new EHRDTO();
		ehrDto.setId(this.getId());
		ehrDto.setSubject((PatientDTO) this.getSubject().toDto());
		ehrDto.setTimeCreated(this.getTimeCreated());

		List<Assignment> assignments = new ArrayList<Assignment>();
		for (Assignment a : this.assignments)
			assignments.add(a.toDto());
		ehrDto.setAssignments(assignments);

		List<Contact> contacts = new ArrayList<Contact>();
		for (Contact c : this.contacts)
			contacts.add(c.toDto());
		ehrDto.setContacts(contacts);

		List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
		for (Diagnosis d : this.diagnoses)
			diagnoses.add(d.toDto());
		ehrDto.setDiagnoses(diagnoses);

		List<Appointment> appointments = new ArrayList<Appointment>();
		for (Appointment a : this.appointments)
			appointments.add(a.toDto());
		ehrDto.setAppointments(appointments);

		List<Medication> medications = new ArrayList<Medication>();
		for (Medication m : this.medications)
			medications.add(m.toDto());
		ehrDto.setMedications(medications);

		return ehrDto;
	}

}
