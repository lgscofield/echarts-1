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
package org.eastway.echarts.server;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.CodeService;
import org.eastway.echarts.domain.Message;
import org.eastway.echarts.domain.MessageService;
import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SaveMessage;
import org.eastway.echarts.shared.SaveMessageResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class SaveMessageHandler implements ActionHandler<SaveMessage, SaveMessageResult> {
	@Override
	public synchronized SaveMessageResult execute(SaveMessage action, ExecutionContext context)
			throws ActionException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		MessageService ms = new MessageService(em);
		CodeService cs = new CodeService(em);
		Code mType = cs.find(action.getMessage().getMessageType().getCodeId());
		Message parent = null;
		if (action.getMessage().getParent() != null)
			parent = ms.find(action.getMessage().getParent().getId());
		em.getTransaction().begin();
		Message newMessage = ms.create(action.getMessage().getCreationTimestamp(),
				action.getMessage().getCaseNumber(), action.getMessage().getLastEdit(),
				action.getMessage().getLastEditBy(), action.getMessage().getMessage(),
				mType, parent);
		em.getTransaction().commit();
		em.close();
		return new SaveMessageResult(newMessage.toDto());
	}

	@Override
	public Class<SaveMessage> getActionType() {
		return SaveMessage.class;
	}

	@Override
	public synchronized void rollback(SaveMessage action, SaveMessageResult result,
			ExecutionContext context) throws ActionException {
		
	}

}
