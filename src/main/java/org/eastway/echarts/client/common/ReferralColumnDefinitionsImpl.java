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

import org.eastway.echarts.client.rpc.ReferralProxy;
import org.eastway.echarts.style.client.GlobalResources;

@SuppressWarnings("serial")
public class ReferralColumnDefinitionsImpl extends ArrayList<ColumnDefinition<ReferralProxy>> {

	public ReferralColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getTakenByStaff() == null ? "" : t.getTakenByStaff());
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Taken&nbsp;by</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getDisposition() == null ? "" : t.getDisposition());
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Disposition</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getReferralSource() == null ? "" : t.getReferralSource());
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Source</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getAdmissionDate() == null ? "" : GlobalResources.getDateFormat().format(t.getAdmissionDate()));
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Admission&nbsp;Date</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getReferralType() == null ? "" : t.getReferralType());
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Type</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getReferralDate() == null ? "" : GlobalResources.getDateFormat().format(t.getReferralDate()));
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Referral&nbsp;Date</b>";
			}
		});
		this.add(new ColumnDefinition<ReferralProxy>() {
			@Override
			public void render(ReferralProxy t, StringBuilder sb) {
				sb.append(t.getDischargeDate() == null ? "" : GlobalResources.getDateFormat().format(t.getDischargeDate()));
			}

			@Override
			public String getHeader(ReferralProxy t) {
				return "<b>Discharge&nbsp;Date</b>";
			}
		});
	}
}
