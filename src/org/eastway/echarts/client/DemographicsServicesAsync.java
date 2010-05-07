package org.eastway.echarts.client;

import org.eastway.echarts.shared.DemographicsDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DemographicsServicesAsync {
	public void getDemographics(long id, String sessionId, AsyncCallback<DemographicsDTO> callback);
}
