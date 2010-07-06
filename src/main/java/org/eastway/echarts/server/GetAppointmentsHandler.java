package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.domain.AppointmentImpl;
import org.eastway.echarts.shared.Appointment;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetAppointments;
import org.eastway.echarts.shared.GetAppointmentsResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetAppointmentsHandler implements ActionHandler<GetAppointments, GetAppointmentsResult> {

	@Override
	public GetAppointmentsResult execute(GetAppointments action, ExecutionContext context) throws ActionException {
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
		List<AppointmentImpl> appointments = em.createQuery(
				"SELECT a From AppointmentImpl a WHERE a.caseNumber = '" + action.getCaseNumber() + "' Order By appointmentDate DESC", AppointmentImpl.class)
				.getResultList();
		List<Appointment> appointmentsDto = new ArrayList<Appointment>();
		for (AppointmentImpl appointment : appointments) {
			appointmentsDto.add(appointment.toDto());
		}
		em.close();
		emf.close();
		return new GetAppointmentsResult(appointmentsDto);
	}

	@Override
	public Class<GetAppointments> getActionType() {
		return GetAppointments.class;
	}

	@Override
	public void rollback(GetAppointments action, GetAppointmentsResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
