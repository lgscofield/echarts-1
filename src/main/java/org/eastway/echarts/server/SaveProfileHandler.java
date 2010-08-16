package org.eastway.echarts.server;

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.UserImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SaveProfile;
import org.eastway.echarts.shared.SaveProfileResult;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.User;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class SaveProfileHandler implements ActionHandler<SaveProfile, SaveProfileResult> {

	@Override
	public Class<SaveProfile> getActionType() {
		return SaveProfile.class;
	}

	@Override
	public SaveProfileResult execute(SaveProfile action, ExecutionContext context) throws DispatchException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		User user = action.getUser();
		em.getTransaction().begin();
		UserImpl userImpl = em.find(UserImpl.class, user.getUsername());
		userImpl.setProgram(user.getProgram());
		userImpl.setStaffName(user.getStaffName());
		userImpl.setCred1(user.getCred1());
		userImpl.setCred2(user.getCred2());
		em.persist(userImpl);
		em.getTransaction().commit();
		SaveProfileResult result;
		try {
			result = new SaveProfileResult(userImpl.toDto());
		} catch(NullPointerException e) {
			result = null;
		}
		em.close();
		return result;
	}

	@Override
	public void rollback(SaveProfile action, SaveProfileResult result,
			ExecutionContext context) throws DispatchException {
		// TODO Auto-generated method stub
		
	}

}
