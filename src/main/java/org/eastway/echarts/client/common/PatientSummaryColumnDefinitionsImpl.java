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
import org.eastway.echarts.style.client.GlobalResources;

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
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				sb.append("<img src=\"" + GlobalResources.resources().defaultPhoto().getURL() + "\" />");
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Picture</b>";
			}
		});
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				sb.append(t.getDemographics().getGender().getDescriptor() == null ? "" : t.getDemographics().getGender().getDescriptor());
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Gender</b>";
			}
		});
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				if (t.getDemographics().getDob() != null)
					sb.append(GlobalResources.getDateFormat().format(t.getDemographics().getDob()));
				else
					sb.append("");
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Date&nbsp;of&nbsp;birth</b>";
			}
		});
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				for (String provider : t.getProviders())
					sb.append(provider == null ? "" : provider + "<br />");
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Providers</b>";
			}
		});
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				sb.append(t.getPatient().getSsn() == null ? "" : t.getPatient().getSsn());
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>SSN</b>";
			}
		});
		this.add(new ColumnDefinition<GetPatientSummaryResult>() {
			@Override
			public void render(GetPatientSummaryResult t, StringBuilder sb) {
				sb.append(t.getPatient().getCaseStatus().getDescriptor() == null ? "" :
					t.getPatient().getCaseStatus().getDescriptor());
			}

			@Override
			public String getHeader(GetPatientSummaryResult t) {
				return "<b>Case Status</b>";
			}
		});
	}
}
