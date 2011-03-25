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
public class Tickler implements Serializable {
	private String caseNumber;
	private String name;
	private DueDate ispDueDate;
	private Date hipaaDateCompleted;
	private DueDate ispReviewDueDate;
	private DueDate healthHistoryDueDate;
	private DueDate diagnosticAssessmentUpdate;
	private DueDate financialDueDate;
	private DueDate ooc;
	private String service;
	private String staffName;

	public Tickler() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Date getHipaaDateCompleted() {
		return hipaaDateCompleted;
	}

	public void setHipaaDateCompleted(Date hipaaDateCompleted) {
		this.hipaaDateCompleted = hipaaDateCompleted;
	}

	public DueDate getIspDueDate() {
		return ispDueDate;
	}

	public void setIspDueDate(Date dueDate, int dueDateStatus) {
		this.ispDueDate = new DueDate(dueDate, dueDateStatus);
	}

	public DueDate getIspReviewDueDate() {
		return ispReviewDueDate;
	}

	public void setIspReviewDueDate(Date dueDate,
			int dueDateStatus) {
		this.ispReviewDueDate = new DueDate(dueDate, dueDateStatus);
	}

	public DueDate getHealthHistoryDueDate() {
		return healthHistoryDueDate;
	}

	public void setHealthHistoryDueDate(Date dueDate,
			int dueDateStatus) {
		this.healthHistoryDueDate = new DueDate(dueDate, dueDateStatus);
	}

	public DueDate getDiagnosticAssessmentUpdate() {
		return diagnosticAssessmentUpdate;
	}

	public void setDiagnosticAssessmentUpdate(Date dueDate,
			int dueDateStatus) {
		this.diagnosticAssessmentUpdate = new DueDate(dueDate, dueDateStatus);
	}

	public DueDate getFinancialDueDate() {
		return financialDueDate;
	}

	public void setFinancialDueDate(Date dueDate,
			int dueDateStatus) {
		this.financialDueDate = new DueDate(dueDate, dueDateStatus);
	}

	public DueDate getOoc() {
		return ooc;
	}

	public void setOoc(Date dueDate, int dueDateStatus) {
		this.ooc = new DueDate(dueDate, dueDateStatus);
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
}
