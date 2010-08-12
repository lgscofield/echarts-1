package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DiagnosisCodeDTO implements DiagnosisCode, Serializable {

	private String icd9;
	private String description;
	private String dsmiv;

	public DiagnosisCodeDTO() { }

	@Override
	public void setIcd9(String icd9) {
		this.icd9 = icd9;
	}

	@Override
	public String getIcd9() {
		return icd9;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDsmiv(String dsmiv) {
		this.dsmiv = dsmiv;
	}

	@Override
	public String getDsmiv() {
		return dsmiv;
	}

	@Override
	public DiagnosisCode toDto() {
		return this;
	}

}
