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

import java.util.List;

import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@RooGwtMirroredFrom("org.eastway.echarts.domain.User")
@ServiceName("org.eastway.echarts.domain.User")
public interface UserRequest extends RequestContext {
	Request<UserProxy> findUser(String userName);
	InstanceRequest<UserProxy, Void> persist();
	Request<List<UserProxy>> findSupervisorAssignments(String supervisorName);
	Request<List<AssignmentProxy>> findAssignments(String supervisor, String staff);
	InstanceRequest<UserProxy, Void> remove();
	InstanceRequest<UserProxy, UserProxy> merge();
	Request<UserProxy> findUserByStaffId(String staffId);
}
