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

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.ReferralPresenter;
import org.eastway.echarts.shared.ReferralDTO;

public class ReferralView extends Composite implements ReferralPresenter.Display {

	private static ReferralViewUiBinder uiBinder = GWT
			.create(ReferralViewUiBinder.class);

	interface ReferralViewUiBinder extends UiBinder<Widget, ReferralView> { }

	@UiField FlexTable referrals;
	private int record = 0;
	private String emptyCellFiller = "&nbsp;";

	enum Column {
		ADMISSION_DATE,
		DISCHARGE_DATE,
		DISPOSITION,
		LAST_EDIT,
		LAST_EDIT_BY,
		LAST_SERVICE,
		LEVEL_OF_CARE,
		PLAN_TYPE,
		PROGRAM,
		REFERRAL_DATE,
		REFERRAL_SOURCE,
		REFERRAL_TYPE,
		TAKEN_BY_STAFF,
		UCI,
		UPID,
	}

	public ReferralView() {
		initWidget(uiBinder.createAndBindUi(this));
		referrals.setHTML(record, Column.ADMISSION_DATE.ordinal(), "<b>Admission Date</b>");
		referrals.setHTML(record, Column.DISCHARGE_DATE.ordinal(), "<b>Discharge Date</b>");
		referrals.setHTML(record, Column.DISPOSITION.ordinal(), "<b>Disposition</b>");
		referrals.setHTML(record, Column.LAST_EDIT.ordinal(), "<b>Last Edit</b>");
		referrals.setHTML(record, Column.LAST_EDIT_BY.ordinal(), "<b>Last Edit By</b>");
		referrals.setHTML(record, Column.LAST_SERVICE.ordinal(), "<b>Last Service</b>");
		referrals.setHTML(record, Column.LEVEL_OF_CARE.ordinal(), "<b>Level Of Care</b>");
		referrals.setHTML(record, Column.PLAN_TYPE.ordinal(), "<b>Plan Type</b>");
		referrals.setHTML(record, Column.PROGRAM.ordinal(), "<b>Program</b>");
		referrals.setHTML(record, Column.REFERRAL_DATE.ordinal(), "<b>Referral Date</b>");
		referrals.setHTML(record, Column.REFERRAL_SOURCE.ordinal(), "<b>Referral Source</b>");
		referrals.setHTML(record, Column.REFERRAL_TYPE.ordinal(), "<b>Referral Type</b>");
		referrals.setHTML(record, Column.TAKEN_BY_STAFF.ordinal(), "<b>Taken By Staff</b>");
		referrals.setHTML(record, Column.UCI.ordinal(), "<b>UCI</b>");
		referrals.setHTML(record, Column.UPID.ordinal(), "<b>UPID</b>");
		referrals.setBorderWidth(1);
		nextRecord();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Date getAdmissionDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDischargeDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisposition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastEditBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLevelOfCare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProgram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getReferralDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferralSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferralType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTakenByStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUCI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUPId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdmissionDate(Date admissionDate) {
		if (admissionDate == null)
			referrals.setHTML(record, Column.ADMISSION_DATE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.ADMISSION_DATE.ordinal(), admissionDate.toString());
	}

	@Override
	public void setDischargeDate(Date dischargeDate) {
		if (dischargeDate == null)
			referrals.setHTML(record, Column.DISCHARGE_DATE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.DISCHARGE_DATE.ordinal(), dischargeDate.toString());
	}

	@Override
	public void setDisposition(String disposition) {
		if (disposition == null || disposition.matches(""))
			referrals.setHTML(record, Column.DISPOSITION.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.DISPOSITION.ordinal(), disposition);
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		if (lastEdit == null)
			referrals.setHTML(record, Column.LAST_EDIT.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.LAST_EDIT.ordinal(), lastEdit.toString());
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		if (lastEditBy == null || lastEditBy.matches(""))
			referrals.setHTML(record, Column.LAST_EDIT_BY.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.LAST_EDIT_BY.ordinal(), lastEditBy);
	}

	@Override
	public void setLastService(Date lastService) {
		if (lastService == null)
			referrals.setHTML(record, Column.LAST_SERVICE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.LAST_SERVICE.ordinal(), lastService.toString());
	}

	@Override
	public void setLevelOfCare(String levelOfCare) {
		if (levelOfCare == null || levelOfCare.matches(""))
			referrals.setHTML(record, Column.LEVEL_OF_CARE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.LEVEL_OF_CARE.ordinal(), levelOfCare);
	}

	@Override
	public void setPlanType(String planType) {
		if (planType == null || planType.matches(""))
			referrals.setHTML(record, Column.PLAN_TYPE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.PLAN_TYPE.ordinal(), planType);
	}

	@Override
	public void setProgram(String program) {
		if (program == null || program.matches(""))
			referrals.setHTML(record, Column.PROGRAM.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.PROGRAM.ordinal(), program);
	}

	@Override
	public void setReferralDate(Date referralDate) {
		if (referralDate == null)
			referrals.setHTML(record, Column.REFERRAL_DATE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.REFERRAL_DATE.ordinal(), referralDate.toString());
	}

	@Override
	public void setReferralSource(String referralSource) {
		if (referralSource == null || referralSource.matches(""))
			referrals.setHTML(record, Column.REFERRAL_SOURCE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.REFERRAL_SOURCE.ordinal(), referralSource);
	}

	@Override
	public void setReferralType(String referralType) {
		if (referralType == null || referralType.matches(""))
			referrals.setHTML(record, Column.REFERRAL_TYPE.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.REFERRAL_TYPE.ordinal(), referralType);
	}

	@Override
	public void setTakenByStaff(String takenByStaff) {
		if (takenByStaff == null || takenByStaff.matches(""))
			referrals.setHTML(record, Column.TAKEN_BY_STAFF.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.TAKEN_BY_STAFF.ordinal(), takenByStaff);
	}

	@Override
	public void setUCI(String UCI) {
		if (UCI == null || UCI.matches(""))
			referrals.setHTML(record, Column.UCI.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.UCI.ordinal(), UCI);
	}

	@Override
	public void setUPId(String UPId) {
		if (UPId == null || UPId.matches(""))
			referrals.setHTML(record, Column.UPID.ordinal(), emptyCellFiller);
		else
			referrals.setText(record, Column.UPID.ordinal(), UPId);
	}

	@Override
	public ReferralDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextRecord() {
		record++;
	}

	@Override
	public String getCaseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		// TODO Auto-generated method stub
		
	}
}
