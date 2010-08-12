package org.eastway.echarts.shared;

public interface DiagnosisCode {

	public void setIcd9(String icd9);

	public String getIcd9();

	public void setDescription(String description);

	public String getDescription();

	public void setDsmiv(String dsmiv);

	public String getDsmiv();

	public DiagnosisCode toDto();
}