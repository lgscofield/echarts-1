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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.eastway.echarts.client.presenter.TopPanelPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelView extends Composite implements TopPanelPresenter.Display {
	private static TopPanelViewUiBinder uiBinder = GWT.create(TopPanelViewUiBinder.class);

	interface TopPanelViewUiBinder extends UiBinder<Widget, TopPanelView> {}

	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox patientIdBox = new SuggestBox(oracle);
	private Button patientSearchButton = new Button("Search");

	@UiField ButtonStyle style;

	interface ButtonStyle extends CssResource {
		String button();

		String searchbox();
	}

	@UiField
	FlowPanel patientSearch;
	@UiField
	Anchor logoutButton;

	@UiField FlowPanel currentPatientData;

	public TopPanelView() {
		initWidget(uiBinder.createAndBindUi(this));
		patientSearch.add(patientIdBox);
		patientSearch.add(patientSearchButton);
		patientSearchButton.addStyleName(style.button());
		patientIdBox.addStyleName(style.searchbox());
		logoutButton.setHref("http://ewsql.eastway.local/echarts/logout.aspx?continue=" + Window.Location.getHref());
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashMap<String, Long> data) {
		oracle.addAll(data.keySet());
	}

	@Override
	public HasClickHandlers getSearchButton() {
		return patientSearchButton;
	}

	@Override
	public HasText getSuggestBox() {
		return patientIdBox;
	}

	@Override
	public HasClickHandlers getLogoutButton() {
		return logoutButton;
	}

	@Override
	public void setCurrentPatientData(ArrayList<String[]> data) {
		if (data == null) {
			currentPatientData.clear();
			return;
		}
		currentPatientData.clear();
		int i = 0;
		FlexTable cpd = new FlexTable();
		cpd.setText(0,0,"Name:");
		cpd.setText(0,1,data.get(i++)[1]);
		cpd.setText(0,2,"DOB:");
		cpd.setText(0,3,DateTimeFormat.getFormat("M/d/y").format(new Date(new Long(data.get(i++)[1]))).toString());
		cpd.setText(0,4,"(" + data.get(i++)[1] + ")");

		cpd.setText(1,0,"Case Status:");
		cpd.setText(1,1,data.get(i++)[1]);
		cpd.setText(1,2,"Provider:");
		cpd.setText(1,3,data.get(i++)[1]);

		cpd.setText(2,0,"SSN:");
		cpd.setText(2,1,data.get(i++)[1]);
		currentPatientData.add(cpd);
	}
}
