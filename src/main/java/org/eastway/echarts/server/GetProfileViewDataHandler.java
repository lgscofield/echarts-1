package org.eastway.echarts.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.CodeImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProfileViewData;
import org.eastway.echarts.shared.GetProfileViewDataResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetProfileViewDataHandler implements
		ActionHandler<GetProfileViewData, GetProfileViewDataResult> {

	@Override
	public Class<GetProfileViewData> getActionType() {
		return GetProfileViewData.class;
	}

	@Override
	public GetProfileViewDataResult execute(GetProfileViewData action,
			ExecutionContext context) throws DispatchException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		Map<String, String> costCenters = new HashMap<String, String>();
		List<CodeImpl> codes = em.createQuery("SELECT c FROM CodeImpl c WHERE c.columnName = 'CostCenter'", CodeImpl.class)
							.getResultList();
		for (CodeImpl c : codes)
			costCenters.put(c.getValue(), c.getDescriptor());
		return new GetProfileViewDataResult(costCenters);
	}

	@Override
	public void rollback(GetProfileViewData action,
			GetProfileViewDataResult result, ExecutionContext context)
			throws DispatchException {
		
	}

}
