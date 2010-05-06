/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Vector;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpcServices")
public interface RpcServices extends RemoteService {
	public EHRDTO getEhr(long ehrId, String sessionId) throws SessionExpiredException, DbException;

	public LinkedHashMap<String, Long> getPatientList(String sessionId) throws SessionExpiredException, DbException;

	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException;

	public Messages getMessages(String patientId, String sessionId) throws SessionExpiredException, DbException;

	public ArrayList<String> getMessageTypes(String sessionId) throws SessionExpiredException, DbException;

	public void addMessage(Message msg, String sessionId) throws SessionExpiredException, DbException;

	public ServiceCodes getServiceCodes(String sessionId) throws SessionExpiredException, DbException;

	public UserData getUserData(String username, String sessionId) throws DbException, SessionExpiredException;

	public LinkedHashSet<String[]> getFormsList(String sessionId, String patientId) throws SessionExpiredException, DbException;

	public EHRDTO editEhr(EHRDTO ehrDto, String sessionId) throws SessionExpiredException, DbException;

	public DemographicsDTO getDemographics(String patientid, String sessionId) throws SessionExpiredException, DbException;

	public PatientDTO getPatient(long patientId, String sessionId) throws SessionExpiredException, DbException;

	public void addEhr(PatientDTO patient, String sessionId) throws SessionExpiredException, DbException;
}
