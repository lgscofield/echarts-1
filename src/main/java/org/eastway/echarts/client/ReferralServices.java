package org.eastway.echarts.client;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Referral;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("referralServices")
public interface ReferralServices extends RemoteService {
	public Referral findByEhr(EHR ehr, String sessionId) throws SessionExpiredException, DbException;
}
