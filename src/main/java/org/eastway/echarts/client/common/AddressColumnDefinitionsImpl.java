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

import org.eastway.echarts.client.rpc.AddressProxy;

@SuppressWarnings("serial")
public class AddressColumnDefinitionsImpl extends ArrayList<ColumnDefinition<AddressProxy>> {

	public AddressColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<AddressProxy>() {
			@Override
			public void render(AddressProxy t, StringBuilder sb) {
				try {
					sb.append(t.getDescriptor() == null ? "" : t.getDescriptor());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}
		});
		this.add(new ColumnDefinition<AddressProxy>() {
			@Override
			public void render(AddressProxy t, StringBuilder sb) {
				try {
					sb.append(t.getTitle() == null ? "" : t.getTitle());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(AddressProxy t) {
				return "<b>Name</b>";
			}
		});
		this.add(new ColumnDefinition<AddressProxy>() {
			@Override
			public void render(AddressProxy t, StringBuilder sb) {
				try {
					sb.append(t.getStreet1() == null ? "" : t.getStreet1() + "&nbsp;");
					sb.append(t.getStreet2() == null ? "" : t.getStreet2() + "&nbsp;");
				} catch (NullPointerException e) {
					sb.append("");
				}
				try {
					sb.append("<div>");
					sb.append(t.getCity() == null ? "" : t.getCity() + ",&nbsp;");
					sb.append(t.getState() == null ? "" : t.getState() + "&nbsp;");
					sb.append(t.getZip() == null ? "" : t.getZip() + "</div>");
				} catch (NullPointerException e) {
					sb.append("</div>");
				}
			}

			@Override
			public String getHeader(AddressProxy t) {
				return "<b>Address</b>";
			}
		});
		this.add(new ColumnDefinition<AddressProxy>() {
			@Override
			public void render(AddressProxy t, StringBuilder sb) {
				try {
					sb.append(t.getPhone1Desc() == null ? "" : t.getPhone1Desc() + "&nbsp;");
					sb.append(t.getPhone1() == null ? "" : t.getPhone1());
				} catch (NullPointerException e) {
					sb.append("");
				}
				try {
					sb.append("<div>");
					sb.append(t.getPhone2Desc() == null ? "" : t.getPhone2Desc() + "&nbsp;");
					sb.append(t.getPhone2() == null ? "" : t.getPhone2() + "</div>");
				} catch (NullPointerException e) {
					sb.append("</div>");
				}
			}

			@Override
			public String getHeader(AddressProxy t) {
				return "<b>Phone</b>";
			}
		});
	}
}
