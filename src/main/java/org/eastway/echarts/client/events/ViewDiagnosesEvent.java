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

import org.eastway.echarts.client.ui.EHRView;
import org.eastway.echarts.shared.GetDiagnoses;

import com.google.gwt.event.shared.GwtEvent;

public class ViewDiagnosesEvent<T> extends GwtEvent<ViewDiagnosesEventHandler> {
	public static Type<ViewDiagnosesEventHandler> TYPE = new Type<ViewDiagnosesEventHandler>();
	private String caseNumber;
	private EHRView<T> view;
	private GetDiagnoses action;

	public ViewDiagnosesEvent(String caseNumber, EHRView<T> view,
			GetDiagnoses action) {
		this.caseNumber = caseNumber;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewDiagnosesEventHandler handler) {
		handler.onViewDiagnoses(this);
	}

	@Override
	public Type<ViewDiagnosesEventHandler> getAssociatedType() {
		return TYPE;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public EHRView<T> getView() {
		return view;
	}

	public GetDiagnoses getAction() {
		return action;
	}

}
