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

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AssignmentDTO implements Serializable {
	private long id;
	private EHRDTO ehr;
	private Date assignmentDate;
	private String service;
	private String staff;
	private Date orderDate;
	private String disposition;
	private String staffName;
	private Date termDate;
	private int planId;
	private int trtEpisode;
	private String program;
	private Date lastEdit;
	private String lastEditBy;

	public AssignmentDTO() { }

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setEhr(EHRDTO ehr) {
		this.ehr = ehr;
	}

	public EHRDTO getEhr() {
		return this.ehr;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return this.service;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getStaff() {
		return this.staff;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDisposition() {
		return this.disposition;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public Date getTermDate() {
		return this.termDate;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPlanId() {
		return this.planId;
	}

	public void setTrtEpisode(int trtEpisode) {
		this.trtEpisode = trtEpisode;
	}

	public int getTrtEpisode() {
		return this.trtEpisode;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getProgram() {
		return this.program;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return this.lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return this.lastEditBy;
	}
}
