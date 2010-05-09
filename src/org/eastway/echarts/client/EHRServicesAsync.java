package org.eastway.echarts.client;

import org.eastway.echarts.shared.EHRDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EHRServicesAsync {
	public void getEhr(long ehrId, String sessionId, AsyncCallback<EHRDTO> callback);

	public void editEhr(EHRDTO ehrDto, String sessionId, AsyncCallback<EHRDTO> callback);

	public void addEhr(EHRDTO ehrDto, String sessionId, AsyncCallback<Void> callback);
}
