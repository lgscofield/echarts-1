package org.eastway.echarts.client;

import com.google.gwt.core.client.GWT;

public class EchartsRpc {
	private static PatientServicesAsync rpc;

	private EchartsRpc() {
		PatientServicesAsync patientSvc = GWT.create(PatientServices.class);
		rpc = patientSvc;
	}

	public static PatientServicesAsync getRpc() {
		if (rpc == null)
			new EchartsRpc();
		return EchartsRpc.rpc;
	}
}
