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
import java.util.List;

import org.eastway.echarts.domain.EHR;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(EHR.class)
public interface EHRProxy extends EntityProxy {
	void setId(Long id);

	Long getId();

	void setPatient(PatientProxy patient);

	PatientProxy getPatient();

	void setTimeCreated(Date timeCreated);

	Date getTimeCreated();

	void setDemographics(DemographicsProxy demographics);

	DemographicsProxy getDemographics();

	void setAssignments(List<AssignmentProxy> assignments);

	List<AssignmentProxy> getAssignments();
}
