package org.eastway.echarts.server;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.ReferralImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetReferral;
import org.eastway.echarts.shared.GetReferralResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetReferralHandler implements ActionHandler<GetReferral, GetReferralResult> {

	@Override
	public GetReferralResult execute(GetReferral action, ExecutionContext context)
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
		ReferralImpl referral = em.find(ReferralImpl.class, action.getCaseNumber());
		em.close();
		return new GetReferralResult(referral.toDto());
	}

	@Override
	public Class<GetReferral> getActionType() {
		return GetReferral.class;
	}

	@Override
	public void rollback(GetReferral action, GetReferralResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
