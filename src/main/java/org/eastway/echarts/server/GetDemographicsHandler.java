package org.eastway.echarts.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.DemographicsImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDemographicsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetDemographicsHandler implements
		ActionHandler<GetDemographics, GetDemographicsResult> {
	@Override
	public GetDemographicsResult execute(GetDemographics action,
			ExecutionContext context) throws ActionException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		TypedQuery<DemographicsImpl> query = em.createQuery(
				"SELECT d FROM DemographicsImpl d WHERE d.id = " + action.getCaseNumber(), DemographicsImpl.class);
		GetDemographicsResult result = new GetDemographicsResult(query.getSingleResult().toDto());
		em.close();
		emf.close();
		return result;
	}

	@Override
	public Class<GetDemographics> getActionType() {
		return GetDemographics.class;
	}

	@Override
	public void rollback(GetDemographics demographics,
			GetDemographicsResult result, ExecutionContext context)
			throws ActionException {

	}
}
