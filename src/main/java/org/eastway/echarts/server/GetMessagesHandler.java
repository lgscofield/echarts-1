package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.CodeImpl;
import org.eastway.echarts.domain.CodeService;
import org.eastway.echarts.domain.Message;
import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.GetMessagesResult;
import org.eastway.echarts.shared.MessageDTO;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetMessagesHandler implements ActionHandler<GetMessages, GetMessagesResult> {

	@Override
	public GetMessagesResult execute(GetMessages action, ExecutionContext context)
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
		List<Message> messages = em.createQuery(
				"SELECT m FROM Message m WHERE m.caseNumber = '" + action.getCaseNumber() + "'", Message.class)
				.getResultList();
		List<MessageDTO> messagesDto = new ArrayList<MessageDTO>();
		for (Message m : messages)
			messagesDto.add(m.toDto());
		CodeService cs = new CodeService(em);
		List<CodeImpl> mtList = cs.findColumnName("MessageType");
		List<CodeDTO> mtsl = new ArrayList<CodeDTO>();
		for (Code mt : mtList)
			mtsl.add(mt.toDto());
		em.close();
		return new GetMessagesResult(messagesDto, mtsl);
	}

	@Override
	public Class<GetMessages> getActionType() {
		return GetMessages.class;
	}

	@Override
	public void rollback(GetMessages arg0, GetMessagesResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}
}
