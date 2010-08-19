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
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.GwtEvent;

public class ViewServiceHistoryEvent extends GwtEvent<ViewServiceHistoryEventHandler> {

	public static final Type<ViewServiceHistoryEventHandler> TYPE = new Type<ViewServiceHistoryEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;

	public ViewServiceHistoryEvent(String caseNumber, EHRView<EHR> view) {
		this.setCaseNumber(caseNumber);
		this.setView(view);
	}

	@Override
	protected void dispatch(ViewServiceHistoryEventHandler handler) {
		handler.onViewServiceHistory(this);
	}

	@Override
	public Type<ViewServiceHistoryEventHandler> getAssociatedType() {
		return TYPE;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setView(EHRView<EHR> view) {
		this.view = view;
	}

	public EHRView<EHR> getView() {
		return view;
	}

}
