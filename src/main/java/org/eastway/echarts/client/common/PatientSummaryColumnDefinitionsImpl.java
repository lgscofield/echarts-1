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
package org.eastway.echarts.client.common;

import java.util.ArrayList;

import org.eastway.echarts.shared.GetPatientSummaryResult;

@SuppressWarnings("serial")
public class PatientSummaryColumnDefinitionsImpl extends ArrayList<ColumnDefinition<GetPatientSummaryResult>> {

	public PatientSummaryColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				sb.append(t.getPatient().getName() == null ? "NO DATA" : t.getPatient().getName());
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Name</b>";
			}
		});
	}
}
