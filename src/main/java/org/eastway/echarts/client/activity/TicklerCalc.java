package org.eastway.echarts.client.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.shared.DueDateStatus;
import org.eastway.echarts.shared.Tickler;

public class TicklerCalc {
	private long NOW = new Date().getTime();
	private long day = 24L * 3600L * 1000L;
	private long YEAR = 365L * day;
	private long THIRTY_DAYS = 30L * day;
	private long NINETY_DAYS = 90L * day;
	private long ONE_HUNDRED_EIGHTY_DAYS = 180L * day;

	public List<Tickler> setDates(List<AssignmentProxy> assignments) {
		List<Tickler> tickler = new ArrayList<Tickler>();
		for (AssignmentProxy assignment : assignments) {
			Tickler result = new Tickler();
			result.setName(assignment.getName());
			result.setCaseNumber(assignment.getCaseNumber());

			if (assignment.getHipaaDateCompleted() != null) {
				TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
				UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
				result.setHipaaDateCompleted(formatDueDate(assignment.getHipaaDateCompleted().getTime()));
			} else {
				TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
				NO_DATA_COUNT = NO_DATA_COUNT.add(new BigDecimal(1));
			}

			long ispDueDate = 0L;
			long ispDateCompleted = 0L;
			if (assignment.getIspDateCompleted() != null) {
				ispDueDate = assignment.getIspDateCompleted().getTime() + YEAR;
				ispDateCompleted = assignment.getIspDateCompleted().getTime();
			}
			int ispDueDateStatus = getDueDateStatus(ispDueDate);
			result.setIspDueDate(formatDueDate(ispDueDate), ispDueDateStatus);
	
			long ispReviewDueDate = getIspReviewDueDate(ispDueDate, ispDueDateStatus, ispDateCompleted, assignment);
			result.setIspReviewDueDate(formatDueDate(ispReviewDueDate), getDueDateStatus(ispReviewDueDate));
	
			long healthHistoryDueDate = 0L;
			if (assignment.getHealthHistoryDateCompleted() != null)
				healthHistoryDueDate = assignment.getHealthHistoryDateCompleted().getTime() + YEAR;
			result.setHealthHistoryDueDate(formatDueDate(healthHistoryDueDate), getDueDateStatus(healthHistoryDueDate));
	
			long diagnosticAssessmentUpdateDueDate = 0L;
			if (assignment.getDiagnosticAssessmentDateCompleted() != null) {
				if (assignment.getService().matches("Pgm021")
						|| assignment.getService().matches("Pgm022")
						|| assignment.getService().matches("Pgm023")
						|| assignment.getService().matches("Pgm030"))
					diagnosticAssessmentUpdateDueDate = assignment.getDiagnosticAssessmentDateCompleted().getTime() + YEAR;
				else
					diagnosticAssessmentUpdateDueDate = assignment.getDiagnosticAssessmentDateCompleted().getTime() + (2L * YEAR);
			}
			result.setDiagnosticAssessmentUpdate(formatDueDate(diagnosticAssessmentUpdateDueDate), getDueDateStatus(diagnosticAssessmentUpdateDueDate));
	
			long financialDueDate = 0L;
			if (assignment.getFinancialDateCompleted() != null) {
				if (assignment.getTitleTwenty())
					financialDueDate = assignment.getFinancialDateCompleted().getTime() + ONE_HUNDRED_EIGHTY_DAYS;
				else
					financialDueDate = assignment.getFinancialDateCompleted().getTime() + YEAR;
			}
			result.setFinancialDueDate(formatDueDate(financialDueDate), getDueDateStatus(financialDueDate));
	
			long oocDueDate = 0L;
			if (assignment.getOutcomesConsumerDateCompleted() != null)
				oocDueDate = assignment.getOutcomesConsumerDateCompleted().getTime() + YEAR;
			result.setOoc(formatDueDate(oocDueDate), getDueDateStatus(oocDueDate));
			result.setService(assignment.getService());
			result.setStaffName(assignment.getStaffName());
			tickler.add(result);
		}
		return tickler;
	}

	private long getIspReviewDueDate(long ispDueDate, int ispDueDateStatus, long ispDateCompleted, AssignmentProxy assignment) {
		long ispReviewDueDate = 0L;
		if (ispDueDateStatus == DueDateStatus.OVERDUE) {
			return ispDueDate;
		} else {
			boolean aodStatus = (assignment.getAlcoholDrug()==null?false:assignment.getAlcoholDrug())&&(EchartsUser.staffId=="5542"||EchartsUser.staffId=="5396");
			if (assignment.getIspReviewDateCompleted() != null) {
				if (assignment.getService().matches("Pgm076")
						|| aodStatus
						|| assignment.getService().matches("Pgm021")
						|| assignment.getService().matches("Pgm022")
						|| assignment.getService().matches("Pgm023")
						|| assignment.getService().matches("Pgm030")) {
					ispReviewDueDate = assignment.getIspReviewDateCompleted().getTime() + NINETY_DAYS;
				} else {
					ispReviewDueDate = assignment.getIspReviewDateCompleted().getTime() + ONE_HUNDRED_EIGHTY_DAYS;
				}
				if (ispReviewDueDate > ispDueDate)
					return ispDueDate;
			} else {
				if (assignment.getService().matches("Pgm076") || aodStatus) {
					ispReviewDueDate = ispDateCompleted + NINETY_DAYS;
				} else {
					ispReviewDueDate = ispDateCompleted + ONE_HUNDRED_EIGHTY_DAYS;
				}
				return ispReviewDueDate;
			}
		}
		return ispReviewDueDate;
	}

	private BigDecimal NO_DATA_COUNT = new BigDecimal(0.0);
	private BigDecimal OVERDUE_COUNT = new BigDecimal(0.0);
	private BigDecimal UP_TO_DATE_COUNT = new BigDecimal(0.0);
	private BigDecimal TOTAL_COUNT = new BigDecimal(0.0);

	public BigDecimal getNoDataCount() {
		return NO_DATA_COUNT;
	}

	public BigDecimal getOverdueCount() {
		return OVERDUE_COUNT;
	}

	public BigDecimal getUpToDateCount() {
		return UP_TO_DATE_COUNT;
	}

	public BigDecimal getTotalCount() {
		return TOTAL_COUNT;
	}

	/**
	 * Determine status of {@code dueDate} and return an integer constant from
	 * {@link org.eastway.echarts.shared.DueDateStatus} representing the status.
	 * 
	 * This method should be called exactly once for each column to be displayed
	 * in the tickler table.
	 * 
	 * @param dueDate the long value to be tested
	 * @return an integer from {@code org.eastway.echarts.shared.DueDateStatus}
	 */
	private int getDueDateStatus(long dueDate) {
		TOTAL_COUNT = TOTAL_COUNT.add(new BigDecimal(1));
		if (dueDate == 0L) {
			NO_DATA_COUNT = NO_DATA_COUNT.add(new BigDecimal(1));
			return DueDateStatus.NO_DATA;
		}
		if ((dueDate - NOW) > THIRTY_DAYS) {
			UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.COMPLIANT;
		} else if (dueDate > NOW) {
			UP_TO_DATE_COUNT = UP_TO_DATE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.DUE_IN_THIRTY_DAYS;
		} else {
			OVERDUE_COUNT = OVERDUE_COUNT.add(new BigDecimal(1));
			return DueDateStatus.OVERDUE;
		}
	}

	private Date formatDueDate(long dueDate) {
		return new Date(dueDate);
	}
}
