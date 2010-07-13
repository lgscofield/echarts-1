package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.ContactImpl;
import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetContactsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetContactsHandler implements
		ActionHandler<GetContacts, GetContactsResult> {

	@Override
	public GetContactsResult execute(GetContacts action, ExecutionContext context)
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
		List<ContactImpl> contacts = em.createQuery(
				"SELECT c FROM ContactImpl c WHERE c.caseNumber = '" + action.getCaseNumber() + "'", ContactImpl.class)
				.getResultList();
		List<Contact> dto = new ArrayList<Contact>();
		for (ContactImpl contact : contacts)
			dto.add(contact.toDto());
		em.close();
		return new GetContactsResult(dto);
	}

	@Override
	public Class<GetContacts> getActionType() {
		return GetContacts.class;
	}

	@Override
	public void rollback(GetContacts arg0, GetContactsResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
