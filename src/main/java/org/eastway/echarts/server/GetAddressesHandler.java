package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.AddressImpl;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetAddressesResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetAddressesHandler implements ActionHandler<GetAddresses, GetAddressesResult> {

	@Override
	public GetAddressesResult execute(GetAddresses action, ExecutionContext context)
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
		TypedQuery<AddressImpl> query = em.createQuery(
				"SELECT a FROM AddressImpl a where a.caseNumber = '" + action.getCaseNumber() + "'", AddressImpl.class);
		List<AddressImpl> addresses = query.getResultList();
		List<Address> dtos = new ArrayList<Address>();
		for (AddressImpl address : addresses)
			dtos.add(address.toDto());
		em.close();
		return new GetAddressesResult(dtos);
	}

	@Override
	public Class<GetAddresses> getActionType() {
		return GetAddresses.class;
	}

	@Override
	public void rollback(GetAddresses arg0, GetAddressesResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
