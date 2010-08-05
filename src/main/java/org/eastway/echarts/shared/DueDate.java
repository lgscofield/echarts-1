package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DueDate implements Serializable {
	String dueDate;
	int status;

	DueDate() {	}

	DueDate(String formatDueDate, int status) {
		this.dueDate = formatDueDate;
		this.status = status;
	}

	public String getDueDate() {
		return dueDate;
	}

	public int getStatus() {
		return status;
	}
}
