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
package org.eastway.echarts.client.rpc;

import org.eastway.echarts.domain.DbServerConfig;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@RooGwtMirroredFrom(DbServerConfig.class)
@Service(DbServerConfig.class)
public interface DbServerConfigRequest extends RequestContext {
	Request<DbServerConfigProxy> findDbServerConfig(String id);
}
