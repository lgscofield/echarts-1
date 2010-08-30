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

import org.eastway.echarts.shared.ARInfo;
import org.eastway.echarts.style.client.GlobalResources;

@SuppressWarnings("serial")
public class ARInfoColumnDefinitionsImpl extends ArrayList<ColumnDefinition<ARInfo>> {

	public ARInfoColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getBillCode() == null ? "" : t.getBillCode());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Bill&nbsp;Code</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getArStatus() == null ? "" : t.getArStatus());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>AR&nbsp;Status</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getIncome() == null ? "" : t.getIncome());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Income</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getDependents() == null ? 0 : t.getDependents());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Dependents</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getSpendDown() == null ? "" : t.getSpendDown());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Spend&nbsp;Down</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getMacRegName() == null ? "" : t.getMacRegName());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>MACSIS&nbsp;Registered&nbsp;Name</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getMacRegDate() == null ? "" : GlobalResources.getDateFormat().format(t.getMacRegDate()));
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>MACSIS&nbsp;Effective&nbsp;Date</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getUci() == null ? "" : t.getUci());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>UCI</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getMedicaidId() == null ? "" : t.getMedicaidId());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Medicaid&nbsp;Number</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getTitleTwentyAppDate() == null ? "" : GlobalResources.getDateFormat().format(t.getTitleTwentyAppDate()));
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Title&nbsp;XX&nbsp;Application&nbsp;Date</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getTitleTwentyRedetermDate() == null ? "" : GlobalResources.getDateFormat().format(t.getTitleTwentyRedetermDate()));
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Title&nbsp;XX&nbsp;Redetermine&nbsp;Date</b>";
			}
		});
		this.add(new ColumnDefinition<ARInfo>() {
			@Override
			public void render(ARInfo t, StringBuilder sb) {
				sb.append(t.getTitleTwentyEligibilityCategory() == null ? "" : t.getTitleTwentyEligibilityCategory());
			}

			@Override
			public String getHeader(ARInfo t) {
				return "<b>Title&nbsp;XX&nbsp;Eligibility&nbsp;Category</b>";
			}
		});
	}
}