package org.eastway.echarts.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.client.ReferralServices;
import org.eastway.echarts.domain.ReferralImpl;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Referral;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class ReferralServicesImpl extends RpcServicesImpl implements ReferralServices {

	@Override
	public Referral findByEhr(EHR ehr, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		TypedQuery<ReferralImpl> query = em.createQuery(
				"SELECT r FROM ReferralImpl r where r.ehr = " + ehr.getId(), ReferralImpl.class);
		Referral referral = query.getSingleResult().toDto();
		em.close();
		emf.close();
		return referral;
	}

}
