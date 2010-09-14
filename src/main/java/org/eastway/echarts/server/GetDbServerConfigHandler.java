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

import javax.persistence.EntityManager;

import org.eastway.echarts.domain.DbServerConfigImpl;
import org.eastway.echarts.shared.DbServerConfig;
import org.eastway.echarts.shared.GetDbServerConfig;
import org.eastway.echarts.shared.GetDbServerConfigResult;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetDbServerConfigHandler implements ActionHandler<GetDbServerConfig, GetDbServerConfigResult> {

	@Override
	public Class<GetDbServerConfig> getActionType() {
		return GetDbServerConfig.class;
	}

	@Override
	public GetDbServerConfigResult execute(GetDbServerConfig action, ExecutionContext context) throws DispatchException {
		EntityManager em = EchartsEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		try {
			DbServerConfig nameValuePair = em.find(DbServerConfigImpl.class, action.getName());
			return new GetDbServerConfigResult(nameValuePair.getValue());
		} finally {
			em.close();
		}
	}

	@Override
	public void rollback(GetDbServerConfig action, GetDbServerConfigResult result, ExecutionContext context) throws DispatchException {
	}

}
