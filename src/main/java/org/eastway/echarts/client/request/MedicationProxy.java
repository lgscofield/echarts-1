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

import org.eastway.echarts.domain.Medication;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Medication.class)
public interface MedicationProxy extends EntityProxy {
	public void setId(Long id);

	public Long getId();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setMedication(String medication);

	public String getMedication();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEdit);

	public String getLastEditBy();

	public void setMedMonitoring(String medMonitoring);

	public String getMedMonitoring();

	public void setLabsOrdered(String labsOrdered);

	public String getLabsOrdered();
}
