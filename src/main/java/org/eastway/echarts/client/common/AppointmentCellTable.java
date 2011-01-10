package org.eastway.echarts.client.common;

import org.eastway.echarts.client.request.AppointmentProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class AppointmentCellTable extends CellTable<AppointmentProxy> {
	public AppointmentCellTable() {
		this.addColumn(new TextColumn<AppointmentProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};
			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getActivity());
			}
		}, "Activity");
		this.addColumn(new TextColumn<AppointmentProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(GlobalResources.DateTimeConstants.SHORT_DATE));

			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getAppointmentDate());
			}
		}, "Date");
		this.addColumn(new TextColumn<AppointmentProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(GlobalResources.DateTimeConstants.SHORT_TIME));
			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getStartTime());
			}
		}, "Start Time");
		this.addColumn(new TextColumn<AppointmentProxy>() {
			DateTimeFormatRenderer renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(GlobalResources.DateTimeConstants.SHORT_TIME));
			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getEndTime());
			}
		}, "End Time");
		this.addColumn(new TextColumn<AppointmentProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};
			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getLocation());
			}
		}, "Location");
		this.addColumn(new TextColumn<AppointmentProxy>() {
			Renderer<String> renderer = new AbstractRenderer<String>() {
				@Override
				public String render(String object) {
					return object == null ? "" : String.valueOf(object);
				}
			};
			@Override
			public String getValue(AppointmentProxy object) {
				return renderer.render(object.getNotes());
			}
		}, "Notes");
	}
}
