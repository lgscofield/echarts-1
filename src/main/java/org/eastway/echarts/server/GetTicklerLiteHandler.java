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

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.GetTicklerLite;
import org.eastway.echarts.shared.GetTicklerLiteResult;
import org.eastway.echarts.shared.PatientListSuggestion;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.ui.SuggestOracle.Response;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetTicklerLiteHandler implements ActionHandler<GetTicklerLite, GetTicklerLiteResult> {

	@Override
	public Class<GetTicklerLite> getActionType() {
		return GetTicklerLite.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GetTicklerLiteResult execute(GetTicklerLite action,
			ExecutionContext context) throws DispatchException {
		ServiceUtil util = new ServiceUtil();
		try {
			util.checkSessionExpire(action.getSessionId());
		} catch (SessionExpiredException e) {
			throw new ActionException(e.getMessage());
		} catch (DbException e) {
			throw new ActionException("Database error");
		}
		Response response = new Response();
		String searchTerm = action.getRequest().getQuery();
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		List<String> ticklerLite = em.createNativeQuery("SELECT top 20 case_number + ' - ' + full_name as search FROM patient where case_number like '" + searchTerm + "%' OR last_name like '" + searchTerm + "%' OR first_name like '" + searchTerm + "%'").getResultList();
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		for (String s : ticklerLite)
			suggestions.add(new PatientListSuggestion(s));
		response.setSuggestions(suggestions);
		em.close();
		return new GetTicklerLiteResult(response);
	}

	@Override
	public void rollback(GetTicklerLite action, GetTicklerLiteResult result,
			ExecutionContext context) throws DispatchException {
		
	}

}
