package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.DiagnosisCode;
import org.eastway.echarts.shared.DiagnosisCodeDTO;

@Entity
@Table(name = "diagnosisaxis")
public class DiagnosisCodeImpl implements DiagnosisCode {

	@Id
	private String icd9;
	private String description;
	private String dsmiv;

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
		DiagnosisCodeDTO dto = new DiagnosisCodeDTO();
		dto.setDescription(description);
		dto.setDsmiv(dsmiv);
		dto.setIcd9(icd9);
		return dto;
	}
}
