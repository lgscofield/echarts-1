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
package org.eastway.echarts.shared;

import java.util.Date;
import java.util.List;

public interface EHR {
	public void setId(long id);

	public long getId();

	public void setTimeCreated(Date timeCreated);

	public Date getTimeCreated();

	public void setSubject(Patient subject);

	public Patient getSubject();

	public void setAssignments(List<Assignment> assignments);

	public List<Assignment> getAssignments();

	public void setDemographics(Demographics demographics);

	public Demographics getDemographics();

	public EHRDTO toDto();

	public void setContacts(List<Contact> contacts);

	public List<Contact> getContacts();

	public List<Diagnosis> getDiagnoses();

	public void setDiagnoses(List<Diagnosis> diagnoses);

	public void setMedications(List<Medication> medications);

	public List<Medication> getMedications();
}
