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

import org.eastway.echarts.client.presenter.EditPatientSummaryPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class EditPatientSummaryView extends Composite implements
		EditPatientSummaryPresenter.Display {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, EditPatientSummaryView> { }

	@UiField HasText caseNumber;
	@UiField HasText firstName;
	@UiField HasText lastName;
	@UiField HasText middleInitial;
	@UiField HasValue<Date> dob;
	@UiField HasText ssn;
	@UiField HasText county;
	@UiField HasText address;
	@UiField HasText phone;
	@UiField HasText altPhone;
	@UiField HasClickHandlers save;

	public EditPatientSummaryView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasText getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setCaseNumber(HasText caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public HasText getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(HasText firstName) {
		this.firstName = firstName;
	}

	@Override
	public HasText getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(HasText lastName) {
		this.lastName = lastName;
	}

	@Override
	public HasText getMiddleInitial() {
		return middleInitial;
	}

	@Override
	public void setMiddleInitial(HasText middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public HasValue<Date> getDob() {
		return dob;
	}

	@Override
	public void setDob(HasValue<Date> dob) {
		this.dob = dob;
	}

	@Override
	public HasText getSsn() {
		return ssn;
	}

	@Override
	public void setSsn(HasText ssn) {
		this.ssn = ssn;
	}

	@Override
	public HasText getCounty() {
		return county;
	}

	@Override
	public void setCounty(HasText county) {
		this.county = county;
	}

	@Override
	public HasText getAddress() {
		return address;
	}

	@Override
	public void setAddress(HasText address) {
		this.address = address;
	}

	@Override
	public HasText getPhone() {
		return phone;
	}

	@Override
	public void setPhone(HasText phone) {
		this.phone = phone;
	}

	@Override
	public HasText getAltPhone() {
		return altPhone;
	}

	@Override
	public void setAltPhone(HasText altPhone) {
		this.altPhone = altPhone;
	}

	@Override
	public HasClickHandlers getSave() {
		return save;
	}

	@Override
	public void setSave(HasClickHandlers save) {
		this.save = save;
	}
}
