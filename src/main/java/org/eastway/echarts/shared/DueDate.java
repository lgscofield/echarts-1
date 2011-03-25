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
package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DueDate implements Serializable {
	Date dueDate;
	int status;

	DueDate() {	}

	DueDate(Date dueDate, int status) {
		this.dueDate = dueDate;
		this.status = status;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public int getStatus() {
		return status;
	}
}
