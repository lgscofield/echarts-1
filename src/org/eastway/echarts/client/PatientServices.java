package org.eastway.echarts.client;

import java.util.ArrayList;
import java.util.Vector;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patientServices")
public interface PatientServices extends RemoteService {
	public Patient getPatient(String cid, String sessionId) throws SessionExpiredException, DbException;

	public Vector<String> getPatientList(String sessionId) throws SessionExpiredException, DbException;

	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException;

	public Messages getMessages(String patientId, String sessionId) throws SessionExpiredException, DbException;

	public ArrayList<String> getMessageTypes(String sessionId) throws SessionExpiredException, DbException;

	public void addMessage(Message msg, String sessionId) throws SessionExpiredException, DbException;

	public ServiceCodes getServiceCodes(String sessionId) throws SessionExpiredException, DbException;

	public String validateUser(String username, String password) throws DbException;

	public UserData getUserData(String sessionId) throws DbException, SessionExpiredException;
}
