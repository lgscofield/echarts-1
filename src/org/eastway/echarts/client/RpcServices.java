package org.eastway.echarts.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Vector;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpcServices")
public interface RpcServices extends RemoteService {
	public EHR getEhr(long ehrId, String sessionId) throws SessionExpiredException, DbException;

	public LinkedHashMap<String, Long> getPatientList(String sessionId) throws SessionExpiredException, DbException;

	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException;

	public Messages getMessages(String patientId, String sessionId) throws SessionExpiredException, DbException;

	public ArrayList<String> getMessageTypes(String sessionId) throws SessionExpiredException, DbException;

	public void addMessage(Message msg, String sessionId) throws SessionExpiredException, DbException;

	public ServiceCodes getServiceCodes(String sessionId) throws SessionExpiredException, DbException;

	public UserData getUserData(String username, String sessionId) throws DbException, SessionExpiredException;

	public LinkedHashSet<String[]> getFormsList(String sessionId, String patientId) throws SessionExpiredException, DbException;

	public Patient saveEhr(Patient patient, String sessionId) throws SessionExpiredException, DbException;

	public Demographics getDemographics(String patientid, String sessionId) throws SessionExpiredException, DbException;

	public Patient getPatient(long patientId, String sessionId) throws SessionExpiredException, DbException;

	public void newEhr(Patient patient, String sessionId) throws SessionExpiredException, DbException;
}
