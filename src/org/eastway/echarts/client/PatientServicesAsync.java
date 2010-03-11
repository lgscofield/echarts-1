package org.eastway.echarts.client;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Vector;

import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PatientServicesAsync {
	public void getPatient(String u, String sessionId, AsyncCallback<Patient> callback);

	public void getPatientList(String sessionId, AsyncCallback<Vector<String>> callback);

	public void getAlerts(String sessionId, AsyncCallback<Vector<String>> callback);

	public void getMessages(String p, String sessionId, AsyncCallback<Messages> callback);

	public void getMessageTypes(String sessionId, AsyncCallback<ArrayList<String>> callback);

	public void addMessage(Message msg, String sessionId, AsyncCallback<Void> callback);

	public void getServiceCodes(String sessionId, AsyncCallback<ServiceCodes> callback);

	public void getUserData(String cookie, AsyncCallback<UserData> callback);

	public void getFormsList(String sessionId, String patientId, AsyncCallback<LinkedHashSet<String[]>> callback);
}
