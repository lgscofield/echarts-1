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

import org.eastway.echarts.shared.EHRProxy;

import com.google.gwt.event.shared.GwtEvent;

public class OpenEhrEvent extends GwtEvent<OpenEhrEventHandler> {
	public static Type<OpenEhrEventHandler> TYPE = new Type<OpenEhrEventHandler>();
	private EHRProxy ehr;

	public OpenEhrEvent(EHRProxy ehr) {
		this.ehr = ehr;
	}

	@Override
	protected void dispatch(OpenEhrEventHandler handler) {
		handler.onOpenEhr(this);
	}

	@Override
	public Type<OpenEhrEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRProxy getEhr() {
		return ehr;
	}
}
