package org.eastway.echarts.server;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.domain.Productivity;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProductivity;
import org.eastway.echarts.shared.GetProductivityResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetProductivityHandler implements ActionHandler<GetProductivity, GetProductivityResult> {

	@Override
	public GetProductivityResult execute(GetProductivity action,
			ExecutionContext context) throws ActionException {
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
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String monthYear = month + "-" + year;
		List<Productivity> productivity = em.createQuery(
				"SELECT p FROM Productivity p WHERE p.staff = '5168' AND p.month = '" + monthYear + "'", Productivity.class)
				.getResultList();
		BigDecimal individual = new BigDecimal(0.00);
		BigDecimal group = new BigDecimal(0.00);
		for (Productivity p : productivity) {
			individual = individual.add(p.getIndividualService()
					.divide(new BigDecimal(4.00))
					.add(p.getDoctorService()));
			group = group.add(p.getGroupService()
					.divide(new BigDecimal(4.00)));
		}
		BigDecimal total = new BigDecimal(0.00);
		total = group.add(individual);
		GetProductivityResult result = new GetProductivityResult();
		result.setGroup(group);
		result.setIndividual(individual);
		result.setMonth(monthYear);
		result.setStaffId(action.getStaffId());
		result.setTotal(total);
		return result;
	}

	@Override
	public Class<GetProductivity> getActionType() {
		return GetProductivity.class;
	}

	@Override
	public void rollback(GetProductivity action, GetProductivityResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
