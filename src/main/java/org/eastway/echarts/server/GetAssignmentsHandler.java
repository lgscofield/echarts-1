package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.AssignmentImpl;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetAssignments;
import org.eastway.echarts.shared.GetAssignmentsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetAssignmentsHandler implements ActionHandler<GetAssignments, GetAssignmentsResult> {

	@Override
	public GetAssignmentsResult execute(GetAssignments action, ExecutionContext context)
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
		List<AssignmentImpl> assignments = em.createQuery(
				"SELECT a From AssignmentImpl a Where a.staff = '" + action.getStaffId() + "' And a.disposition = 'Open' Order By a.patient.lastName ASC, a.patient.firstName ASC", AssignmentImpl.class)
				.getResultList();
		List<Assignment> assignmentsDto = new ArrayList<Assignment>();
		for (Assignment assignment : assignments)
			if (assignment != null) {
				assignmentsDto.add(assignment.toDto());
			}
		em.close();
		return new GetAssignmentsResult(assignmentsDto);
	}

	@Override
	public Class<GetAssignments> getActionType() {
		return GetAssignments.class;
	}

	@Override
	public synchronized void rollback(GetAssignments list, GetAssignmentsResult result, ExecutionContext context) throws ActionException {
		
	}
}
