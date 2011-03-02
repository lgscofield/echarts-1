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
package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.Diagnosis;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Diagnosis.class)
public interface DiagnosisProxy extends EntityProxy {

	DiagnosisCodeProxy getAxis1A();

	DiagnosisCodeProxy getAxis1B();

	DiagnosisCodeProxy getAxis1C();

	DiagnosisCodeProxy getAxis1D();

	DiagnosisCodeProxy getAxis1E();

	DiagnosisCodeProxy getAxis2A();

	DiagnosisCodeProxy getAxis2B();

	DiagnosisCodeProxy getAxis2C();

	String getAxis3();

	String getAxis4();

	String getCaseNumber();

	String getCurrentGAF();

	Date getDate();

	String getHighestGAF();

	Long getId();

	Date getLastEdit();

	String getLastEditBy();

	void setAxis1A(DiagnosisCodeProxy axis1A);

	void setAxis1B(DiagnosisCodeProxy axis1B);

	void setAxis1C(DiagnosisCodeProxy axis1C);

	void setAxis1D(DiagnosisCodeProxy axis1D);

	void setAxis1E(DiagnosisCodeProxy axis1E);

	void setAxis2A(DiagnosisCodeProxy axis2A);

	void setAxis2B(DiagnosisCodeProxy axis2B);

	void setAxis2C(DiagnosisCodeProxy axis2C);

	void setAxis3(String axis3);

	void setAxis4(String axis4);

	void setCaseNumber(String caseNumber);

	void setCurrentGAF(String currentGAF);

	void setDate(Date date);

	void setHighestGAF(String highestGAF);

	void setId(Long id);

	void setLastEdit(Date lastEdit);

	void setLastEditBy(String lastEditBy);

	void setVersion(Integer version);

	Integer getVersion();

}