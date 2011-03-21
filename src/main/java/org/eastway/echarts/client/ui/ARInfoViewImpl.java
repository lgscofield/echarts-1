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

import org.eastway.echarts.client.request.ARInfoProxy;
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

public class ARInfoViewImpl extends Composite implements ARInfoView {
	@UiTemplate("ARInfoView.ui.xml")
	interface ARInfoViewUiBinder extends UiBinder<Widget, ARInfoViewImpl> {}
	static ARInfoViewUiBinder uiBinder = GWT.create(ARInfoViewUiBinder.class);

	@UiField TableElement table;
	@UiField SpanElement error;
	@UiField SpanElement billCode;
	@UiField SpanElement arStatus;
	@UiField SpanElement income;
	@UiField SpanElement dependents;
	@UiField SpanElement spendDown;
	@UiField SpanElement macsisRegisteredName;
	@UiField SpanElement macsisEffectiveDate;
	@UiField SpanElement uci;
	@UiField SpanElement medicaidNumber;
	@UiField SpanElement titleXXApplicationDate;
	@UiField SpanElement titleXXRedetermineDate;
	@UiField SpanElement titleXXEligibilityCategory;

	private ARInfoProxy proxy;

	public ARInfoViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setAlternateColors();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setValue(ARInfoProxy proxy) {
		if (proxy == null) {
			billCode.setInnerText("");
			arStatus.setInnerText("");
			income.setInnerText("");
			dependents.setInnerText("");
			spendDown.setInnerText("");
			macsisRegisteredName.setInnerText("");
			macsisEffectiveDate.setInnerText("");
			uci.setInnerText("");
			medicaidNumber.setInnerText("");
			titleXXApplicationDate.setInnerText("");
			titleXXRedetermineDate.setInnerText("");
			titleXXEligibilityCategory.setInnerText("");
			return;
		}

		this.proxy = proxy;

		billCode.setInnerText(this.proxy.getBillCode() == null ? "" : String.valueOf(this.proxy.getBillCode()));
		arStatus.setInnerText(this.proxy.getArStatus() == null ? "" : String.valueOf(this.proxy.getArStatus()));
		income.setInnerText(this.proxy.getIncome() == null ? "" : String.valueOf(this.proxy.getIncome()));
		dependents.setInnerText(this.proxy.getDependents() == null ? "" : String.valueOf(this.proxy.getDependents()));
		spendDown.setInnerText(this.proxy.getSpendDown() == null ? "" : String.valueOf(this.proxy.getSpendDown()));
		macsisRegisteredName.setInnerText(this.proxy.getMacRegName() == null ? "" : String.valueOf(this.proxy.getMacRegName()));
		macsisEffectiveDate.setInnerText(this.proxy.getMacRegDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getMacRegDate()));
		uci.setInnerText(this.proxy.getUci() == null ? "" : String.valueOf(this.proxy.getUci()));
		medicaidNumber.setInnerText(this.proxy.getMedicaidId() == null ? "" : String.valueOf(this.proxy.getMedicaidId()));
		titleXXApplicationDate.setInnerText(this.proxy.getTitleTwentyAppDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getTitleTwentyAppDate()));
		titleXXRedetermineDate.setInnerText(this.proxy.getTitleTwentyRedetermDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getTitleTwentyRedetermDate()));
		titleXXEligibilityCategory.setInnerText(this.proxy.getTitleTwentyEligibilityCategory() == null ? "" : String.valueOf(this.proxy.getTitleTwentyEligibilityCategory()));
	}

	@UiField Style style;

	private void setAlternateColors() {
		NodeList<TableRowElement> nodeList = table.getRows();
		for (int i = 0; i < nodeList.getLength(); i++)
			if ((i % 2) == 0)
				nodeList.getItem(i).addClassName(style.evenRow());
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}
}