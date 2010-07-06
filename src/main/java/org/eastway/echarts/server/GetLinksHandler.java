package org.eastway.echarts.server;

import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.domain.Link;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetLinksResult;
import org.eastway.echarts.shared.SessionExpiredException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

public class GetLinksHandler implements ActionHandler<GetLinks, GetLinksResult> {

	@Override
	public GetLinksResult execute(GetLinks action, ExecutionContext context)
			throws ActionException {
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

		TypedQuery<Link> query = em.createQuery(
				"SELECT link FROM Link link", Link.class);
		List<Link> linkList = query.getResultList();
		LinkedHashSet<String[]> linkDto = new LinkedHashSet<String[]>();
		for (Link link : linkList) {
			String[] s = new String[3];
			s[0] = link.getName();
			s[1] = link.getUrl();
			s[2] = link.getHeader();
			linkDto.add(s);
		}
		em.close();
		emf.close();
		return new GetLinksResult(linkDto);
	}

	@Override
	public Class<GetLinks> getActionType() {
		return GetLinks.class;
	}

	@Override
	public void rollback(GetLinks arg0, GetLinksResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
