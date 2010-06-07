package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.client.AddressServices;
import org.eastway.echarts.domain.AddressImpl;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class AddressServicesImpl extends RpcServicesImpl implements AddressServices {
	@Override
	public List<Address> findByEhr(EHR ehr, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		TypedQuery<AddressImpl> query = em.createQuery(
				"SELECT a FROM AddressImpl a where a.caseNumber = '" + ehr.getSubject().getCaseNumber() + "'", AddressImpl.class);
		List<AddressImpl> addresses = query.getResultList();
		List<Address> dtos = new ArrayList<Address>();

		for (AddressImpl address : addresses)
			dtos.add(address.toDto());

		em.close();
		emf.close();
		return dtos;
	}
}
