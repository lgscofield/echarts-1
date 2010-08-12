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
