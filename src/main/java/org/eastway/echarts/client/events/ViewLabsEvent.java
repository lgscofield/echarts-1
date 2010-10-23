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

public class ViewLabsEvent<T> extends GwtEvent<ViewLabsEventHandler> {

	public static final Type<ViewLabsEventHandler> TYPE = new Type<ViewLabsEventHandler>();
	private String caseNumber;
	private EHRView<T> view;

	public ViewLabsEvent(String caseNumber,	EHRView<T> view) {
		this.setCaseNumber(caseNumber);
		this.setView(view);
	}

	@Override
	public Type<ViewLabsEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ViewLabsEventHandler handler) {
		handler.onViewLabs(this);
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setView(EHRView<T> view) {
		this.view = view;
	}

	public EHRView<T> getView() {
		return view;
	}
}
