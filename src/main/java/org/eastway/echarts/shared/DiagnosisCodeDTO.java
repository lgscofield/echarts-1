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
