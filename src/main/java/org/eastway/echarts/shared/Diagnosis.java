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

	public void setId(long id);

	public long getId();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setDate(Date date);

	public Date getDate();

	public void setAxis1A(String axis1A);

	public String getAxis1A();

	public void setAxis1B(String axis1B);

	public String getAxis1B();

	public void setAxis1C(String axis1C);

	public String getAxis1C();

	public void setAxis1D(String axis1D);

	public String getAxis1D();

	public void setAxis1E(String axis1E);

	public String getAxis1E();

	public void setAxis2A(String axis2A);

	public String getAxis2A();

	public void setAxis2B(String axis2B);

	public String getAxis2B();

	public void setAxis2C(String axis2C);

	public String getAxis2C();

	public void setAxis3(String axis3);

	public String getAxis3();

	public void setAxis4(String axis4);

	public String getAxis4();

	public void setCurrentGAF(int currentGAF);

	public int getCurrentGAF();

	public void setHighestGAF(int highestGAF);

	public int getHighestGAF();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public DiagnosisDTO toDto();
}
