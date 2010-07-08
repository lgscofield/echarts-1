package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetAssignmentsResult implements Result {
	private List<Assignment> assignments;

	GetAssignmentsResult() { }

	public GetAssignmentsResult(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}
}
