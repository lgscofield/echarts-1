package org.eastway.echarts.client.presenter;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class PatientPresenter extends Presenter<PatientPresenter.Display> {

	private Patient patient;
	private String patientid;

	public PatientPresenter(Display display,
			HandlerManager eventBus, RpcServicesAsync singleton,
			String patientid) {
		super(display, eventBus);
		this.patientid = patientid;
	}

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data, int ncols);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchPatient();
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
				setPersonalData();
			}
		};
		Rpc.singleton().getPatient(patientid, UserImpl.getSessionId(),
				callback);
	}

	protected void setPatient(Patient patient) {
		this.patient = patient;
	}

	void setPersonalData() {
		LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
		// TODO: the first value here could easily be set by
		// patient.getPatientIdTitle() or some such.  This way it could
		// be stored in the database.
		data.add(new String[] { "Patient Id", patient.getPatientId() });
		data.add(new String[] { "Name", patient.getName() });
		display.setData(data, data.size());
	}
}
