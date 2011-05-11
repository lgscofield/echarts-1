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

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName("org.eastway.echarts.domain.DbServerConfig")
public interface DbServerConfigProxy extends EntityProxy {
	abstract Integer getVersion();

	abstract void setId(Long id);

	abstract Long getId();

	abstract void setConfigName(String name);

	abstract String getConfigName();

	abstract void setConfigValue(String value);

	abstract String getConfigValue();

	abstract String getServerMode();

	abstract void setServerMode(String serverMode);
}
