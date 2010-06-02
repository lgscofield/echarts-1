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
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.AssignmentDTO;
import org.eastway.echarts.shared.EHRDTO;

@Entity
public class EHR {
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	@Column(name="ehr_id")
	private long id;

	@OneToMany
	@OrderBy(value="orderDate DESC")
	private List<Assignment> assignments;

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

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public EHRDTO toDto() {
		EHRDTO ehrDto = new EHRDTO();
		ehrDto.setId(this.getId());
		ehrDto.setSubject(this.getSubject().toDto());
		ehrDto.setTimeCreated(this.getTimeCreated());
		List<AssignmentDTO> assignments = new ArrayList<AssignmentDTO>();
		for (Assignment a : this.assignments)
			assignments.add(a.toDto());
		ehrDto.setAssignments(assignments);

		return ehrDto;
	}
}
