package org.eastway.echarts.client.ui;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.EchartsConstants;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.CodeProxy;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CurrentEhrView extends Composite {

	private static CurrentEhrWidgetUiBinder uiBinder = GWT
			.create(CurrentEhrWidgetUiBinder.class);

	interface CurrentEhrWidgetUiBinder extends
			UiBinder<Widget, CurrentEhrView> {
	}

	@UiField SpanElement name;
	@UiField SpanElement dob;
	@UiField SpanElement age;
	@UiField SpanElement ssn;
	@UiField SpanElement provider;
	@UiField SpanElement caseStatus;
	@UiField HTMLPanel container;
	@UiField SpanElement error;
	@UiField SpanElement caseNumber;

	EchartsConstants echartsConstants = GWT.<EchartsConstants>create(EchartsConstants.class);

	public CurrentEhrView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setRowData(EHRProxy ehr) {
		if (ehr == null) {
			Window.setTitle(Window.getTitle().replaceAll("ECharts(.*)", "ECharts"));
			name.setInnerText("");
			caseNumber.setInnerText("");
			dob.setInnerText("");
			age.setInnerText("");
			ssn.setInnerText("");
			caseStatus.setInnerText("");
			container.setVisible(false);
			return;
		}
		name.setInnerText(ehr.getPatient().getName() == null ? "NO DATA" : ehr.getPatient().getName());
		caseNumber.setInnerText(ehr.getPatient().getCaseNumber() == null ? "NO DATA" : String.valueOf(ehr.getPatient().getCaseNumber()));
		dob.setInnerText(ehr.getDemographics() == null ? "NO DATA" : GlobalResources.getDateFormat()
				.format(ehr.getDemographics()
						.getDob()));
		age.setInnerText(getAge(ehr.getDemographics().getDob()));
		ssn.setInnerText(ehr.getPatient().getSsn() == null ? "NO DATA" : ehr.getPatient().getSsn());
		caseStatus.setInnerText(getCaseStatus(ehr.getPatient().getCaseStatus()));
		container.setVisible(true);
		Window.setTitle(Window.getTitle().replaceAll("ECharts(.*)", "ECharts - " + ehr.getPatient().getCaseNumber()));
	}

	private String getCaseStatus(CodeProxy caseStatus) {
		if (caseStatus == null)
			return "NO DATA";
		else if (caseStatus.getCodeDescriptor() == null)
			return "NO DATA";
		return caseStatus.getCodeDescriptor();
	}

	private String getAge(Date dob) {
		if (dob == null)
			return "NO DATA";
		return new Long((new Date().getTime() - dob.getTime()) / (3600*24*365) / 1000).toString();
	}

	public void setProvider(List<AssignmentProxy> assignments) {
		String result = null;
		if (assignments == null || assignments.isEmpty()) {
			result = "NO DATA";
		} else {
			for (AssignmentProxy a : assignments)
			if (a.getStaff() != null && a.getStaff().equals(EchartsUser.staffId))
				result = a.getStaffName();
			result = assignments.get(0).getStaffName() != null ? assignments.get(0).getStaffName() : "NO DATA";
		}
		provider.setInnerText(result);
	}

	public void setError(String message) {
		error.setInnerText(message);
	}

	public boolean isEhrLoaded(String caseNumber) {
		if (this.caseNumber.getInnerText() != null && this.caseNumber.getInnerText().equals(caseNumber))
			return true;
		return false;
	}
}
