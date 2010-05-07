package org.eastway.echarts.client;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ehrServices")
public interface EHRServices extends RemoteService {
	public EHRDTO getEhr(long ehrId, String sessionId) throws SessionExpiredException, DbException;

	public EHRDTO editEhr(EHRDTO ehrDto, String sessionId) throws SessionExpiredException, DbException;

	public void addEhr(EHRDTO ehrDto, String sessionId) throws SessionExpiredException, DbException;
}
