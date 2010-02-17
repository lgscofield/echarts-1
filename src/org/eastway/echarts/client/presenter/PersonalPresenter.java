package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PersonalPresenter extends EchartsPresenter<PersonalPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(Patient data);
	}

	private String patientId;
	private PatientServicesAsync patientSvc;

	public PersonalPresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
		this.patientId = patientId;
		this.patientSvc = patientSvc;
		setData();
	}

	private void setData() {
		AsyncCallback<Patient> callback =
				new AsyncCallback<Patient>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Patient data) {
				display.setData(data);
			};
		};
		patientSvc.getPatient(patientId, Cookies.getCookie("sessionId"),
				callback);
	}
}
