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

public class GetAppointments {

	private String sessionId;
	private String caseNumber;
	private int startRecord;
	private int maxResults;

	GetAppointments() { }

	public GetAppointments(String sessionId, String caseNumber) {
		this(sessionId, caseNumber, 0, 20);
	}

	public GetAppointments(String sessionId, String caseNumber, int startRecord, int maxResults) {
		this.sessionId = sessionId;
		this.caseNumber = caseNumber;
		this.setStartRecord(startRecord);
		this.setMaxResults(maxResults);
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getMaxResults() {
		return maxResults;
	}
}
