package org.eastway.echarts.client.presenter;

import java.util.HashMap;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PersonalPresenter extends EchartsPresenter<PersonalPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(HashMap<String, String> data);
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
		AsyncCallback<HashMap<String, String>> callback =
				new AsyncCallback<HashMap<String, String>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(HashMap<String, String> data) {
				display.setData(data);
			};
		};
		patientSvc.getPatientDemo(patientId, Cookies.getCookie("sessionId"),
				callback);
	}
}
