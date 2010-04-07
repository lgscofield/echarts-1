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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import org.eastway.echarts.client.presenter.EditEhrPresenter;

public class EditEhrView extends Composite implements
						SelectionHandler<TreeItem>,
						EditEhrPresenter.Display {

	private static EditEhrViewUiBinder uiBinder = GWT
			.create(EditEhrViewUiBinder.class);

	interface EditEhrViewUiBinder extends
			UiBinder<Widget, EditEhrView> {
	}

	@UiField DockLayoutPanel panel;
	@UiField Tree steps;
	@UiField TabLayoutPanel wizard;
	@UiField HTMLPanel returningPatient;

	/* Begin Chart data */
	@UiField TextBox previousProvider;
	@UiField DateBox previousVisit;
	@UiField TextBox caseNumber;
	@UiField TextBox firstName;
	@UiField TextBox lastName;
	@UiField TextBox middleInitial;
	@UiField TextBox dob;
	@UiField TextBox ssn;
	@UiField TextBox county;
	@UiField TextBox address;
	@UiField TextBox phone;
	@UiField TextBox altPhone;
	@UiField CheckBox aod;
	@UiField CheckBox requiredAod;
	@UiField CheckBox historyOfTreatment;
	@UiField TextArea treatmentDescription;
	@UiField CheckBox isPregnant;
	@UiField CheckBox isIvUser;
	@UiField TextBox referredBy;
	@UiField TextBox emergencyContact;
	@UiField TextBox emergencyContactPhoneNumber;
	@UiField TextBox emergencyContactRelationshipToPatient;
	@UiField CheckBox isEmployed;
	@UiField TextBox lastEmployedDate;
	@UiField TextBox insuranceType;
	@UiField TextBox income;
	@UiField CheckBox hasDesireToWork;
	@UiField CheckBox hasEmploymentBarriers;
	@UiField CheckBox hasLegalProblems;
	@UiField TextArea legalProblemsDescription;
	@UiField CheckBox isReceivingSsdForMhReasons;
	@UiField CheckBox hasRecentlyVisitedErOrHospital;
	@UiField TextArea recentlyReceivedTreatment;
	@UiField TextArea previousMentalHealthTreatment;
	@UiField TextArea previousMentalHealthHospitalizations;
	@UiField TextArea currentMedications;
	@UiField TextBox guardian;
	@UiField DatePicker firstAppointmentOfferedDatePicker;
	@UiField Label firstAppointmentOffered;
	@UiField DatePicker firstAppointmentTakenDatePicker;
	@UiField Label firstAppointmentTaken;
	@UiField CheckBox informedPatient;
	@UiField TextBox referralSource;
	@UiField TextBox referralSourcePhoneNumber;
	@UiField TextBox serviceRequest;
	@UiField TextBox reasonDenied;
	@UiField TextBox referredTo;
	@UiField TextArea otherPertinentInfo;
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

	public EditEhrView() {
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

	@UiHandler(value = { "finish"})
	void handleFinish(ClickEvent event) {
		// TODO : do something really cool with the data
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
	public HasClickHandlers getFinishButton() {
		return finish;
	}

	@Override
	public String getFirstName() {
		return firstName.getText();
	}

	@Override
	public String getLastName() {
		return lastName.getText();
	}

	@Override
	public String getMiddleInitial() {
		return middleInitial.getText();
	}

	@Override
	public Date getDob() {
		Date date = DateTimeFormat.getMediumDateFormat().parse(dob.getText());
		return date;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber.getText();
	}

	@Override
	public void setCaseNumber(TextBox caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getPreviousProvider() {
		return previousProvider.getText();
	}

	@Override
	public void setPreviousProvider(String previousProvider) {
		this.previousProvider.setText(previousProvider);
	}

	@Override
	public Date getPreviousVisit() {
		return previousVisit.getValue();
	}

	@Override
	public void setPreviousVisit(Date previousVisit) {
		this.previousVisit.setValue(previousVisit);
	}

	@Override
	public String getSsn() {
		return ssn.getText();
	}

	@Override
	public void setSsn(String ssn) {
		this.ssn.setText(ssn);
	}

	@Override
	public String getCounty() {
		return county.getText();
	}

	@Override
	public void setCounty(String county) {
		this.county.setText(county);
	}

	@Override
	public String getAddress() {
		return address.getText();
	}

	@Override
	public void setAddress(String address) {
		this.address.setText(address);
	}

	@Override
	public String getPhone() {
		return phone.getText();
	}

	@Override
	public void setPhone(String phone) {
		this.phone.setText(phone);
	}

	@Override
	public String getAltPhone() {
		return altPhone.getText();
	}

	@Override
	public void setAltPhone(String altPhone) {
		this.altPhone.setText(altPhone);
	}

	@Override
	public boolean isAod() {
		return aod.isEnabled();
	}

	@Override
	public void setAod(boolean useAod) {
		this.aod.setEnabled(useAod);
	}

	@Override
	public boolean isRequiredAod() {
		return requiredAod.isEnabled();
	}

	@Override
	public void setRequiredAod(boolean requiredAod) {
		this.requiredAod.setEnabled(requiredAod);
	}

	@Override
	public boolean hasHistoryOfTreatment() {
		return historyOfTreatment.isEnabled();
	}

	@Override
	public void setHistoryOfTreatment(boolean historyOfTreatment) {
		this.historyOfTreatment.setEnabled(historyOfTreatment);
	}

	@Override
	public String getTreatmentDescription() {
		return treatmentDescription.getText();
	}

	@Override
	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription.setText(treatmentDescription);
	}

	@Override
	public boolean isPregnant() {
		return isPregnant.isEnabled();
	}

	@Override
	public void setPregnant(boolean isPregnant) {
		this.isPregnant.setEnabled(isPregnant);
	}

	@Override
	public boolean isIvUser() {
		return isIvUser.isEnabled();
	}

	@Override
	public void setIvUser(boolean isIvUser) {
		this.isIvUser.setEnabled(isIvUser);
	}

	@Override
	public String getReferredBy() {
		return referredBy.getText();
	}

	@Override
	public void setReferredBy(String referredBy) {
		this.referredBy.setText(referredBy);
	}

	@Override
	public String getEmergencyContact() {
		return emergencyContact.getText();
	}

	@Override
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact.setText(emergencyContact);
	}

	@Override
	public String getEmergencyContactPhoneNumber() {
		return emergencyContactPhoneNumber.getText();
	}

	@Override
	public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber) {
		this.emergencyContactPhoneNumber.setText(emergencyContactPhoneNumber);
	}

	@Override
	public String getEmergencyContactRelationshipToPatient() {
		return emergencyContactRelationshipToPatient.getText();
	}

	@Override
	public void setEmergencyContactRelationshipToPatient(
			String emergencyContactRelationshipToPatient) {
		this.emergencyContactRelationshipToPatient.setText(emergencyContactRelationshipToPatient);
	}

	@Override
	public boolean isEmployed() {
		return isEmployed.isEnabled();
	}

	@Override
	public void setEmployed(boolean isEmployed) {
		this.isEmployed.setEnabled(isEmployed);
	}

	@Override
	public String getLastEmployedDate() {
		return lastEmployedDate.getText();
	}

	@Override
	public void setLastEmployedDate(String lastEmployedDate) {
		this.lastEmployedDate.setText(lastEmployedDate);
	}

	@Override
	public String getInsuranceType() {
		return insuranceType.getText();
	}

	@Override
	public void setInsuranceType(String insuranceType) {
		this.insuranceType.setText(insuranceType);
	}

	@Override
	public String getIncome() {
		return income.getText();
	}

	@Override
	public void setIncome(String income) {
		this.income.setText(income);
	}

	@Override
	public boolean hasDesireToWork() {
		return hasDesireToWork.isEnabled();
	}

	@Override
	public void setDesireToWork(boolean hasDesireToWork) {
		this.hasDesireToWork.setEnabled(hasDesireToWork);
	}

	@Override
	public boolean hasEmploymentBarriers() {
		return hasEmploymentBarriers.isEnabled();
	}

	@Override
	public void setEmploymentBarriers(boolean hasEmploymentBarriers) {
		this.hasEmploymentBarriers.setEnabled(hasEmploymentBarriers);
	}

	@Override
	public boolean hasLegalProblems() {
		return hasLegalProblems.isEnabled();
	}

	@Override
	public void setLegalProblems(boolean hasLegalProblems) {
		this.hasLegalProblems.setEnabled(hasLegalProblems);
	}

	@Override
	public String getLegalProblemsDescription() {
		return legalProblemsDescription.getText();
	}

	@Override
	public void setLegalProblemsDescription(String legalProblemsDescription) {
		this.legalProblemsDescription.setText(legalProblemsDescription);
	}

	@Override
	public boolean isReceivingSsdForMhReasons() {
		return isReceivingSsdForMhReasons.isEnabled();
	}

	@Override
	public void setReceivingSsdForMhReasons(boolean isReceivingSsdForMhReasons) {
		this.isReceivingSsdForMhReasons.setEnabled(isReceivingSsdForMhReasons);
	}

	@Override
	public boolean hasRecentlyVisitedErOrHospital() {
		return hasRecentlyVisitedErOrHospital.isEnabled();
	}

	@Override
	public void setRecentlyVisitedErOrHospital(
			boolean hasRecentlyVisitedErOrHospital) {
		this.hasRecentlyVisitedErOrHospital.setEnabled(hasRecentlyVisitedErOrHospital);
	}

	@Override
	public String getRecentlyReceivedTreatment() {
		return recentlyReceivedTreatment.getText();
	}

	@Override
	public void setRecentlyReceivedTreatment(String recentlyReceivedTreatment) {
		this.recentlyReceivedTreatment.setText(recentlyReceivedTreatment);
	}

	@Override
	public String getPreviousMentalHealthTreatment() {
		return previousMentalHealthTreatment.getText();
	}

	@Override
	public void setPreviousMentalHealthTreatment(
			String previousMentalHealthTreatment) {
		this.previousMentalHealthTreatment.setText(previousMentalHealthTreatment);
	}

	@Override
	public String getPreviousMentalHealthHospitalizations() {
		return previousMentalHealthHospitalizations.getText();
	}

	@Override
	public void setPreviousMentalHealthHospitalizations(
			String previousMentalHealthHospitalizations) {
		this.previousMentalHealthHospitalizations.setText(previousMentalHealthHospitalizations);
	}

	@Override
	public String getCurrentMedications() {
		return currentMedications.getText();
	}

	@Override
	public void setCurrentMedications(String currentMedications) {
		this.currentMedications.setText(currentMedications);
	}

	@Override
	public String getGuardian() {
		return guardian.getText();
	}

	@Override
	public void setGuardian(String guardian) {
		this.guardian.setText(guardian);
	}

	@Override
	public Date getFirstAppointmentOfferedDatePicker() {
		return firstAppointmentOfferedDatePicker.getValue();
	}

	@Override
	public void setFirstAppointmentOfferedDatePicker(
			Date firstAppointmentOfferedDatePicker) {
		this.firstAppointmentOfferedDatePicker.setValue(firstAppointmentOfferedDatePicker);
	}

	@Override
	public String getFirstAppointmentOffered() {
		return firstAppointmentOffered.getText();
	}

	@Override
	public void setFirstAppointmentOffered(String firstAppointmentOffered) {
		this.firstAppointmentOffered.setText(firstAppointmentOffered);
	}

	@Override
	public Date getFirstAppointmentTakenDatePicker() {
		return firstAppointmentTakenDatePicker.getValue();
	}

	@Override
	public void setFirstAppointmentTakenDatePicker(
			Date firstAppointmentTakenDatePicker) {
		this.firstAppointmentTakenDatePicker.setValue(firstAppointmentTakenDatePicker);
	}

	@Override
	public String getFirstAppointmentTaken() {
		return firstAppointmentTaken.getText();
	}

	@Override
	public void setFirstAppointmentTaken(String firstAppointmentTaken) {
		this.firstAppointmentTaken.setText(firstAppointmentTaken);
	}

	@Override
	public boolean isInformedPatient() {
		return informedPatient.isEnabled();
	}

	@Override
	public void setInformedPatient(boolean informedPatient) {
		this.informedPatient.setEnabled(informedPatient);
	}

	@Override
	public String getReferralSource() {
		return referralSource.getText();
	}

	@Override
	public void setReferralSource(String referralSource) {
		this.referralSource.setText(referralSource);
	}

	@Override
	public String getReferralSourcePhoneNumber() {
		return referralSourcePhoneNumber.getText();
	}

	@Override
	public void setReferralSourcePhoneNumber(String referralSourcePhoneNumber) {
		this.referralSourcePhoneNumber.setText(referralSourcePhoneNumber);
	}

	@Override
	public String getServiceRequest() {
		return serviceRequest.getText();
	}

	@Override
	public void setServiceRequest(String serviceRequest) {
		this.serviceRequest.setText(serviceRequest);
	}

	@Override
	public String getReasonDenied() {
		return reasonDenied.getText();
	}

	@Override
	public void setReasonDenied(String reasonDenied) {
		this.reasonDenied.setText(reasonDenied);
	}

	@Override
	public String getReferredTo() {
		return referredTo.getText();
	}

	@Override
	public void setReferredTo(String referredTo) {
		this.referredTo.setText(referredTo);
	}

	@Override
	public String getOtherPertinentInfo() {
		return otherPertinentInfo.getText();
	}

	@Override
	public void setOtherPertinentInfo(String otherPertinentInfo) {
		this.otherPertinentInfo.setText(otherPertinentInfo);
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName.setText(firstName);
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName.setText(lastName);
	}

	@Override
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial.setText(middleInitial);
	}

	@Override
	public void setDob(String dob) {
		this.dob.setText(dob);
	}
}
