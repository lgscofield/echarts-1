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
import org.eastway.echarts.shared.GetMedications;

import com.google.gwt.event.shared.GwtEvent;

public class ViewMedicationsEvent extends GwtEvent<ViewMedicationsEventHandler> {

	public static Type<ViewMedicationsEventHandler> TYPE = new Type<ViewMedicationsEventHandler>();
	private EHRView<EHR> view;
	private GetMedications action;

	public ViewMedicationsEvent(String caseNumber, EHRView<EHR> view,
			GetMedications action) {
		this.view = view;
		this.action = action;
	}

	@Override
	protected void dispatch(ViewMedicationsEventHandler handler) {
		handler.onViewMedications(this);
	}

	@Override
	public Type<ViewMedicationsEventHandler> getAssociatedType() {
		return TYPE;
	}

	public EHRView<EHR> getView() {
		return view;
	}

	public GetMedications getAction() {
		return action;
	}
}
