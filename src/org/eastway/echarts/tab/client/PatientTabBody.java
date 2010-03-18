package org.eastway.echarts.tab.client;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class PatientTabBody extends Composite {
	private FlowPanel body = new FlowPanel();
	private MessagesPresenter mp;
	private Patient patient = new Patient();

	public PatientTabBody(HandlerManager eventBus, String patientId) {
		initWidget(body);
		this.patient.setPatientId(patientId);
		fetchPatient();
		mp = new MessagesPresenter(new MessagesView(), eventBus, Rpc.singleton(), patient);
		mp.go(body);
	}

	private void fetchPatient() {
		AsyncCallback<Patient> callback = new AsyncCallback<Patient>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Patient patient) {
				setPatient(patient);
			}
		};
		Rpc.singleton().getPatient(patient.getPatientId(), UserImpl.getSessionId(),
				callback);
	}

	protected void setPatient(Patient patient) {
		this.patient = patient;
	}
}
