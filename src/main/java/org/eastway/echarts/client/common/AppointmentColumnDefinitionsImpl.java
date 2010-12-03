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

import org.eastway.echarts.shared.AppointmentProxy;
import org.eastway.echarts.style.client.GlobalResources;

@SuppressWarnings("serial")
public class AppointmentColumnDefinitionsImpl extends ArrayList<ColumnDefinition<AppointmentProxy>>{

	public AppointmentColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getActivity() == null ? "" : t.getActivity());
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>Activity</b>";
			}
		});
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getAppointmentDate() == null ? "" : GlobalResources.getDateFormat().format(t.getAppointmentDate()));
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>Date</b>";
			}
		});
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getStartTime() == null ? "" : GlobalResources.getTimeFormat().format(t.getStartTime()));
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>Start&nbsp;Time</b>";
			}
		});
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getEndTime() == null ? "" : GlobalResources.getTimeFormat().format(t.getEndTime()));
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>End&nbsp;Time</b>";
			}
		});
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getLocation() == null ? "" : t.getLocation());
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>Location</b>";
			}
		});
		this.add(new ColumnDefinition<AppointmentProxy>() {
			@Override
			public void render(AppointmentProxy t, StringBuilder sb) {
				sb.append(t.getNotes() == null ? "" : t.getNotes());
			}

			@Override
			public String getHeader(AppointmentProxy t) {
				return "<b>Notes</b>";
			}
		});
	}
}
