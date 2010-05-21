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
package org.eastway.echarts.client.presenter;

import java.util.Date;

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddEhrPresenter extends Presenter<AddEhrPresenter.Display> {
	public interface Display extends EchartsDisplay {
		HasClickHandlers getFinishButton();

		HasText getFirstName();

		HasText getLastName();

		HasText getMiddleInitial();

		HasValue<Date> getDob();

		HasText getCaseNumber();

		void setCaseNumber(HasText caseNumber);

		HasText getPreviousProvider();

		void setPreviousProvider(HasText previousProvider);

		HasValue<Date> getPreviousVisit();

		void setPreviousVisit(HasValue<Date> previousVisit);

		HasText getSsn();

		void setSsn(HasText ssn);

		HasText getCounty();

		void setCounty(HasText county);

		HasText getAddress();

		void setAddress(HasText address);

		HasText getPhone();

		void setPhone(HasText phone);

		HasText getAltPhone();

		void setAltPhone(HasText altPhone);

		HasValue<Boolean> isAod();

		void setAod(HasValue<Boolean> useAod);

		HasValue<Boolean> isRequiredAod();

		void setRequiredAod(HasValue<Boolean> requiredAod);

		HasValue<Boolean> hasHistoryOfTreatment();

		void setHistoryOfTreatment(HasValue<Boolean> historyOfTreatment);

		HasText getTreatmentDescription();

		void setTreatmentDescription(HasText treatmentDescription);

		HasValue<Boolean> isPregnant();

		void setPregnant(HasValue<Boolean> isPregnant);

		HasValue<Boolean> isIvUser();

		void setIvUser(HasValue<Boolean> isIvUser);

		HasText getReferredBy();

		void setReferredBy(HasText referredBy);

		HasText getEmergencyContact();

		void setEmergencyContact(HasText emergencyContact);

		HasText getEmergencyContactPhoneNumber();

		void setEmergencyContactPhoneNumber(
				HasText emergencyContactPhoneNumber);

		HasText getEmergencyContactRelationshipToPatient();

		void setEmergencyContactRelationshipToPatient(
				HasText emergencyContactRelationshipToPatient);

		HasValue<Boolean> isEmployed();

		void setEmployed(HasValue<Boolean> isEmployed);

		HasText getLastEmployedDate();

		void setLastEmployedDate(HasText lastEmployedDate);

		HasText getInsuranceType();

		void setInsuranceType(HasText insuranceType);

		HasText getIncome();

		void setIncome(HasText income);

		HasValue<Boolean> hasDesireToWork();

		void setDesireToWork(HasValue<Boolean> hasDesireToWork);

		HasValue<Boolean> hasEmploymentBarriers();

		void setEmploymentBarriers(HasValue<Boolean> hasEmploymentBarriers);

		HasValue<Boolean> hasLegalProblems();

		void setLegalProblems(HasValue<Boolean> hasLegalProblems);

		HasText getLegalProblemsDescription();

		void setLegalProblemsDescription(HasText legalProblemsDescription);

		HasValue<Boolean> isReceivingSsdForMhReasons();

		void setReceivingSsdForMhReasons(
				HasValue<Boolean> isReceivingSsdForMhReasons);

		HasValue<Boolean> hasRecentlyVisitedErOrHospital();

		void setRecentlyVisitedErOrHospital(
				HasValue<Boolean> hasRecentlyVisitedErOrHospital);

		HasText getRecentlyReceivedTreatment();

		void setRecentlyReceivedTreatment(
				HasText recentlyReceivedTreatment);

		HasText getPreviousMentalHealthTreatment();

		void setPreviousMentalHealthTreatment(
				HasText previousMentalHealthTreatment);

		HasText getPreviousMentalHealthHospitalizations();

		void setPreviousMentalHealthHospitalizations(
				HasText previousMentalHealthHospitalizations);

		HasText getCurrentMedications();

		void setCurrentMedications(HasText currentMedications);

		HasText getGuardian();

		void setGuardian(HasText guardian);

		HasValue<Date> getFirstAppointmentOfferedDatePicker();

		void setFirstAppointmentOfferedDatePicker(
				HasValue<Date> firstAppointmentOfferedDatePicker);

		HasText getFirstAppointmentOffered();

		void setFirstAppointmentOffered(HasText firstAppointmentOffered);

		HasValue<Date> getFirstAppointmentTakenDatePicker();

		void setFirstAppointmentTakenDatePicker(
				HasValue<Date> firstAppointmentTakenDatePicker);

		HasText getFirstAppointmentTaken();

		void setFirstAppointmentTaken(HasText firstAppointmentTaken);

		HasValue<Boolean> isInformedPatient();

		void setInformedPatient(HasValue<Boolean> informedPatient);

		HasText getReferralSource();

		void setReferralSource(HasText referralSource);

		HasText getReferralSourcePhoneNumber();

		void setReferralSourcePhoneNumber(
				HasText referralSourcePhoneNumber);

		HasText getServiceRequest();

		void setServiceRequest(HasText serviceRequest);

		HasText getReasonDenied();

		void setReasonDenied(HasText reasonDenied);

		HasText getReferredTo();

		void setReferredTo(HasText referredTo);

		HasText getOtherPertinentInfo();

		void setOtherPertinentInfo(HasText otherPertinentInfo);

		void setFirstName(HasText firstName);

		void setLastName(HasText lastName);

		void setMiddleInitial(HasText middleInitial);

		void setDob(HasValue<Date> dob);

		HasText getCaseStatus();

		HasText getSuffix();

		HasText getAlias();
	}

	private EHRServicesAsync rpcServices;

	public AddEhrPresenter(Display display, HandlerManager eventBus,
					EHRServicesAsync rpcServices) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	public void go() {
		bind();
	}

	private void bind() {
		display.getFinishButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doFinish();
			}
		});
	}

	private void doFinish() {
		EHRDTO ehrDto = new EHRDTO();
		PatientDTO patient = new PatientDTO();
		DemographicsDTO d = new DemographicsDTO();
		ehrDto.setSubject(patient);
		patient.setDemographics(d);

		patient.setCaseNumber(display.getCaseNumber().getText());
		patient.setFirstName(display.getFirstName().getText());
		patient.setLastName(display.getLastName().getText());
		patient.setMiddleInitial(display.getMiddleInitial().getText());
		patient.setSuffix(display.getSuffix().getText());
		patient.setAlias(display.getAlias().getText());
		patient.setSsn(display.getSsn().getText());
		patient.setCaseStatus(display.getCaseStatus().getText());
		patient.getDemographics().setDob(display.getDob().getValue());
		patient.setLastEdit(new Date());
		patient.setLastEditBy(UserImpl.getUserName());

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Void result) {
				;
			}
		};
		rpcServices.addEhr(ehrDto, UserImpl.getSessionId(), callback);
	}
}
