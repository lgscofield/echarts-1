package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CodeService {
	@PersistenceContext(unitName="CodesService")
	protected EntityManager em;

	public CodeService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public List<Code> findColumnName(String columnName) {
		TypedQuery<Code> query = getEntityManager().createQuery(
				"SELECT c FROM Code c WHERE c.columnName = '" + columnName + "'",
				Code.class);
		return query.getResultList();
	}

	public Code find(long id) {
		return getEntityManager().find(Code.class, id);
	}
}
