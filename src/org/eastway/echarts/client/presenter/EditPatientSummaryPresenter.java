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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditPatientSummaryPresenter extends Presenter<EditPatientSummaryPresenter.Display> {

	public interface Display extends EchartsDisplay {
		HasText getCaseNumber();

		void setCaseNumber(HasText caseNumber);

		HasText getFirstName();

		void setFirstName(HasText firstName);

		HasText getLastName();

		void setLastName(HasText lastName);

		HasText getMiddleInitial();

		void setMiddleInitial(HasText middleInitial);

		HasValue<Date> getDob();

		void setDob(HasValue<Date> dob);

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

		HasClickHandlers getSave();

		void setSave(HasClickHandlers save);
	}

	private RpcServicesAsync rpcServices;
	private Patient patient;

	public EditPatientSummaryPresenter(
			Display display,
			HandlerManager eventBus, RpcServicesAsync rpcServices,
			Patient patient) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
		this.patient = patient;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
		setData();
	}

	private void setData() {
		//display.getAddress().setText(patient.getAddress());
		//display.getAltPhone().setText(patient.getAltPhone());
		display.getCaseNumber().setText(patient.getCaseNumber());
		//display.getCounty().setText(patient.getCounty());
		display.getDob().setValue(patient.getDemographics().getDob());
		display.getFirstName().setText(patient.getFirstName());
		display.getLastName().setText(patient.getLastName());
		display.getMiddleInitial().setText(patient.getMiddleInitial());
		//display.getPhone().setText(patient.getPhone());
		display.getSsn().setText(patient.getSsn());
	}

	private void bind() {
		display.getSave().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
	}

	private void doSave() {
		patient.setCaseNumber(display.getCaseNumber().getText());
		patient.setFirstName(display.getFirstName().getText());
		patient.setLastName(display.getLastName().getText());
		patient.setMiddleInitial(display.getMiddleInitial().getText());
		patient.getDemographics().setDob(display.getDob().getValue());
		patient.setSsn(display.getSsn().getText());
		//patient.setCounty(display.getCounty().getText());
		//patient.setAddress(display.getAddress().getText());
		//patient.setPhone(display.getPhone().getText());
		//patient.setAltPhone(display.getAltPhone().getText());

		AsyncCallback<Patient> callback = new AsyncCallback<Patient>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Patient result) {
				;
			}
		};
		rpcServices.saveEhr(patient, UserImpl.getSessionId(), callback);
	}
}