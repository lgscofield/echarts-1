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

import org.eastway.echarts.shared.Diagnosis;

import com.google.gwt.i18n.client.DateTimeFormat;

@SuppressWarnings("serial")
public class DiagnosisColumnDefinitionsImpl extends ArrayList<ColumnDefinition<Diagnosis>> {

	protected DiagnosisColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis1A().getDescription() == null)
					sb.append("<p>Axis 1A: NO DATA</p>");
				else
					sb.append("<p>Axis 1A: " + t.getAxis1A().getDescription() + "</p>");
			}

			@Override
			public String getHeader(Diagnosis t) {
				return DateTimeFormat.getFormat("M/d/y").format(t.getDate());
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis1B().getDescription() == null)
					sb.append("<p>Axis 1B: NO DATA</p>");
				else
					sb.append("<p>Axis 1B: " + t.getAxis1B().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis1C().getDescription() == null)
					sb.append("<p>Axis 1C: NO DATA</p>");
				else
					sb.append("<p>Axis 1C: " + t.getAxis1C().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis1D().getDescription() == null)
					sb.append("<p>Axis 1D: NO DATA</p>");
				else
					sb.append("<p>Axis 1D: " + t.getAxis1D().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis1E().getDescription() == null)
					sb.append("<p>Axis 1E: NO DATA</p>");
				else
					sb.append("<p>Axis 1E: " + t.getAxis1E().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis2A().getDescription() == null)
					sb.append("<p>Axis 2A: NO DATA</p>");
				else
					sb.append("<p>Axis 2A: " + t.getAxis2A().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis2B().getDescription() == null)
					sb.append("<p>Axis 2B: NO DATA</p>");
				else
					sb.append("<p>Axis 2B: " + t.getAxis2B().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis2C().getDescription() == null)
					sb.append("<p>Axis 2C: NO DATA</p>");
				else
					sb.append("<p>Axis 2C: " + t.getAxis2C().getDescription() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis3() == null)
					sb.append("<p>Axis 3: NO DATA</p>");
				else
					sb.append("<p>Axis 3: " + t.getAxis3() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				if (t.getAxis4() == null)
					sb.append("<p>Axis 4: NO DATA</p>");
				else
					sb.append("<p>Axis 4: " + t.getAxis4() + "</p>");
			}
		});
	}
}
