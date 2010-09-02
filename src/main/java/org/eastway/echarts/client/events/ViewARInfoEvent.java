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
import org.eastway.echarts.shared.GetARInfo;

import com.google.gwt.event.shared.GwtEvent;

public class ViewARInfoEvent<T> extends GwtEvent<ViewARInfoEventHandler> {

	public static final Type<ViewARInfoEventHandler> TYPE = new Type<ViewARInfoEventHandler>();
	private String caseNumber;
	private EHRView<T> view;
	private GetARInfo action;

	public ViewARInfoEvent(String caseNumber, EHRView<T> view, GetARInfo action) {
		this.setCaseNumber(caseNumber);
		this.setView(view);
		this.setAction(action);
	}

	@Override
	public Type<ViewARInfoEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ViewARInfoEventHandler handler) {
		handler.onViewARInfo(this);
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

	public void setAction(GetARInfo action) {
		this.action = action;
	}

	public GetARInfo getAction() {
		return action;
	}

}
