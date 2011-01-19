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

import org.eastway.echarts.client.request.ReferralProxy;
import org.eastway.echarts.client.style.GlobalResources;

public class ReferralViewImpl extends Composite implements ReferralView {
	@UiTemplate("ReferralView.ui.xml")
	interface ReferralViewUiBinder extends UiBinder<Widget, ReferralViewImpl> { }

	private static ReferralViewUiBinder uiBinder = GWT.create(ReferralViewUiBinder.class);

	@UiField TableElement table;
	@UiField SpanElement takenBy;
	@UiField SpanElement disposition;
	@UiField SpanElement source;
	@UiField SpanElement admissionDate;
	@UiField SpanElement type;
	@UiField SpanElement referralDate;
	@UiField SpanElement dischargeDate;
	@UiField SpanElement error;

	private ReferralProxy proxy;

	public ReferralViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setAlternateColors();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setValue(ReferralProxy proxy) {
		this.proxy = proxy;

		takenBy.setInnerText(this.proxy.getTakenByStaff() == null ? "" : String.valueOf(this.proxy.getTakenByStaff()));
		disposition.setInnerText(this.proxy.getDisposition() == null ? "" : String.valueOf(this.proxy.getDisposition()));
		source.setInnerText(this.proxy.getReferralSource() == null ? "" : String.valueOf(this.proxy.getReferralSource()));
		admissionDate.setInnerText(this.proxy.getAdmissionDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getAdmissionDate()));
		type.setInnerText(this.proxy.getReferralType() == null ? "" : String.valueOf(this.proxy.getReferralType()));
		referralDate.setInnerText(this.proxy.getReferralDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getReferralDate()));
		dischargeDate.setInnerText(this.proxy.getDischargeDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getDischargeDate()));
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
