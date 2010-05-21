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
package org.eastway.echarts.client;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class HandleRpcException {
	private static boolean sessionExpiredState = false;

	public HandleRpcException(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			Window.alert("Please save your work, if you can, and refresh the browser");
		} catch (InvocationException e) {
			Window.alert("Server error: please try again in a few minutes");
		} catch (DbException e) {
			Window.alert(e.getMessage());
		} catch (SessionExpiredException e) {
			if (!sessionExpiredState) {
				setSessionExpiredState(true);
				Window.alert(e.getMessage());
			}
		} catch (Throwable e) {
			Window.alert(e.getMessage());
		}
	}

	public static void setSessionExpiredState(boolean state) {
		sessionExpiredState = state;
	}
}
