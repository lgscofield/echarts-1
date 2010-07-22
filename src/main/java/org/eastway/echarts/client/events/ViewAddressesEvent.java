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
import org.eastway.echarts.shared.GetAddresses;

import com.google.gwt.event.shared.GwtEvent;

public class ViewAddressesEvent extends GwtEvent<ViewAddressesEventHandler> {
	public static Type<ViewAddressesEventHandler> TYPE = new Type<ViewAddressesEventHandler>();
	private String caseNumber;
	private EHRView<EHR> view;
	private GetAddresses action;

	public ViewAddressesEvent(String caseNumber, EHRView<EHR> view,
			GetAddresses action) {
		this.caseNumber = caseNumber;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewAddressesEventHandler handler) {
		handler.onViewAddresses(this);
	}

	@Override
	public Type<ViewAddressesEventHandler> getAssociatedType() {
		return TYPE ;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetAddresses getAction() {
		return action;
	}
}
