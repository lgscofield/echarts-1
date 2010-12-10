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

import org.eastway.echarts.client.view.EHRView;

import com.google.gwt.event.shared.GwtEvent;

public class ViewMessagesEvent<T> extends GwtEvent<ViewMessagesEventHandler> {
	public static Type<ViewMessagesEventHandler> TYPE = new Type<ViewMessagesEventHandler>();
	private EHRView<T> view;
	private String caseNumber;

	public ViewMessagesEvent(String caseNumber, EHRView<T> view) {
		this.view = view;
		this.caseNumber = caseNumber;
	}

	@Override
	protected void dispatch(ViewMessagesEventHandler handler) {
		handler.onViewMessages(this);
	}

	@Override
	public Type<ViewMessagesEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<T> getView() {
		return view;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
