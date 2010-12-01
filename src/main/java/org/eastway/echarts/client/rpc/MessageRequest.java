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
package org.eastway.echarts.client.rpc;

import java.util.List;

import org.eastway.echarts.domain.Message;
import org.eastway.echarts.shared.MessageProxy;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(Message.class)
public interface MessageRequest extends RequestContext {
	Request<MessageProxy> findMessage(Long id);
	Request<List<MessageProxy>> findMessageByCaseNumber(String caseNumber);
	//Request<List<MessageProxy>> findMessageEntriesByCaseNumber(String caseNumber, int firstResult, int maxResults);
	InstanceRequest<MessageProxy, Void> persist();
	InstanceRequest<MessageProxy, Void> remove();
}
