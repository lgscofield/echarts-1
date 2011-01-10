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
package org.eastway.echarts.client.request;

import org.eastway.echarts.domain.Link;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Link.class)
public interface LinkProxy extends EntityProxy {
	public void setName(String name);

	public String getName();

	public void setUrl(String url);

	public String getUrl();

	public void setHeader(String header);

	public String getHeader();

	public void setSortOrder(Integer sortOrder);

	public Integer getSortOrder();

	public void setVersion(Integer version);

	public Integer getVersion();
}
