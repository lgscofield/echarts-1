package org.eastway.echarts.client;

import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Referral;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReferralServicesAsync {
	public void findByEhr(EHR ehr, String sessionId, AsyncCallback<Referral> callback);
}
