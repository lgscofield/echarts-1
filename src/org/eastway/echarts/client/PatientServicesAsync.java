package org.eastway.echarts.client;

import java.util.LinkedHashMap;

import org.eastway.echarts.shared.PatientDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PatientServicesAsync {
	public void getPatient(long patientId, String sessionId, AsyncCallback<PatientDTO> callback);

	public void getPatientList(String sessionId, AsyncCallback<LinkedHashMap<String, Long>> callback);
}
