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

import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.style.client.GlobalResources;

@SuppressWarnings("serial")
public class DiagnosisColumnDefinitionsImpl extends ArrayList<ColumnDefinition<DiagnosisProxy>> {

	private String dash = "&nbsp;&mdash;&nbsp;";
	protected DiagnosisColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis1A() == null || t.getAxis1A().getDescription() == null)
					sb.append("<p>Axis 1A: NO DATA</p>");
				else
					sb.append("<p>Axis 1A: " + t.getAxis1A().getId() + dash + t.getAxis1A().getDescription() + "</p>");
			}

			@Override
			public String getHeader(DiagnosisProxy t) {
				return GlobalResources.getDateFormat().format(t.getDate());
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis1B() == null || t.getAxis1B().getDescription() == null)
					sb.append("<p>Axis 1B: NO DATA</p>");
				else
					sb.append("<p>Axis 1B: " + t.getAxis1B().getId() + dash + t.getAxis1B().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis1C() == null || t.getAxis1C().getDescription() == null)
					sb.append("<p>Axis 1C: NO DATA</p>");
				else
					sb.append("<p>Axis 1C: " + t.getAxis1C().getId() + dash + t.getAxis1C().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis1D() == null || t.getAxis1D().getDescription() == null)
					sb.append("<p>Axis 1D: NO DATA</p>");
				else
					sb.append("<p>Axis 1D: " + t.getAxis1D().getId() + dash + t.getAxis1D().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis1E() == null || t.getAxis1E().getDescription() == null)
					sb.append("<p>Axis 1E: NO DATA</p>");
				else
					sb.append("<p>Axis 1E: " + t.getAxis1E().getId() + dash + t.getAxis1E().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis2A() == null || t.getAxis2A().getDescription() == null)
					sb.append("<p>Axis 2A: NO DATA</p>");
				else
					sb.append("<p>Axis 2A: " + t.getAxis2A().getId() + dash + t.getAxis2A().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis2B() == null || t.getAxis2B().getDescription() == null)
					sb.append("<p>Axis 2B: NO DATA</p>");
				else
					sb.append("<p>Axis 2B: " + t.getAxis2B().getId() + dash + t.getAxis2B().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis2C() == null || t.getAxis2C().getDescription() == null)
					sb.append("<p>Axis 2C: NO DATA</p>");
				else
					sb.append("<p>Axis 2C: " + t.getAxis2C().getId() + dash + t.getAxis2C().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis3() == null)
					sb.append("<p>Axis 3: NO DATA</p>");
				else
					sb.append("<p>Axis 3: " + t.getAxis3() + "</p>");
			}
		});
		this.add(new ColumnDefinition<DiagnosisProxy>() {
			@Override
			public void render(DiagnosisProxy t, StringBuilder sb) {
				if (t.getAxis4() == null)
					sb.append("<p>Axis 4: NO DATA</p>");
				else
					sb.append("<p>Axis 4: " + t.getAxis4() + "</p>");
			}
		});
	}
}
