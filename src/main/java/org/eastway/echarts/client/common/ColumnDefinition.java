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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;

public abstract class ColumnDefinition<T> {
	public abstract void render(T t, StringBuilder sb);

	public String getData(T t) {
		return null;
	}

	public List<String> getList(T t) {
		return null;
	}

	public void setData(T t, String data) {
		
	}

	public boolean isClickable() {
		return false;
	}

	public boolean isSelectable() {
		return false;
	}

	public boolean isList() {
		return false;
	}

	public boolean isEditable() {
		return false;
	}

	public boolean isContextMenu() {
		return false;
	}

	public String getHeader(T t) {
		return null;
	}

	public Map<String, String> getMap(T t) {
		return null;
	}

	public boolean isMap() {
		return false;
	}

	public String createTicklerUrl(T t) {
		return null;
	}

	public int getTicklerDueDateStatus(T t) {
		return 0;
	}

	public boolean isTicklerDueDate() {
		return false;
	}

	public boolean isTicklerCompletedDate() {
		return false;
	}

	public Date getCompletedDate(T t) {
		return null;
	}

	public Boolean isPlace() {
		return false;
	}

	public Place getPlace(T t) {
		return null;
	}
}
