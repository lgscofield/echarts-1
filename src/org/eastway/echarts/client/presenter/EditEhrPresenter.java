package org.eastway.echarts.client.presenter;

import java.util.Date;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;

public class EditEhrPresenter extends Presenter<EditEhrPresenter.Display> {
	public interface Display extends EchartsDisplay {
		HasClickHandlers getFinishButton();

		String getFirstName();

		String getLastName();

		String getMiddleInitial();

		Date getDob();

		String getCaseNumber();

		void setCaseNumber(TextBox caseNumber);

		String getPreviousProvider();

		void setPreviousProvider(String previousProvider);

		Date getPreviousVisit();

		void setPreviousVisit(Date previousVisit);

		String getSsn();

		void setSsn(String ssn);

		String getCounty();

		void setCounty(String county);

		String getAddress();

		void setAddress(String address);

		String getPhone();

		void setPhone(String phone);

		String getAltPhone();

		void setAltPhone(String altPhone);

		boolean isAod();

		void setAod(boolean useAod);

		boolean isRequiredAod();

		void setRequiredAod(boolean requiredAod);

		boolean hasHistoryOfTreatment();

		void setHistoryOfTreatment(boolean historyOfTreatment);

		String getTreatmentDescription();

		void setTreatmentDescription(String treatmentDescription);

		boolean isPregnant();

		void setPregnant(boolean isPregnant);

		boolean isIvUser();

		void setIvUser(boolean isIvUser);

		String getReferredBy();

		void setReferredBy(String referredBy);

		String getEmergencyContact();

		void setEmergencyContact(String emergencyContact);

		String getEmergencyContactPhoneNumber();

		void setEmergencyContactPhoneNumber(
				String emergencyContactPhoneNumber);

		String getEmergencyContactRelationshipToPatient();

		void setEmergencyContactRelationshipToPatient(
				String emergencyContactRelationshipToPatient);

		boolean isEmployed();

		void setEmployed(boolean isEmployed);

		String getLastEmployedDate();

		void setLastEmployedDate(String lastEmployedDate);

		String getInsuranceType();

		void setInsuranceType(String insuranceType);

		String getIncome();

		void setIncome(String income);

		boolean hasDesireToWork();

		void setDesireToWork(boolean hasDesireToWork);

		boolean hasEmploymentBarriers();

		void setEmploymentBarriers(boolean hasEmploymentBarriers);

		boolean hasLegalProblems();

		void setLegalProblems(boolean hasLegalProblems);

		String getLegalProblemsDescription();

		void setLegalProblemsDescription(String legalProblemsDescription);

		boolean isReceivingSsdForMhReasons();

		void setReceivingSsdForMhReasons(
				boolean isReceivingSsdForMhReasons);

		boolean hasRecentlyVisitedErOrHospital();

		void setRecentlyVisitedErOrHospital(
				boolean hasRecentlyVisitedErOrHospital);

		String getRecentlyReceivedTreatment();

		void setRecentlyReceivedTreatment(
				String recentlyReceivedTreatment);

		String getPreviousMentalHealthTreatment();

		void setPreviousMentalHealthTreatment(
				String previousMentalHealthTreatment);

		String getPreviousMentalHealthHospitalizations();

		void setPreviousMentalHealthHospitalizations(
				String previousMentalHealthHospitalizations);

		String getCurrentMedications();

		void setCurrentMedications(String currentMedications);

		String getGuardian();

		void setGuardian(String guardian);

		Date getFirstAppointmentOfferedDatePicker();

		void setFirstAppointmentOfferedDatePicker(
				Date firstAppointmentOfferedDatePicker);

		String getFirstAppointmentOffered();

		void setFirstAppointmentOffered(String firstAppointmentOffered);

		Date getFirstAppointmentTakenDatePicker();

		void setFirstAppointmentTakenDatePicker(
				Date firstAppointmentTakenDatePicker);

		String getFirstAppointmentTaken();

		void setFirstAppointmentTaken(String firstAppointmentTaken);

		boolean isInformedPatient();

		void setInformedPatient(boolean informedPatient);

		String getReferralSource();

		void setReferralSource(String referralSource);

		String getReferralSourcePhoneNumber();

		void setReferralSourcePhoneNumber(
				String referralSourcePhoneNumber);

		String getServiceRequest();

		void setServiceRequest(String serviceRequest);

		String getReasonDenied();

		void setReasonDenied(String reasonDenied);

		String getReferredTo();

