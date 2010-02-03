package org.eastway.echarts.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.ServiceCodes;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PatientServicesAsync {
	public void getPatientDemo(String u, String sessionId, AsyncCallback<HashMap<String, String>> callback);

	public void getPatientList(String sessionId, AsyncCallback<Vector<String>> callback);

	public void getAlerts(String sessionId, AsyncCallback<Vector<String>> callback);

	public void getMessages(String p, String sessionId, AsyncCallback<Messages> callback);

	public void getMessageTypes(String sessionId, AsyncCallback<ArrayList<String>> callback);

	public void addMessage(Message msg, String sessionId, AsyncCallback<Void> callback);

	public void getTicket(Integer ticketnum, String sessionId, AsyncCallback<String> callback);

	public void getServiceCodes(String sessionId, AsyncCallback<ServiceCodes> callback);

	public void getProgressNoteBody(String service, String sessionId, AsyncCallback<String> callback);

	public void validateUser(String username, String password, AsyncCallback<String> callback);
}
