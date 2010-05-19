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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.MessageDTO;
import org.eastway.echarts.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcServicesAsync {
	public void getAlerts(String sessionId, AsyncCallback<Vector<String>> callback);

	public void getMessages(long ehrId, String sessionId, AsyncCallback<List<MessageDTO>> callback);

	public void getMessageTypes(String sessionId, AsyncCallback<List<CodeDTO>> callback);

	public void addMessage(MessageDTO msg, String sessionId, AsyncCallback<MessageDTO> callback);

	public void getUser(String username, String cookie, AsyncCallback<UserDTO> callback);

	public void getFormsList(String sessionId, String patientId, AsyncCallback<LinkedHashSet<String[]>> callback);
}
