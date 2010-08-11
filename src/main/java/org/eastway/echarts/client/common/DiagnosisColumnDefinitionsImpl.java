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
				sb.append("<p>Axis 1A: " + t.getAxis1A() + "</p>");
			}

			@Override
			public String getHeader(Diagnosis t) {
				return DateTimeFormat.getFormat("M/d/y").format(t.getDate());
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 1B: " + t.getAxis1B() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 1C: " + t.getAxis1C() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 1D: " + t.getAxis1D() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 1E: " + t.getAxis1E() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 2A: " + t.getAxis2A() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 2B: " + t.getAxis2B() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 2C: " + t.getAxis2C() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 3: " + t.getAxis3() + "</p>");
			}
		});
		this.add(new ColumnDefinition<Diagnosis>() {
			@Override
			public void render(Diagnosis t, StringBuilder sb) {
				sb.append("<p>Axis 4: " + t.getAxis4() + "</p>");
			}
		});
	}
}
