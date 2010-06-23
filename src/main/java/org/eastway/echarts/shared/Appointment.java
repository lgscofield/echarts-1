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

public interface Appointment {

	public void setId(int id);

	public int getId();

	public void setAppointmentDate(Date appointmentDate);

	public Date getAppointmentDate();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setStartTime(float startTime);

	public float getStartTime();

	public void setEndTime(float endTime);

	public float getEndTime();

	public void setActivity(String activity);

	public String getActivity();

	public void setPriority(float priority);

	public float getPriority();

	public void setStaff(String staff);

	public String getStaff();

	public void setLocation(String location);

	public String getLocation();

	public void setNotes(String notes);

	public String getNotes();

	public AppointmentDTO toDto();

}