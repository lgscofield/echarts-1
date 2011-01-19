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

import org.eastway.echarts.client.request.DemographicsProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DemographicsViewImpl extends Composite implements DemographicsView {
	@UiTemplate("DemographicsView.ui.xml")
	interface DemographicsViewUiBinder extends UiBinder<Widget, DemographicsViewImpl> { }

	private static DemographicsViewUiBinder uiBinder = GWT
			.create(DemographicsViewUiBinder.class);

	private DemographicsProxy proxy;

	@UiField SpanElement error;
	@UiField SpanElement allergies;
	@UiField SpanElement employment;
	@UiField SpanElement dob;
	@UiField SpanElement maritalStatus;
	@UiField SpanElement educationLevel;
	@UiField SpanElement educationType;
	@UiField SpanElement race;
	@UiField TableElement table;

	public DemographicsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setAlternateColors();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setValue(DemographicsProxy proxy) {
		this.proxy = proxy;

		allergies.setInnerText(this.proxy.getAllergies() == null ? "" : this.proxy.getAllergies());
		employment.setInnerText(this.proxy.getEmployment() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getEmployment()));
		dob.setInnerText(this.proxy.getDob() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getDob()));
		maritalStatus.setInnerText(this.proxy.getMaritalStatus() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getMaritalStatus()));
		educationLevel.setInnerText(this.proxy.getEducationLevel() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getEducationLevel()));
		educationType.setInnerText(this.proxy.getEducationType() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getEducationType()));
		race.setInnerText(this.proxy.getRace() == null ? null : CodeProxyRenderer.instance().render(this.proxy.getRace()));
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}

	@UiField Style style;

	private void setAlternateColors() {
		NodeList<TableRowElement> nodeList = table.getRows();
		for (int i = 0; i < nodeList.getLength(); i++)
			if ((i % 2) == 0)
				nodeList.getItem(i).addClassName(style.evenRow());
	}
}
