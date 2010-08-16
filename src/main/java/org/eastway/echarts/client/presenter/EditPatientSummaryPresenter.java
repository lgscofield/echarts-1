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

//import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.rpc.HandleRpcException;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditPatientSummaryPresenter implements Presenter {

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

	//private EHRServicesAsync ehrServices;
	private Patient patient;
	private Display display;

//	public EditPatientSummaryPresenter(
//			Display display,
//			HandlerManager eventBus, EHRServicesAsync ehrServices,
//			Patient patient) {
//		this.ehrServices = ehrServices;
//		this.patient = patient;
//		this.display = display;
//	}

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
		patient.setSsn(display.getSsn().getText());
		//patient.setCounty(display.getCounty().getText());
		//patient.setAddress(display.getAddress().getText());
		//patient.setPhone(display.getPhone().getText());
		//patient.setAltPhone(display.getAltPhone().getText());

		//EHR ehr = new EHRDTO(patient.getEhrId());
		//ehr.setSubject(patient);

		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR result) {
				;
			}
		};
		//ehrServices.editEhr(ehr, EchartsUser.sessionId, callback);
	}
}
