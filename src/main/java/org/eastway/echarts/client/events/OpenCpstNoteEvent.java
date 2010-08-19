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
package org.eastway.echarts.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenCpstNoteEvent extends GwtEvent<OpenCpstNoteEventHandler> {

	public static final Type<OpenCpstNoteEventHandler> TYPE = new Type<OpenCpstNoteEventHandler>();
	private String caseNumber;

	public OpenCpstNoteEvent(String caseNumber) {
		this.setCaseNumber(caseNumber);
	}

	@Override
	protected void dispatch(OpenCpstNoteEventHandler handler) {
		handler.onOpenCpstNote(this);
	}

	@Override
	public Type<OpenCpstNoteEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
