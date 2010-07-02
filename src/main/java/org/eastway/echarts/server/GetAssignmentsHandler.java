package org.eastway.echarts.server;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

		LinkedHashMap<String, Long> pl = new LinkedHashMap<String, Long>();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		List<AssignmentImpl> assignments = em.createQuery(
				"SELECT a From AssignmentImpl a Where a.staff = '" + action.getStaffId() + "' And a.disposition = 'Open' Order By a.ehr.subject.lastName ASC, a.ehr.subject.firstName ASC", AssignmentImpl.class).getResultList();

		for (Assignment assignment : assignments)
			if (assignment != null)
				pl.put(new StringBuilder()
							.append(assignment.getEhr().getSubject().getCaseNumber())
							.append(" - ")
							.append( assignment.getEhr().getSubject().getLastName())
							.append((assignment.getEhr().getSubject().getSuffix() == null ? ", " : " " + assignment.getEhr().getSubject().getSuffix() + ", "))
							.append(assignment.getEhr().getSubject().getFirstName())
							.append((assignment.getEhr().getSubject().getMiddleInitial() == null ? "" : ", " + assignment.getEhr().getSubject().getMiddleInitial())).toString(),
							new Long(assignment.getEhr().getId()));
		em.close();
		emf.close();
		GetAssignmentsResult result = new GetAssignmentsResult(pl);
		return result;
	}

	@Override
	public Class<GetAssignments> getActionType() {
		return GetAssignments.class;
	}

	@Override
	public synchronized void rollback(GetAssignments list, GetAssignmentsResult result, ExecutionContext context) throws ActionException {
		
	}
}
