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
import org.eastway.echarts.shared.GetReferral;

import com.google.gwt.event.shared.GwtEvent;

public class ViewReferralEvent<T> extends GwtEvent<ViewReferralEventHandler> {
	public static Type<ViewReferralEventHandler> TYPE = new Type<ViewReferralEventHandler>();
	private long id;
	private EHRView<T> view;
	private GetReferral action;

	public ViewReferralEvent(long id, EHRView<T> view, GetReferral action) {
		this.id = id;
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewReferralEventHandler handler) {
		handler.onViewReferral(this);
	}

	@Override
	public Type<ViewReferralEventHandler> getAssociatedType() {
		return TYPE;
	}

	public long getId() {
		return id;
	}

	public EHRView<T> getView() {
		return view;
	}

	public GetReferral getAction() {
		return action;
	}
}
