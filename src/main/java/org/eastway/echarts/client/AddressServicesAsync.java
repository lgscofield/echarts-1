package org.eastway.echarts.client;

import java.util.List;

import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddressServicesAsync {
	public void findByEhr(EHR ehr, String sessionId, AsyncCallback<List<Address>> callback);
}
