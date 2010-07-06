package org.eastway.echarts.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
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
		emf.close();
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
