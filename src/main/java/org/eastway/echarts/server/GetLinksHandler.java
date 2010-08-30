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

import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
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

		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Link> query = em.createQuery(
					"SELECT link FROM Link link ORDER BY link.header, link.sortOrder", Link.class);
			List<Link> linkList = query.getResultList();
			LinkedHashSet<String[]> linkDto = new LinkedHashSet<String[]>();
			for (Link link : linkList) {
				String[] s = new String[3];
				s[0] = link.getName();
				s[1] = link.getUrl();
				s[2] = link.getHeader();
				linkDto.add(s);
			}
			return new GetLinksResult(linkDto);
		} finally {
			em.close();
		}
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
