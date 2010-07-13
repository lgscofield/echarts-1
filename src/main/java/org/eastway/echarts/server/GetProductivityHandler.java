package org.eastway.echarts.server;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.Productivity;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetProductivity;
import org.eastway.echarts.shared.GetProductivityResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetProductivityHandler implements ActionHandler<GetProductivity, GetProductivityResult> {
	private EntityManager em = null;
	@Override
	public GetProductivityResult execute(GetProductivity action,
			ExecutionContext context) throws ActionException {
		em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}

		String staffId = null;
		if (action.getStaffId().equals("5597") || action.getStaffId().equals("5274"))
			staffId = "5262";
		else
			staffId = action.getStaffId();
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		String monthYear = month + "-" + year;
		List<Productivity> productivity = em.createQuery(
				"SELECT p FROM Productivity p WHERE p.staff = '" + staffId + "' AND p.month = '" + monthYear + "'", Productivity.class)
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
		result.setTotal(total.setScale(1, RoundingMode.HALF_UP));
		long monthlyWorkDays = getMonthlyWorkDays(calendar);
		long currentWorkDays = getCurrentWorkDays(calendar);
		result.setGreenNumber(getGreenNumber(monthlyWorkDays, currentWorkDays));
		result.setYellowNumber(getYellowNumber(monthlyWorkDays, currentWorkDays));
		em.close();
		return result;
	}

	private double getYellowNumber(double m, double c) {
		return new BigDecimal((92.0/m) * c).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private double getGreenNumber(double m, double c) {
		return new BigDecimal((100.0/m) * c).setScale(2, RoundingMode.HALF_UP).doubleValue();
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

	private long getMonthlyWorkDays(Calendar calendar) {
		long workDays = 0;
		int month = calendar.get(Calendar.MONTH);
		Calendar monthlyWorkDaysCalendar = Calendar.getInstance();

		monthlyWorkDaysCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		monthlyWorkDaysCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		monthlyWorkDaysCalendar.set(Calendar.DAY_OF_MONTH, 1);

		while (monthlyWorkDaysCalendar.get(Calendar.MONTH) == month) {
			if (monthlyWorkDaysCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
					monthlyWorkDaysCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				workDays++;
			monthlyWorkDaysCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		monthlyWorkDaysCalendar.add(Calendar.MONTH, -1);
		long holidays = getHolidays(monthlyWorkDaysCalendar);
		return workDays - holidays;
	}

	private long getHolidays(Calendar calendar) {
		Long holidays = em.createQuery(
			"SELECT COUNT(h) FROM Holiday h WHERE h.month = '" + (calendar.get(Calendar.MONTH)+1) + "' "
			+ "AND h.year = '" + calendar.get(Calendar.YEAR) + "' "
			+ "AND h.day > '"	+ calendar.get(Calendar.DAY_OF_MONTH) + "'" , Long.class)
			.getSingleResult();
		return holidays;
	}

	private long getCurrentWorkDays(Calendar calendar) {
		long holidays = getHolidays(calendar);
		long workDays = 0;
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		Calendar currentWorkDays = Calendar.getInstance();
		currentWorkDays.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		currentWorkDays.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		currentWorkDays.set(Calendar.DAY_OF_MONTH, 1);
		while (currentWorkDays.get(Calendar.DAY_OF_MONTH) != today) {
			if (currentWorkDays.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
					currentWorkDays.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				workDays++;
			currentWorkDays.add(Calendar.DAY_OF_MONTH, 1);
		}
		return workDays - holidays;
	}
}
