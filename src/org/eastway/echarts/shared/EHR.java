package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class EHR implements Serializable {
	private List<Long> patientIds;
	private long ehrId;

	public EHR() { }

	public EHR(long ehrId, List<Long> patientIds) {
		this.setEhrId(ehrId);
		this.setPatientIds(patientIds);
	}

	public void setPatientIds(List<Long> patientIds) {
		this.patientIds = patientIds;
	}

	public List<Long> getPatientIds() {
		return patientIds;
	}

	public void setEhrId(long ehrId) {
		this.ehrId = ehrId;
	}

	public long getEhrId() {
		return ehrId;
	}
}
