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

import org.eastway.echarts.shared.Address;

@SuppressWarnings("serial")
public class AddressColumnDefinitionsImpl extends ArrayList<ColumnDefinition<Address>> {

	public AddressColumnDefinitionsImpl() {
		this.add(new ColumnDefinition<Address>() {
			@Override
			public void render(Address t, StringBuilder sb) {
				try {
					sb.append(t.getPhone1().isEmpty() ? "" : t.getPhone1());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(Address t) {
				return "<b>Phone&nbsp;1</b>";
			}
		});
		this.add(new ColumnDefinition<Address>() {
			@Override
			public void render(Address t, StringBuilder sb) {
				try {
					sb.append(t.getStreet1().isEmpty() ? "" : t.getStreet1());
				} catch (NullPointerException e) {
					sb.append("");
				}
			}

			@Override
			public String getHeader(Address t) {
				return "<b>Street&nbsp;1</b>";
			}
		});
	}
}
