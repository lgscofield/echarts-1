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

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Diagnosis.class)
public interface DiagnosisProxy extends EntityProxy {

	DiagnosisCodeProxy getAxis1A();

	String getProvision1A();

	DiagnosisCodeProxy getAxis1B();

	String getProvision1B();

	DiagnosisCodeProxy getAxis1C();

	String getProvision1C();

	DiagnosisCodeProxy getAxis1D();

	String getProvision1D();

	DiagnosisCodeProxy getAxis1E();

	String getProvision1E();

	DiagnosisCodeProxy getAxis2A();

	String getProvision2A();

	DiagnosisCodeProxy getAxis2B();

	String getProvision2B();

	DiagnosisCodeProxy getAxis2C();

	String getProvision2C();

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

	void setProvision1A(String provision1A);

	void setAxis1B(DiagnosisCodeProxy axis1B);

	void setProvision1B(String provision1B);

	void setAxis1C(DiagnosisCodeProxy axis1C);

	void setProvision1C(String provision1C);

	void setAxis1D(DiagnosisCodeProxy axis1D);

	void setProvision1D(String provision1D);

	void setAxis1E(DiagnosisCodeProxy axis1E);

	void setProvision1E(String provision1E);

	void setAxis2A(DiagnosisCodeProxy axis2A);

	void setProvision2A(String provision2A);

	void setAxis2B(DiagnosisCodeProxy axis2B);

	void setProvision2B(String provision2B);

	void setAxis2C(DiagnosisCodeProxy axis2C);

	void setProvision2C(String provision2C);

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