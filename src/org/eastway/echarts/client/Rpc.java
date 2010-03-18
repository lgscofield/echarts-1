package org.eastway.echarts.client;

import com.google.gwt.core.client.GWT;

public class Rpc {
	private static RpcServicesAsync singleton;

	private Rpc() {
		RpcServicesAsync rpcSvc = GWT.create(RpcServices.class);
		singleton = rpcSvc;
	}

	public static RpcServicesAsync singleton() {
		if (singleton == null)
			new Rpc();
		return Rpc.singleton;
	}
}
