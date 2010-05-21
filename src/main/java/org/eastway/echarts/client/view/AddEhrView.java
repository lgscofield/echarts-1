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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import org.eastway.echarts.client.presenter.AddEhrPresenter;

public class AddEhrView extends Composite implements
						SelectionHandler<TreeItem>,
						AddEhrPresenter.Display {

	private static Binder uiBinder = GWT
			.create(Binder.class);

	interface Binder extends
			UiBinder<Widget, AddEhrView> {
	}

	@UiField DockLayoutPanel panel;
	@UiField Tree steps;
	@UiField TabLayoutPanel wizard;
	@UiField HTMLPanel returningPatient;

	/* Begin Chart data */
	@UiField HasText previousProvider;
	@UiField DateBox previousVisit;
	@UiField HasText caseNumber;
	@UiField HasText caseStatus;
	@UiField HasText firstName;
	@UiField HasText lastName;
	@UiField HasText middleInitial;
	@UiField HasText suffix;
	@UiField HasText alias;
	@UiField HasValue<Date> dob;
	@UiField HasText ssn;
	@UiField HasText county;
	@UiField HasText address;
	@UiField HasText phone;
	@UiField HasText altPhone;
	@UiField HasValue<Boolean> aod;
	@UiField HasValue<Boolean> requiredAod;
	@UiField HasValue<Boolean> hasHistoryOfTreatment;
	@UiField HasText treatmentDescription;
	@UiField HasValue<Boolean> isPregnant;
	@UiField HasValue<Boolean> isIvUser;
	@UiField HasText referredBy;
	@UiField HasText emergencyContact;
	@UiField HasText emergencyContactPhoneNumber;
	@UiField HasText emergencyContactRelationshipToPatient;
	@UiField HasValue<Boolean> isEmployed;
	@UiField HasText lastEmployedDate;
	@UiField HasText insuranceType;
	@UiField HasText income;
	@UiField HasValue<Boolean> hasDesireToWork;
	@UiField HasValue<Boolean> hasEmploymentBarriers;
	@UiField HasValue<Boolean> hasLegalProblems;
	@UiField HasText legalProblemsDescription;
	@UiField HasValue<Boolean> isReceivingSsdForMhReasons;
	@UiField HasValue<Boolean> hasRecentlyVisitedErOrHospital;
	@UiField HasText recentlyReceivedTreatment;
	@UiField HasText previousMentalHealthTreatment;
	@UiField HasText previousMentalHealthHospitalizations;
	@UiField HasText currentMedications;
	@UiField HasText guardian;
	@UiField HasValue<Date> firstAppointmentOfferedDatePicker;
	@UiField HasText firstAppointmentOffered;
	@UiField HasValue<Date> firstAppointmentTakenDatePicker;
	@UiField HasText firstAppointmentTaken;
	@UiField HasValue<Boolean> informedPatient;
	@UiField HasText referralSource;
	@UiField HasText referralSourcePhoneNumber;
	@UiField HasText serviceRequest;
	@UiField HasText reasonDenied;
	@UiField HasText referredTo;
	@UiField HasText otherPertinentInfo;
	/* End Chart Data */

	@UiField Button next, previous, finish;

	private TreeItem stepOne;
	private TreeItem stepTwo;
	private TreeItem stepThree;
	private TreeItem stepFour;
	private TreeItem stepFive;
	private TreeItem stepSix;
	private TreeItem stepSeven;

	private TreeItem[] stepList = new TreeItem[] {
			stepOne,
			stepTwo,
			stepThree,
			stepFour,
			stepFive,
			stepSix,
			stepSeven
	};

	public AddEhrView() {
		initWidget(uiBinder.createAndBindUi(this));

		stepList[0] = steps.addItem("Step 1");
		stepList[1] = steps.addItem("Step 2");
		stepList[2] = steps.addItem("Step 3");
		stepList[3] = steps.addItem("Step 4");
		stepList[4] = steps.addItem("Step 5");
		stepList[5] = steps.addItem("Step 6");
		stepList[6] = steps.addItem("Step 7");
		steps.setSelectedItem(stepList[0]);

		steps.addSelectionHandler(this);

		DateTimeFormat dateFormat = DateTimeFormat.getMediumDateFormat();
		previousVisit.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();
		if (item.equals(stepList[0])) {
			previous.setEnabled(false);
			next.setEnabled(true);
			wizard.selectTab(0);
		} else if (item.equals(stepList[1])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(1);
		} else if (item.equals(stepList[2])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(2);
		} else if (item.equals(stepList[3])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(3);
		} else if (item.equals(stepList[4])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(4);
		} else if (item.equals(stepList[5])) {
			next.setEnabled(true);
			previous.setEnabled(true);
			wizard.selectTab(5);
		} else if (item.equals(stepList[6])) {
			next.setEnabled(false);
			previous.setEnabled(true);
			finish.setEnabled(true);
			wizard.selectTab(6);
		}
	}


	@UiHandler(value = { "next"})
	void handleNext(ClickEvent event) {
		for (int i = 0; i < stepList.length; i++) {
			if (stepList[i].equals(steps.getSelectedItem()))
				steps.setSelectedItem(stepList[++i]);
		}
	}

	@UiHandler(value = { "previous"})
	void handlePrevious(ClickEvent event) {
		for (int i = stepList.length - 1; i > 0; i--) {
			if (stepList[i].equals(steps.getSelectedItem()))
				steps.setSelectedItem(stepList[--i]);
		}
	}

	@UiHandler(value = { "firstAppointmentOfferedDatePicker"})
	void handleFirstAppointmentOffered(ValueChangeEvent<Date> event) {
		Date date = event.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		firstAppointmentOffered.setText(dateString);
	}

	@UiHandler(value = { "firstAppointmentTakenDatePicker"})
	void handleFirstAppointmentTaken(ValueChangeEvent<Date> event) {
		Date date = event.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		firstAppointmentTaken.setText(dateString);
	}

	@Override
	public HasText getAddress() {
		return address;
	}

	@Override
	public HasText getAltPhone() {
		return altPhone;
	}

	@Override
	public HasText getCaseNumber() {
		return caseNumber;
	}

	@Override
	public HasText getCounty() {
		return county;
	}

	@Override
	public HasText getCurrentMedications() {
		return currentMedications;
	}

	@Override
	public HasValue<Date> getDob() {
		return dob;
	}

	@Override
	public HasText getEmergencyContact() {
		return emergencyContact;
	}

	@Override
	public HasText getEmergencyContactPhoneNumber() {
		return emergencyContactPhoneNumber;
	}

	@Override
	public HasText getEmergencyContactRelationshipToPatient() {
		return emergencyContactRelationshipToPatient;
	}

	@Override
	public HasClickHandlers getFinishButton() {
		return finish;
	}

	@Override
	public HasText getFirstAppointmentOffered() {
		return firstAppointmentOffered;
	}

	@Override
	public HasValue<Date> getFirstAppointmentOfferedDatePicker() {
		return firstAppointmentOfferedDatePicker;
	}

	@Override
	public HasText getFirstAppointmentTaken() {
		return firstAppointmentTaken;
	}

	@Override
	public HasValue<Date> getFirstAppointmentTakenDatePicker() {
		return firstAppointmentTakenDatePicker;
	}

	@Override
	public HasText getFirstName() {
		return firstName;
	}

	@Override
	public HasText getGuardian() {
		return guardian;
	}

	@Override
	public HasText getIncome() {
		return income;
	}

	@Override
	public HasText getInsuranceType() {
		return insuranceType;
	}

	@Override
	public HasText getLastEmployedDate() {
		return lastEmployedDate;
	}

	@Override
	public HasText getLastName() {
		return lastName;
	}

	@Override
	public HasText getLegalProblemsDescription() {
		return legalProblemsDescription;
	}

	@Override
	public HasText getMiddleInitial() {
		return middleInitial;
	}

	@Override
	public HasText getOtherPertinentInfo() {
		return otherPertinentInfo;
	}

	@Override
	public HasText getPhone() {
		return phone;
	}

	@Override
	public HasText getPreviousMentalHealthHospitalizations() {
		return previousMentalHealthHospitalizations;
	}

	@Override
	public HasText getPreviousMentalHealthTreatment() {
		return previousMentalHealthTreatment;
	}

	@Override
	public HasText getPreviousProvider() {
		return previousProvider;
	}

	@Override
	public HasValue<Date> getPreviousVisit() {
		return previousVisit;
	}

	@Override
	public HasText getReasonDenied() {
		return reasonDenied;
	}

	@Override
	public HasText getRecentlyReceivedTreatment() {
		return recentlyReceivedTreatment;
	}

	@Override
	public HasText getReferralSource() {
		return referralSource;
	}

	@Override
	public HasText getReferralSourcePhoneNumber() {
		return referralSourcePhoneNumber;
	}

	@Override
	public HasText getReferredBy() {
		return referredBy;
	}

	@Override
	public HasText getReferredTo() {
		return referredTo;
	}

	@Override
	public HasText getServiceRequest() {
		return serviceRequest;
	}

	@Override
	public HasText getSsn() {
		return ssn;
	}

	@Override
	public HasText getTreatmentDescription() {
		return treatmentDescription;
	}

	@Override
	public HasValue<Boolean> hasDesireToWork() {
		return hasDesireToWork;
	}

	@Override
	public HasValue<Boolean> hasEmploymentBarriers() {
		return hasEmploymentBarriers;
	}

	@Override
	public HasValue<Boolean> hasHistoryOfTreatment() {
		return hasHistoryOfTreatment;
	}

	@Override
	public HasValue<Boolean> hasLegalProblems() {
		return hasLegalProblems;
	}

	@Override
	public HasValue<Boolean> hasRecentlyVisitedErOrHospital() {
		return hasRecentlyVisitedErOrHospital;
	}

	@Override
	public HasValue<Boolean> isAod() {
		return aod;
	}

	@Override
	public HasValue<Boolean> isEmployed() {
		return isEmployed;
	}

	@Override
	public HasValue<Boolean> isInformedPatient() {
		return informedPatient;
	}

	@Override
	public HasValue<Boolean> isIvUser() {
		return isIvUser;
	}

	@Override
	public HasValue<Boolean> isPregnant() {
		return isPregnant;
	}

	@Override
	public HasValue<Boolean> isReceivingSsdForMhReasons() {
		return isReceivingSsdForMhReasons;
	}

	@Override
	public HasValue<Boolean> isRequiredAod() {
		return requiredAod;
	}

	@Override
	public void setAddress(HasText address) {
		this.address = address;
	}

	@Override
	public void setAltPhone(HasText altPhone) {
		this.altPhone = altPhone;
	}

	@Override
	public void setAod(HasValue<Boolean> aod) {
		this.aod = aod;
	}

	@Override
	public void setCaseNumber(HasText caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setCounty(HasText county) {
		this.county = county;
	}

	@Override
	public void setCurrentMedications(HasText currentMedications) {
		this.currentMedications = currentMedications;
	}

	@Override
	public void setDesireToWork(HasValue<Boolean> hasDesireToWork) {
		this.hasDesireToWork = hasDesireToWork;
	}

	@Override
	public void setDob(HasValue<Date> dob) {
		this.dob = dob;
	}

	@Override
	public void setEmergencyContact(HasText emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	@Override
	public void setEmergencyContactPhoneNumber(
			HasText emergencyContactPhoneNumber) {
		this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
	}

	@Override
	public void setEmergencyContactRelationshipToPatient(
			HasText emergencyContactRelationshipToPatient) {
		this.emergencyContactRelationshipToPatient = emergencyContactRelationshipToPatient;
	}

	@Override
	public void setEmployed(HasValue<Boolean> isEmployed) {
		this.isEmployed = isEmployed;
	}

	@Override
	public void setEmploymentBarriers(
			HasValue<Boolean> hasEmploymentBarriers) {
		this.hasEmploymentBarriers = hasEmploymentBarriers;
	}

	@Override
	public void setFirstAppointmentOffered(HasText firstAppointmentOffered) {
		this.firstAppointmentOffered = firstAppointmentOffered;
	}

	@Override
	public void setFirstAppointmentOfferedDatePicker(
			HasValue<Date> firstAppointmentOfferedDatePicker) {
		this.firstAppointmentOfferedDatePicker = firstAppointmentOfferedDatePicker;
	}

	@Override
	public void setFirstAppointmentTaken(HasText firstAppointmentTaken) {
		this.firstAppointmentTaken = firstAppointmentTaken;
	}

	@Override
	public void setFirstAppointmentTakenDatePicker(
			HasValue<Date> firstAppointmentTakenDatePicker) {
		this.firstAppointmentTakenDatePicker = firstAppointmentTakenDatePicker;
	}

	@Override
	public void setFirstName(HasText firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setGuardian(HasText guardian) {
		this.guardian = guardian;
	}

	@Override
	public void setHistoryOfTreatment(HasValue<Boolean> hasHistoryOfTreatment) {
		this.hasHistoryOfTreatment = hasHistoryOfTreatment;
	}

	@Override
	public void setIncome(HasText income) {
		this.income = income;
	}

	@Override
	public void setInformedPatient(HasValue<Boolean> informedPatient) {
		this.informedPatient = informedPatient;
	}

	@Override
	public void setInsuranceType(HasText insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public void setIvUser(HasValue<Boolean> isIvUser) {
		this.isIvUser = isIvUser;
	}

	@Override
	public void setLastEmployedDate(HasText lastEmployedDate) {
		this.lastEmployedDate = lastEmployedDate;
	}

	@Override
	public void setLastName(HasText lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setLegalProblems(HasValue<Boolean> hasLegalProblems) {
		this.hasLegalProblems = hasLegalProblems;
	}

	@Override
	public void setLegalProblemsDescription(HasText legalProblemsDescription) {
		this.legalProblemsDescription = legalProblemsDescription;
	}

	@Override
	public void setMiddleInitial(HasText middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public void setOtherPertinentInfo(HasText otherPertinentInfo) {
		this.otherPertinentInfo = otherPertinentInfo;
	}

	@Override
	public void setPhone(HasText phone) {
		this.phone = phone;
	}

	@Override
	public void setPregnant(HasValue<Boolean> isPregnant) {
		this.isPregnant = isPregnant;
	}

	@Override
	public void setPreviousMentalHealthHospitalizations(
			HasText previousMentalHealthHospitalizations) {
		this.previousMentalHealthHospitalizations = previousMentalHealthHospitalizations;
	}

	@Override
	public void setPreviousMentalHealthTreatment(
			HasText previousMentalHealthTreatment) {
		this.previousMentalHealthTreatment = previousMentalHealthTreatment;
	}

	@Override
	public void setPreviousProvider(HasText previousProvider) {
		this.previousProvider = previousProvider;
	}

	@Override
	public void setPreviousVisit(HasValue<Date> previousVisit) {
		this.previousVisit.setValue(previousVisit.getValue());
	}

	@Override
	public void setReasonDenied(HasText reasonDenied) {
		this.reasonDenied = reasonDenied;
	}

	@Override
	public void setReceivingSsdForMhReasons(
			HasValue<Boolean> isReceivingSsdForMhReasons) {
		this.isReceivingSsdForMhReasons = isReceivingSsdForMhReasons;
	}

	@Override
	public void setRecentlyReceivedTreatment(
			HasText recentlyReceivedTreatment) {
		this.recentlyReceivedTreatment = recentlyReceivedTreatment;
	}

	@Override
	public void setRecentlyVisitedErOrHospital(
			HasValue<Boolean> hasRecentlyVisitedErOrHospital) {
		this.hasRecentlyVisitedErOrHospital = hasRecentlyVisitedErOrHospital;
	}

	@Override
	public void setReferralSource(HasText referralSource) {
		this.referralSource = referralSource;
	}

	@Override
	public void setReferralSourcePhoneNumber(
			HasText referralSourcePhoneNumber) {
		this.referralSourcePhoneNumber = referralSourcePhoneNumber;
	}

	@Override
	public void setReferredBy(HasText referredBy) {
		this.referredBy = referredBy;
	}

	@Override
	public void setReferredTo(HasText referredTo) {
		this.referredTo = referredTo;
	}

	@Override
	public void setRequiredAod(HasValue<Boolean> requiredAod) {
		this.requiredAod = requiredAod;
	}

	@Override
	public void setServiceRequest(HasText serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@Override
	public void setSsn(HasText ssn) {
		this.ssn = ssn;
	}

	@Override
	public void setTreatmentDescription(
			HasText treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}

	@Override
	public HasText getCaseStatus() {
		return caseStatus;
	}

	@Override
	public HasText getSuffix() {
		return suffix;
	}

	@Override
	public HasText getAlias() {
		return alias;
	}
}
