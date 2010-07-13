package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.DiagnosisImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Diagnosis;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetDiagnosesResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetDiagnosesHandler implements ActionHandler<GetDiagnoses, GetDiagnosesResult> {

	@Override
	public GetDiagnosesResult execute(GetDiagnoses action, ExecutionContext context)
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
		List<DiagnosisImpl> diagnoses = em.createQuery(
				"SELECT d FROM DiagnosisImpl d WHERE d.caseNumber = '" + action.getCaseNumber() + "' ORDER BY d.date DESC", DiagnosisImpl.class)
				.getResultList();
		List<Diagnosis> diagnosesDto = new ArrayList<Diagnosis>();
		for (DiagnosisImpl diagnosis : diagnoses)
			diagnosesDto.add(diagnosis.toDto());
		em.close();
		return new GetDiagnosesResult(diagnosesDto);
	}

	@Override
	public Class<GetDiagnoses> getActionType() {
		return GetDiagnoses.class;
	}

	@Override
	public void rollback(GetDiagnoses arg0, GetDiagnosesResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
