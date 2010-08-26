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

import java.util.Date;


public interface Diagnosis {

	public void setId(Long id);

	public long getId();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setDate(Date date);

	public Date getDate();

	public void setAxis1A(DiagnosisCode axis1A);

	public DiagnosisCode getAxis1A();

	public void setAxis1B(DiagnosisCode axis1B);

	public DiagnosisCode getAxis1B();

	public void setAxis1C(DiagnosisCode axis1C);

	public DiagnosisCode getAxis1C();

	public void setAxis1D(DiagnosisCode axis1D);

	public DiagnosisCode getAxis1D();

	public void setAxis1E(DiagnosisCode axis1E);

	public DiagnosisCode getAxis1E();

	public void setAxis2A(DiagnosisCode axis2A);

	public DiagnosisCode getAxis2A();

	public void setAxis2B(DiagnosisCode axis2B);

	public DiagnosisCode getAxis2B();

	public void setAxis2C(DiagnosisCode axis2C);

	public DiagnosisCode getAxis2C();

	public void setAxis3(String axis3);

	public String getAxis3();

	public void setAxis4(String axis4);

	public String getAxis4();

	public void setCurrentGAF(Integer currentGAF);

	public int getCurrentGAF();

	public void setHighestGAF(Integer highestGAF);

	public int getHighestGAF();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public DiagnosisDTO toDto();
}
