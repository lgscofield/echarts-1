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
public class DiagnosisDTO implements Diagnosis, Serializable {

	private DiagnosisCode axis1A;
	private DiagnosisCode axis1B;
	private DiagnosisCode axis1C;
	private DiagnosisCode axis1D;
	private DiagnosisCode axis1E;
	private DiagnosisCode axis2A;
	private DiagnosisCode axis2B;
	private DiagnosisCode axis2C;
	private String axis3;
	private String axis4;
	private String caseNumber;
	private int currentGAF;
	private Date date;
	private int highestGAF;
	private long id;
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
		this.axis1A = axis1A;
	}

	@Override
	public void setAxis1B(DiagnosisCode axis1B) {
		this.axis1B = axis1B;
	}

	@Override
	public void setAxis1C(DiagnosisCode axis1C) {
		this.axis1C = axis1C;
	}

	@Override
	public void setAxis1D(DiagnosisCode axis1D) {
		this.axis1D = axis1D;
	}

	@Override
	public void setAxis1E(DiagnosisCode axis1E) {
		this.axis1E = axis1E;
	}

	@Override
	public void setAxis2A(DiagnosisCode axis2A) {
		this.axis2A = axis2A;
	}

	@Override
	public void setAxis2B(DiagnosisCode axis2B) {
		this.axis2B = axis2B;
	}

	@Override
	public void setAxis2C(DiagnosisCode axis2C) {
		this.axis2C = axis2C;
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
	public void setCurrentGAF(int currentGAF) {
		this.currentGAF = currentGAF;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void setHighestGAF(int highestGAF) {
		this.highestGAF = highestGAF;
	}

	@Override
	public void setId(long id) {
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
		return this;
	}
}
