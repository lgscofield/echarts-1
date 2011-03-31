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

import java.util.List;

import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.style.GlobalResources;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PatientSummaryViewImpl extends Composite implements PatientSummaryView {
	@UiTemplate("PatientSummaryView.ui.xml")
	interface Binder extends UiBinder<Widget, PatientSummaryViewImpl> {}
	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField SpanElement displayName;
	@UiField ImageElement picture;
	@UiField SpanElement gender;
	@UiField SpanElement dob;
	@UiField SpanElement providers;
	@UiField SpanElement ssn;
	@UiField SpanElement caseStatus;
	@UiField SpanElement error;
	@UiField TableElement table;
	@UiField SpanElement isp;
	@UiField SpanElement ispReview;
	@UiField SpanElement healthHX;
	@UiField SpanElement daUpdate;
	@UiField SpanElement financial;
	@UiField SpanElement ooc;

	private EHRProxy proxy;

	public PatientSummaryViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setAlternateColors();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setEhrData(EHRProxy proxy) {
		if (proxy == null) {
			displayName.setInnerText("");
			picture.setSrc(GlobalResources.resources().defaultPhoto().getURL());
			ssn.setInnerText("");
			caseStatus.setInnerText("");
			gender.setInnerText("");
			dob.setInnerText("");
			return;
		}
		this.proxy = proxy;

		displayName.setInnerText(this.proxy.getPatient() == null || this.proxy.getPatient().getName() == null ? "" : this.proxy.getPatient().getName());
		picture.setSrc(GlobalResources.resources().defaultPhoto().getURL());
		ssn.setInnerText(this.proxy.getPatient() == null || this.proxy.getPatient().getSsn() == null ? "" : this.proxy.getPatient().getSsn());
		caseStatus.setInnerText(this.proxy.getPatient() == null || this.proxy.getPatient().getCaseStatus() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getPatient().getCaseStatus()));

		gender.setInnerText(this.proxy.getDemographics() == null || this.proxy.getDemographics().getGender() == null ? "" : CodeProxyRenderer.instance().render(this.proxy.getDemographics().getGender()));
		dob.setInnerText(this.proxy.getDemographics() == null || this.proxy.getDemographics().getDob() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getDemographics().getDob()));
	}

	@Override
	public void setProviders(List<AssignmentProxy> assignments) {
		if (assignments != null && assignments.size() != 0) {
			StringBuilder sb = new StringBuilder();
			for (AssignmentProxy assignment : assignments)
				sb.append(AssignmentProxyRenderer.instance().render(assignment))
					.append("<br />");
			providers.setInnerHTML(sb.toString());
			return;
		}
		providers.setInnerText("");
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

	TicklerFormatter formatter = new TicklerFormatter();

	@Override
	public void setTickler(List<Tickler> tickler) {
		if (tickler != null && tickler.size() != 0) {
			Tickler t = tickler.get(0);
			String caseNumber = t.getCaseNumber();
			isp.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createIspUrl(caseNumber), t.getIspDueDate().getStatus(), GlobalResources.getDateFormat().format(t.getIspDueDate().getDueDate())));
			ispReview.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createIspReviewUrl(caseNumber), t.getIspReviewDueDate().getStatus(), GlobalResources.getDateFormat().format(t.getIspReviewDueDate().getDueDate())));
			healthHX.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createHealthHistoryUrl(caseNumber), t.getHealthHistoryDueDate().getStatus(), GlobalResources.getDateFormat().format(t.getHealthHistoryDueDate().getDueDate())));
			daUpdate.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createDAUpdateUrl(caseNumber), t.getDiagnosticAssessmentUpdate().getStatus(), GlobalResources.getDateFormat().format(t.getDiagnosticAssessmentUpdate().getDueDate())));
			financial.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createFinancialUpdateUrl(caseNumber), t.getFinancialDueDate().getStatus(), GlobalResources.getDateFormat().format(t.getFinancialDueDate().getDueDate())));
			ooc.setInnerHTML(formatter.formatColumn(TicklerUrlBuilder.createOocUrl(), t.getOoc().getStatus(), GlobalResources.getDateFormat().format(t.getOoc().getDueDate())));
			return;
		}
		isp.setInnerText("");
		ispReview.setInnerText("");
		healthHX.setInnerText("");
		daUpdate.setInnerText("");
		financial.setInnerText("");
		ooc.setInnerText("");
	}
}
