package org.eastway.echarts.server;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.SessionIdLog;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SessionExpiredException;
import org.springframework.dao.EmptyResultDataAccessException;

public class ServiceUtil {
	protected void checkSessionExpire(String sessionId) throws SessionExpiredException, DbException {
		if (sessionId == null)
			throw new IllegalArgumentException("Please login");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();

		TypedQuery<SessionIdLog> query = em.createQuery(
				"SELECT s FROM SessionIdLog s WHERE s.sessionId = '" + sessionId + "'",
					SessionIdLog.class);
		SessionIdLog sil = null;
		try {
			sil = query.getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			throw new SessionExpiredException("Please login");
		} catch (NoResultException e) {
			throw new SessionExpiredException("Please login");
		}
		Date now = new Date();
		Date expire = new Date(sil.getSessionIdExpire());

		if (now.after(expire))
			throw new SessionExpiredException();
		em.close();
		emf.close();
	}
}
