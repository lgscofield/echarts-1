package org.eastway.echarts.client;

import java.util.LinkedHashMap;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patientServices")
public interface PatientServices extends RemoteService {
	public PatientDTO getPatient(long patientId, String sessionId) throws SessionExpiredException, DbException;

	public LinkedHashMap<String, Long> getPatientList(String sessionId) throws SessionExpiredException, DbException;
}