		void setReferredTo(String referredTo);

		String getOtherPertinentInfo();

		void setOtherPertinentInfo(String otherPertinentInfo);

		void setFirstName(String firstName);

		void setLastName(String lastName);

		void setMiddleInitial(String middleInitial);

		void setDob(String dob);
	}

	private RpcServicesAsync rpcServices;
	private Patient patient;

	public EditEhrPresenter(Display display, HandlerManager eventBus,
					RpcServicesAsync rpcServices) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
	}

	public EditEhrPresenter(Display display, HandlerManager eventBus,
					Patient patient,
					RpcServicesAsync rpcServices) {
		super(display, eventBus);
		this.patient = patient;
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
		patient.setPatientId(display.getCaseNumber());
		patient.setFirstName(display.getFirstName());
		patient.setLastName(display.getLastName());
		//patient.setDob(display.getDob());

		AsyncCallback<Patient> callback = new AsyncCallback<Patient>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Patient result) {
				setPatient(result);
				//setData(result);
			}
		};
		rpcServices.saveEhr(patient, UserImpl.getSessionId(), callback);
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

//	public void setData(EH chart) {
//		Patient patient = fetchPatient(chart.getSubjectId());
//		display.setAddress(chart.getAddress());
//		display.setAltPhone(chart.getAltPhone());
//		display.setCaseNumber(chart.getCaseNumber());
//		display.setCounty(chart.getCounty());
//		display.setCurrentMedications(chart.getCurrentMedications());
//		display.setDob(chart.getDob());
//		display.setEmergencyContact(chart.getEmergencyContact());
//		display.setEmergencyContactPhoneNumber(chart.getEmergencyContactPhoneNumber());
//		display.setEmergencyContactRelationshipToPatient(chart.getEmergencyContactRelationshipToPatient());
//		display.setFirstAppointmentOffered(chart.getFirstAppointmentOfferred());
//		display.setFirstAppointmentTaken(chart.getFirstAppointmentTaken());
//		display.setFirstName(chart.getFirstName());
//		display.setGuardian(chart.getGuardian());
//		display.setHasDesireToWork(chart.getHasDesireToWork());
//		display.setHasEmploymentBarriers(chart.getHasEmploymentBarriers());
//		display.setHasLegalProblems(chart.getHasLegalProblems());
//		display.setHasRecentlyVisitedErOrHospital(chart.getHasRecentlyVisitedErOrHospital());
//		display.setHistoryOfTreatment(chart.getHistoryOfTreatment());
//		display.setIncome(chart.getIncome());
//		display.setInformedPatient(chart.getInformedPatient());
//		display.setInsuranceType(chart.getInsuranceType());
//		display.setIsEmployed(chart.getIsEmployed());
//		display.setIsIvUser(chart.getIsIvUser());
//		display.setIsPregnant(chart.getIsPregnant());
//		display.setIsReceivingSsdForMhReasons(chart.getIsReceivingSsdForMhReasons());
//		display.setLastEmployedDate(chart.getLastEmployedDate());
//		display.setLastName(chart.getLastName());
//		display.setLegalProblemsDescription(chart.getLegalProblemsDescription());
//		display.setMiddleInitial(chart.getMiddleInitial());
//		display.setOtherPertinentInfo(chart.getOtherPertinentInfo());
//		display.setPhone(chart.getPhone());
//		display.setPreviousMentalHealthHospitalizations(chart.getPreviousMentalHealthHospitalizations());
//		display.setPreviousMentalHealthTreatment(chart.getPreviousMentalHealthTreatment());
//		display.setPreviousProvider(chart.getPreviousProvider());
//		display.setPreviousVisit(chart.getPreviousVisit());
//		display.setReasonDenied(chart.getReasonDenied());
//		display.setRecentlyReceivedTreatment(chart.getRecentlyReceivedTreatment());
//		display.setReferralSource(chart.getReferralSource());
//		display.setReferralSourcePhoneNumber(chart.getReferralSourcePhoneNumber());
//		display.setReferredBy(chart.getReferredBy());
//		display.setReferredTo(chart.getReferredTo());
//		display.setRequiredAod(chart.getRequiredAod());
//		display.setServiceRequest(chart.getServiceRequest());
//		display.setSsn(chart.getSsn());
//		display.setTreatmentDescription(chart.getTreatmentDescription());
//		display.setUseAod(chart.getUseAod());
//	}

	private Patient fetchPatient(int subjectId) {
		return null;
	}
}
