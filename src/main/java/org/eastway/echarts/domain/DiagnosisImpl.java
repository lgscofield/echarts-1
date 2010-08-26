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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.DiagnosisCode;
import org.eastway.echarts.shared.DiagnosisCodeDTO;
import org.eastway.echarts.shared.DiagnosisDTO;

@Entity
@Table(name = "Diagnosis")
public class DiagnosisImpl implements Diagnosis {

	@ManyToOne
	@JoinColumn(name = "axis1a")
	private DiagnosisCodeImpl axis1A;
	@ManyToOne
	@JoinColumn(name = "axis1b")
	private DiagnosisCodeImpl axis1B;
	@ManyToOne
	@JoinColumn(name = "axis1c")
	private DiagnosisCodeImpl axis1C;
	@ManyToOne
	@JoinColumn(name = "axis1d")
	private DiagnosisCodeImpl axis1D;
	@ManyToOne
	@JoinColumn(name = "axis1e")
	private DiagnosisCodeImpl axis1E;
	@ManyToOne
	@JoinColumn(name = "axis2a")
	private DiagnosisCodeImpl axis2A;
	@ManyToOne
	@JoinColumn(name = "axis2b")
	private DiagnosisCodeImpl axis2B;
	@ManyToOne
	@JoinColumn(name = "axis2c")
	private DiagnosisCodeImpl axis2C;
	private String axis3;
	private String axis4;
	private String caseNumber;
	private Integer currentGAF;
	private Date date;
	private Integer highestGAF;
	@Id
	private Long id;
	private Date lastEdit;
	private String lastEditBy;

	@Override
	public DiagnosisCode getAxis1A() {
		return axis1A;
	}

	@Override
	public DiagnosisCode getAxis1B() {
		return axis1B;
	}

	@Override
	public DiagnosisCode getAxis1C() {
		return axis1C;
	}

	@Override
	public DiagnosisCode getAxis1D() {
		return axis1D;
	}

	@Override
	public DiagnosisCode getAxis1E() {
		return axis1E;
	}

	@Override
	public DiagnosisCode getAxis2A() {
		return axis2A;
	}

	@Override
	public DiagnosisCode getAxis2B() {
		return axis2B;
	}

	@Override
	public DiagnosisCode getAxis2C() {
		return axis2C;
	}

	@Override
	public String getAxis3() {
		return axis3;
	}

	@Override
	public String getAxis4() {
		return axis4;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public int getCurrentGAF() {
		return currentGAF;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public int getHighestGAF() {
		return highestGAF;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public void setAxis1A(DiagnosisCode axis1A) {
		this.axis1A = (DiagnosisCodeImpl) axis1A;
	}

	@Override
	public void setAxis1B(DiagnosisCode axis1B) {
		this.axis1B = (DiagnosisCodeImpl) axis1B;
	}

	@Override
	public void setAxis1C(DiagnosisCode axis1C) {
		this.axis1C = (DiagnosisCodeImpl) axis1C;
	}

	@Override
	public void setAxis1D(DiagnosisCode axis1D) {
		this.axis1D = (DiagnosisCodeImpl) axis1D;
	}

	@Override
	public void setAxis1E(DiagnosisCode axis1E) {
		this.axis1E = (DiagnosisCodeImpl) axis1E;
	}

	@Override
	public void setAxis2A(DiagnosisCode axis2A) {
		this.axis2A = (DiagnosisCodeImpl) axis2A;
	}

	@Override
	public void setAxis2B(DiagnosisCode axis2B) {
		this.axis2B = (DiagnosisCodeImpl) axis2B;
	}

	@Override
	public void setAxis2C(DiagnosisCode axis2C) {
		this.axis2C = (DiagnosisCodeImpl) axis2C;
	}

	@Override
	public void setAxis3(String axis3) {
		this.axis3 = axis3;
	}

	@Override
	public void setAxis4(String axis4) {
		this.axis4 = axis4;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setCurrentGAF(Integer currentGAF) {
		this.currentGAF = currentGAF;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void setHighestGAF(Integer highestGAF) {
		this.highestGAF = highestGAF;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public DiagnosisDTO toDto() {
		DiagnosisDTO dto = new DiagnosisDTO();
		dto.setAxis1A((axis1A == null) ? new DiagnosisCodeDTO() : axis1A.toDto());
		dto.setAxis1B((axis1B == null) ? new DiagnosisCodeDTO() : axis1B.toDto());
		dto.setAxis1C((axis1C == null) ? new DiagnosisCodeDTO() : axis1C.toDto());
		dto.setAxis1D((axis1D == null) ? new DiagnosisCodeDTO() : axis1D.toDto());
		dto.setAxis1E((axis1E == null) ? new DiagnosisCodeDTO() : axis1E.toDto());
		dto.setAxis2A((axis2A == null) ? new DiagnosisCodeDTO() : axis2A.toDto());
		dto.setAxis2B((axis2B == null) ? new DiagnosisCodeDTO() : axis2B.toDto());
		dto.setAxis2C((axis2C == null) ? new DiagnosisCodeDTO() : axis2C.toDto());
		dto.setAxis3(axis3);
		dto.setAxis4(axis4);
		dto.setCaseNumber(caseNumber);
		dto.setCurrentGAF(currentGAF);
		dto.setDate(date);
		dto.setHighestGAF(highestGAF);
		dto.setId(id);
		dto.setLastEdit(lastEdit);
		dto.setLastEditBy(lastEditBy);
		return dto;
	}

}
