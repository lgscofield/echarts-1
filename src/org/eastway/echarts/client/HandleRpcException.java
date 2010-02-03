package org.eastway.echarts.client;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class HandleRpcException {
	public static boolean sessionExpiredState = false;

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
				sessionExpiredState = true;
				Window.alert(e.getMessage());
			}
		} catch (Throwable e) {
			Window.alert(e.getMessage());
		}
	}
}
