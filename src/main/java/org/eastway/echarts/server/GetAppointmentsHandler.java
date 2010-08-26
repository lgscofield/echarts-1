/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		String query = "SELECT a From AppointmentImpl a WHERE a.caseNumber = '" + action.getCaseNumber() + "' Order By a.appointmentDate DESC";
		String count = "SELECT count(a) From AppointmentImpl a WHERE a.caseNumber = '" + action.getCaseNumber() + "'";
		List<AppointmentImpl> appointments = em.createQuery(query, AppointmentImpl.class)
				.setFirstResult(action.getStartRecord())
				.setMaxResults(action.getMaxResults())
				.getResultList();
		Long rowCount = em.createQuery(count, Long.class).getSingleResult();
		List<Appointment> appointmentsDto = new ArrayList<Appointment>();
		for (AppointmentImpl appointment : appointments) {
			appointmentsDto.add(appointment.toDto());
		}
		em.close();
		return new GetAppointmentsResult(appointmentsDto, rowCount);
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
