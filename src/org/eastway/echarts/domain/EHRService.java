package org.eastway.echarts.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class EHRService {
	@PersistenceContext(unitName="EHRService")
	protected EntityManager em;

	public EHRService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public EHR createEhr(Patient subject, Date timeCreated) {
		EHR ehr = new EHR();
		ehr.setSubject(subject);
		ehr.setTimeCreated(timeCreated);
		getEntityManager().persist(ehr);
		return ehr;
	}

	public EHR findEhr(long ehrId) {
		return getEntityManager().find(EHR.class, ehrId);
	}

	public List<EHR> findAll() {
		TypedQuery<EHR> query = getEntityManager().createQuery(
				"SELECT e FROM EHR e", EHR.class);
		return query.getResultList();
	}
}
