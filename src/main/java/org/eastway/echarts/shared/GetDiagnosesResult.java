package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetDiagnosesResult implements Result {

	private List<Diagnosis> diagnoses;

	GetDiagnosesResult() { }

	public GetDiagnosesResult(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}
}
