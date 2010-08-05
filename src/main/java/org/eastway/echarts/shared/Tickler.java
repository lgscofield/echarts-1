package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tickler implements Serializable {
	private String caseNumber;
	private String name;
	private DueDate ispDueDate;
	private String hipaaDateCompleted;
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

	public String getHipaaDateCompleted() {
		return hipaaDateCompleted;
	}

	public void setHipaaDateCompleted(String hipaaDateCompleted) {
		this.hipaaDateCompleted = hipaaDateCompleted;
	}

	public DueDate getIspDueDate() {
		return ispDueDate;
	}

	public void setIspDueDate(String formatDueDate, int dueDateStatus) {
		this.ispDueDate = new DueDate(formatDueDate, dueDateStatus);
	}

	public DueDate getIspReviewDueDate() {
		return ispReviewDueDate;
	}

	public void setIspReviewDueDate(String formatDueDate,
			int dueDateStatus) {
		this.ispReviewDueDate = new DueDate(formatDueDate, dueDateStatus);
	}

	public DueDate getHealthHistoryDueDate() {
		return healthHistoryDueDate;
	}

	public void setHealthHistoryDueDate(String formatDueDate,
			int dueDateStatus) {
		this.healthHistoryDueDate = new DueDate(formatDueDate, dueDateStatus);
	}

	public DueDate getDiagnosticAssessmentUpdate() {
		return diagnosticAssessmentUpdate;
	}

	public void setDiagnosticAssessmentUpdate(String formatDueDate,
			int dueDateStatus) {
		this.diagnosticAssessmentUpdate = new DueDate(formatDueDate, dueDateStatus);
	}

	public DueDate getFinancialDueDate() {
		return financialDueDate;
	}

	public void setFinancialDueDate(String formatDueDate,
			int dueDateStatus) {
		this.financialDueDate = new DueDate(formatDueDate, dueDateStatus);
	}

	public DueDate getOoc() {
		return ooc;
	}

	public void setOoc(String formatDueDate, int dueDateStatus) {
		this.ooc = new DueDate(formatDueDate, dueDateStatus);
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
