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

import org.eastway.echarts.shared.Demographics;

import com.google.gwt.i18n.client.DateTimeFormat;

@SuppressWarnings("serial")
public class DemographicsColumnDefinitionsImpl extends ArrayList<ColumnDefinition<Demographics>> {

	private String noData = "NO DATA";
	protected DemographicsColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(t.getAllergies() == null ? noData : t.getAllergies());
			}

			@Override
			public String getHeader(Demographics t) {
				return "Allergies";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append((t.getEmployment() == null || t.getEmployment().getDescriptor() == null ? noData : t.getEmployment().getDescriptor()));
			}

			@Override
			public String getHeader(Demographics t) {
				return "Employment";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(DateTimeFormat.getFormat("M/d/y").format(t.getDob()));
			}

			@Override
			public String getHeader(Demographics t) {
				return "DOB";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(t.getMaritalStatus() == null || t.getMaritalStatus().getDescriptor() == null ? noData : t.getMaritalStatus().getDescriptor());
			}

			@Override
			public String getHeader(Demographics t) {
				return "Marital Status";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(t.getEducationLevel() == null || t.getEducationLevel().getDescriptor() == null ? noData : t.getEducationLevel().getDescriptor());
			}

			@Override
			public String getHeader(Demographics t) {
				return "Education Level";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(t.getEducationType() == null || t.getEducationType().getDescriptor() == null ? noData : t.getEducationType().getDescriptor());
			}

			@Override
			public String getHeader(Demographics t) {
				return "Education Type";
			}
		});
		this.add(new ColumnDefinition<Demographics>() {
			@Override
			public void render(Demographics t, StringBuilder sb) {
				sb.append(t.getRace() == null || t.getRace().getDescriptor() == null ? noData : t.getRace().getDescriptor());
			}

			@Override
			public String getHeader(Demographics t) {
				return "Race";
			}
		});
	}
}