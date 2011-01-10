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

import org.eastway.echarts.client.request.DemographicsProxy;
import org.eastway.echarts.client.style.GlobalResources;

@SuppressWarnings("serial")
public class DemographicsColumnDefinitionsImpl extends ArrayList<ColumnDefinition<DemographicsProxy>> {

	private String noData = "NO DATA";
	protected DemographicsColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append(t.getAllergies() == null ? noData : t.getAllergies());
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Allergies</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append((t.getEmployment() == null || t.getEmployment().getCodeDescriptor() == null ? noData : t.getEmployment().getCodeDescriptor()));
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Employment</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				if (t.getDob() != null)
					sb.append(GlobalResources.getDateFormat().format(t.getDob()));
				else
					sb.append(noData);
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>DOB</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append(t.getMaritalStatus() == null || t.getMaritalStatus().getCodeDescriptor() == null ? noData : t.getMaritalStatus().getCodeDescriptor());
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Marital&nbsp;Status</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append(t.getEducationLevel() == null || t.getEducationLevel().getCodeDescriptor() == null ? noData : t.getEducationLevel().getCodeDescriptor());
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Education&nbsp;Level</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append(t.getEducationType() == null || t.getEducationType().getCodeDescriptor() == null ? noData : t.getEducationType().getCodeDescriptor());
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Education&nbsp;Type</b>";
			}
		});
		this.add(new ColumnDefinition<DemographicsProxy>() {
			@Override
			public void render(DemographicsProxy t, StringBuilder sb) {
				sb.append(t.getRace() == null || t.getRace().getCodeDescriptor() == null ? noData : t.getRace().getCodeDescriptor());
			}

			@Override
			public String getHeader(DemographicsProxy t) {
				return "<b>Race</b>";
			}
		});
	}
}
