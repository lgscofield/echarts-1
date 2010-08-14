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
				sb.append("<table border='1' style='border-collapse: collapse;' cellpadding='2' cellspacing='0'><tbody><tr>");
				sb.append("<td>Allergies:</td><td>" + (t.getAllergies() == null ? noData : t.getAllergies()) + "</td>");
				sb.append("</tr><tr>");
				sb.append("<td>Employment:</td><td>" + (t.getEmployment() == null ? noData : t.getEmployment().getDescriptor()) + "</td>");
				sb.append("</tr><tr>");
				sb.append("<td>DOB:</td><td>" + (DateTimeFormat.getFormat("M/d/y").format(t.getDob())) + "</td>");
				sb.append("</tr></tbody></table>");
			}
		});
	}
}
