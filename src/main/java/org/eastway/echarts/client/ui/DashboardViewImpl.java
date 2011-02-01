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
package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.StaffAnalysisPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DashboardViewImpl<T> extends Composite implements DashboardView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("DashboardView.ui.xml")
	interface DashboardViewUiBinder extends UiBinder<Widget, DashboardViewImpl> { }

	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	@UiField Style style;
	@UiField SpanElement productivity;
	@UiField SpanElement bonusProjection;
	@UiField TableCellElement graphColor;
	@UiField TableElement graph;

	private double productivityUnit = 0.83;

	private Presenter<T> presenter;

	public DashboardViewImpl() {
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
	public void setBonusProjection(String greenNumber) {
		bonusProjection.setInnerText(greenNumber);
	}

	@Override
	public void setProductivity(String productivity, String color) {
		this.productivity.setInnerText(productivity);
		if (color.equals("red"))
			graph.addClassName(style.red());
		else if (color.equals("yellow"))
			graph.addClassName(style.yellow());
		else
			graph.addClassName(style.green());
		Double width = new Double(new Double(productivity) * productivityUnit);
		if (width < 1.0 && width > 0.0)
			graph.setWidth("1%");
		else if (width >= 1.0)
			graph.setWidth(width.toString() + "%");
	}

	@Override
	public void isFirstLogin() {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);
		dialogBox.setText("Welcome to Echarts!");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(new HTML("This appears to be the first time you've logged into " +
				"Echarts.  Please continue by closing this dialog box and clicking " +
				"on your username in the upper right corner of the browser.  Please " +
				"verify the information there, set any credentials you have and " +
				"select your program from the drop down."));
		Button closeButton = new Button("Close");
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		verticalPanel.add(closeButton);
		dialogBox.setWidget(verticalPanel);
		dialogBox.setWidth("500px");
		dialogBox.center();
		dialogBox.show();
	}

	@UiHandler("staffAnalysis")
	void openStaffAnalysis(ClickEvent event) {
		event.preventDefault();
		presenter.goTo(new StaffAnalysisPlace(EchartsUser.staffId));
	}
}
