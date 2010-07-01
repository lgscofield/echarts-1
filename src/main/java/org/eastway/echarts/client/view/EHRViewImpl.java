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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class EHRViewImpl<T> extends Composite implements EHRView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("EHRView.ui.xml")
	interface EHRViewUiBinder extends UiBinder<Widget, EHRViewImpl> { }
	private static EHRViewUiBinder uiBinder = GWT.<EHRViewUiBinder>create(EHRViewUiBinder.class);
	private Presenter<T> presenter;
	@UiField Tree menu;
	@UiField Label patientSummary;
	@UiField ScrollPanel displayArea;

	public EHRViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setTreeItemWidth() {
		// TODO Auto-generated method stub
		
	}

	@UiHandler("patientSummary")
	void onPatientSummarySelected(ClickEvent event) {
		presenter.viewPatientSummary();
	}

	@UiHandler("messages")
	void onMessagesSelected(ClickEvent event) {
		presenter.viewMessages();
	}

	@UiHandler("demographics")
	void onDemographicsSelected(ClickEvent event) {
		presenter.viewDemographics();
	}

	@Override
	public Presenter<T> getPresenter() {
		return presenter;
	}

	@Override
	public HasWidgets getDisplayArea() {
		return displayArea;
	}
}
