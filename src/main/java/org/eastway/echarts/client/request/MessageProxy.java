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
package org.eastway.echarts.client.request;

import java.util.Date;

import org.eastway.echarts.domain.Message;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Message.class)
public interface MessageProxy extends EntityProxy {
	EntityProxyId<MessageProxy> stableId();
	String getCaseNumber();
	CodeProxy getMessageType();
	Date getCreationTimestamp();
	String getMessage();
	Date getLastEdit();
	String getLastEditBy();
	MessageProxy getParent();
	void setCaseNumber(String caseNumber);
	void setMessageType(CodeProxy messageType);
	void setCreationTimestamp(Date timestamp);
	void setMessage(String message);
	void setLastEdit(Date lastEdit);
	void setLastEditBy(String lastEditBy);
	void setParent(MessageProxy parent);
}
