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
package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.eastway.echarts.shared.Code;

public class CodeService {
	@PersistenceContext(unitName="CodesService")
	protected EntityManager em;

	public CodeService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public List<CodeImpl> findColumnName(String columnName) {
		TypedQuery<CodeImpl> query = getEntityManager().createQuery(
				"SELECT c FROM CodeImpl c WHERE c.columnName = '" + columnName + "'",
				CodeImpl.class);
		return query.getResultList();
	}

	public Code find(long id) {
		return getEntityManager().find(CodeImpl.class, id);
	}
}
