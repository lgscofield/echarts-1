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
import java.util.List;

import org.eastway.echarts.domain.EHR;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(EHR.class)
public interface EHRProxy extends EntityProxy {
	public void setId(long id);

	public long getId();

	public void setPatient(PatientProxy patient);

	public PatientProxy getPatient();

	public void setTimeCreated(Date timeCreated);

	public Date getTimeCreated();

	public void setDemographics(DemographicsProxy demographics);

	DemographicsProxy getDemographics();

	public void setAssignments(List<AssignmentProxy> assignments);

	public List<AssignmentProxy> getAssignments();

	public EntityProxyId<EHRProxy> stableId();
}
