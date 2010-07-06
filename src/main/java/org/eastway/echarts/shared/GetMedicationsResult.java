package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetMedicationsResult implements Result {

	private List<Medication> medications;

	GetMedicationsResult() { }

	public GetMedicationsResult(List<Medication> medications) {
		this.medications = medications;
	}

	public List<Medication> getMedications() {
		return medications;
	}
}
