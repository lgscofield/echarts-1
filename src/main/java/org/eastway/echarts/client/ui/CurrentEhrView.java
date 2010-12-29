package org.eastway.echarts.client.ui;

import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.CodeProxy;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

	public CurrentEhrView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setRowData(EHRProxy ehr) {
		if (ehr == null)
			return;
		name.setInnerText(ehr.getPatient().getName() == null ? "NO DATA" : ehr.getPatient().getName());
		dob.setInnerText(ehr.getDemographics() == null ? "NO DATA" : GlobalResources.getDateFormat()
				.format(ehr.getDemographics()
						.getDob()));
		age.setInnerText(getAge(ehr.getDemographics().getDob()));
		ssn.setInnerText(ehr.getPatient().getSsn() == null ? "NO DATA" : ehr.getPatient().getSsn());
		provider.setInnerText(getProvider(ehr.getAssignments()));
		caseStatus.setInnerText(getCaseStatus(ehr.getPatient().getCaseStatus()));
		container.setVisible(true);
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

	private String getProvider(List<AssignmentProxy> assignments) {
		if (assignments == null || assignments.isEmpty())
			return "NO DATA";
		for (AssignmentProxy a : assignments)
			if (a.getStaff() != null && a.getStaff().equals(EchartsUser.staffId))
				return a.getStaffName();
		return assignments.get(0).getStaffName() != null ? assignments.get(0).getStaffName() : "NO DATA";
	}

	public void setError(String message) {
		error.setInnerText(message);
	}
}
