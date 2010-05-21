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
package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ServiceHistoryView extends Widget implements ServiceHistoryPresenter.Display {
	private static ServiceHistoryUiBinder uiBinder = GWT.create(ServiceHistoryUiBinder.class);

	interface ServiceHistoryUiBinder extends UiBinder<Element, ServiceHistoryView> {}

	@UiField SpanElement nameSpan;

	public ServiceHistoryView() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setName(String name) {
		nameSpan.setInnerText(name);
	}
}
