package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.domain.MedicationImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetMedicationsResult;
import org.eastway.echarts.shared.Medication;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetMedicationsHandler implements ActionHandler<GetMedications, GetMedicationsResult> {

	@Override
	public GetMedicationsResult execute(GetMedications action,
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
		List<MedicationImpl> medications = em.createQuery(
				"SELECT m FROM MedicationImpl m WHERE m.caseNumber = '" + action.getCaseNumber() + "'", MedicationImpl.class)
				.getResultList();
		List<Medication> medicationsDto = new ArrayList<Medication>();
		for (MedicationImpl medication : medications)
			medicationsDto.add(medication.toDto());
		return new GetMedicationsResult(medicationsDto);
	}

	@Override
	public Class<GetMedications> getActionType() {
		return GetMedications.class;
	}

	@Override
	public void rollback(GetMedications arg0, GetMedicationsResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
