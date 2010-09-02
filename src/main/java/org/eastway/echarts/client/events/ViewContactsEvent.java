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
import org.eastway.echarts.shared.GetContacts;

import com.google.gwt.event.shared.GwtEvent;

public class ViewContactsEvent<T> extends GwtEvent<ViewContactsEventHandler> {
	public static Type<ViewContactsEventHandler> TYPE = new Type<ViewContactsEventHandler>();
	private EHRView<T> view;
	private GetContacts action;

	public ViewContactsEvent(String caseNumber, EHRView<T> view,
			GetContacts action) {
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewContactsEventHandler handler) {
		handler.onViewContacts(this);
	}

	@Override
	public Type<ViewContactsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<T> getView() {
		return view;
	}

	public GetContacts getAction() {
		return action;
	}
}
